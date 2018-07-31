
package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdvertisementService;
import domain.Actor;
import domain.Advertisement;

@Controller
@RequestMapping("advertisement")
public class AdvertisementController extends AbstractController {

	//Services

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdvertisementService	advertisementService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Advertisement> advertisements = this.advertisementService.findAll();

		result = new ModelAndView("advertisement/list");
		result.addObject("advertisements", advertisements);
		result.addObject("requestURI", "advertisement/list.do");

		return result;
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ModelAndView listUser(@RequestParam final int q) {
		final ModelAndView result;
		final Actor user = this.actorService.findByPrincipal();
		Collection<Advertisement> advertisements = new ArrayList<Advertisement>();
		if (q == 0)
			advertisements = user.getOwnerAdvertisement();
		else
			advertisements = user.getRegistersAdvertisement();

		result = new ModelAndView("advertisement/list");
		result.addObject("advertisements", advertisements);
		result.addObject("requestURI", "advertisement/user/list.do?q=" + q);

		return result;
	}

	//Viewing

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int q) {
		final ModelAndView result;
		final Advertisement advertisement = this.advertisementService.findOne(q);
		boolean isOwner = false;
		boolean isRegistered = false;

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().toArray()[0].equals("USER")) {
			final Actor principal = this.actorService.findByPrincipal();
			isOwner = advertisement.getActorOwener().getId() == principal.getId();
			isRegistered = advertisement.getActorRegisters().contains(principal);
		}

		result = new ModelAndView("advertisement/view");
		result.addObject("advertisement", advertisement);
		result.addObject("isOwner", isOwner);
		result.addObject("isRegistered", isRegistered);
		result.addObject("requestURI", "advertisement/view.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final Advertisement advertisement = this.advertisementService.create();
		result = this.createEditModelAndView(advertisement);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int q) throws ParseException {
		final ModelAndView result;
		
		final Advertisement advertisement = this.advertisementService.findOne(q);
		
		
		Assert.notNull(advertisement);
		result = this.createEditModelAndView(advertisement);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Advertisement advertisement, final BindingResult binding) {
		ModelAndView result;
		
		if (binding.hasErrors())
			result = this.createEditModelAndView(advertisement);
		else
			try {
				if (advertisement.getStartDate().after(advertisement.getEndDate())) {
					binding.rejectValue("endDate", "acme.date.later","error");
					throw new IllegalArgumentException();
				}
				
				this.advertisementService.save(advertisement);
				result = this.listUser(0);
			} catch (final Throwable oops) {
				System.out.println(oops.toString());
				result = this.createEditModelAndView(advertisement, "advertisement.commit.error");
			}
		return result;
	}

	//Deletion

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Advertisement advertisement, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(advertisement);
		else
			try {
				this.advertisementService.delete(advertisement);
				result = this.listUser(0);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(advertisement, "advertisement.commit.error");
			}
		return result;
	}

	//Registering

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam final int q) {
		ModelAndView result;
		final Advertisement advertisement = this.advertisementService.findOne(q);

		try {
			this.advertisementService.register(advertisement);
			result = this.listUser(1);
		} catch (final Throwable oops) {
			result = this.listUser(1);
		}

		return result;
	}

	@RequestMapping(value = "/unregister", method = RequestMethod.GET)
	public ModelAndView unregister(@RequestParam final int q) {
		ModelAndView result;
		final Advertisement advertisement = this.advertisementService.findOne(q);

		try {
			this.advertisementService.unregister(advertisement);
			result = this.listUser(1);
		} catch (final Throwable oops) {
			result = this.listUser(1);
		}

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Advertisement advertisement) {
		ModelAndView result;

		result = this.createEditModelAndView(advertisement, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Advertisement advertisement, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("advertisement/edit");
		result.addObject("advertisement", advertisement);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "advertisement/edit.do");

		return result;
	}
}
