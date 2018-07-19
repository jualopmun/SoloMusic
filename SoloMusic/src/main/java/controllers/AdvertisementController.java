
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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
		final Collection<Advertisement> ads = this.advertisementService.findAll();

		result = new ModelAndView("advertisement/list");
		result.addObject("ads", ads);
		result.addObject("requestURI", "advertisement/list.do");

		return result;
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ModelAndView listUser(@RequestParam final int varId) {
		final ModelAndView result;
		final Actor user = this.actorService.findByPrincipal();
		Collection<Advertisement> ads = new ArrayList<Advertisement>();
		if (varId == 0)
			ads = user.getOwnerAdvertisement();
		else
			ads = user.getRegistersAdvertisement();

		result = new ModelAndView("advertisement/list");
		result.addObject("ads", ads);
		result.addObject("requestURI", "advertisement/list.do");

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
	public ModelAndView edit(@RequestParam final int varId) {
		final ModelAndView result;
		final Advertisement advertisement = this.advertisementService.findOne(varId);
		Assert.notNull(advertisement);
		result = this.createEditModelAndView(advertisement);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Advertisement advertisement, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(advertisement);
		else
			try {
				this.advertisementService.save(advertisement);
				result = new ModelAndView("redirect:/advertisement/manager/list.do");
			} catch (final Throwable oops) {
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
				result = new ModelAndView("redirect:/advertisement/manager/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(advertisement, "advertisement.commit.error");
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
