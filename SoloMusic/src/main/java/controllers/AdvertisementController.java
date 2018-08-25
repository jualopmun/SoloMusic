
package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Advertisement;
import security.LoginService;
import services.ActorService;
import services.AdvertisementService;

@Controller
@RequestMapping("advertisement")
public class AdvertisementController extends AbstractController {

	//Services

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdvertisementService	advertisementService;

	@Autowired
	private LoginService			loginService;

	public Integer					advertisementId;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Collection<Advertisement> advertisements = this.advertisementService.findAll();

			result = new ModelAndView("advertisement/list");
			Actor actor = actorService.findByPrincipal();
			result.addObject("actor", actor);
			result.addObject("advertisements", advertisements);
			result.addObject("requestURI", "advertisement/list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ModelAndView listUser(@RequestParam final int q) {
		ModelAndView result;
		try {
			final Actor user = this.actorService.findByPrincipal();
			Collection<Advertisement> advertisements = new ArrayList<Advertisement>();
			if (q == 0)
				advertisements = user.getOwnerAdvertisement();
			else
				advertisements = user.getRegistersAdvertisement();

			result = new ModelAndView("advertisement/list");
			Actor actor = actorService.findByPrincipal();
			result.addObject("advertisements", advertisements);
			result.addObject("actor", actor);
			result.addObject("requestURI", "advertisement/user/list.do?q=" + q);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	//Viewing

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int q) {
		ModelAndView result;
		try {
			final Advertisement advertisement = this.advertisementService.findOne(q);
			boolean isOwner = false;
			boolean isRegistered = false;
			Actor actor = new Actor();

			if (LoginService.isAnyAuthenticated()) {
				actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
				isOwner = advertisement.getActorOwener().getId() == actor.getId();
				isRegistered = advertisement.getActorRegisters().contains(actor);

			}

			result = new ModelAndView("advertisement/view");
			result.addObject("advertisement", advertisement);
			result.addObject("isOwner", isOwner);
			result.addObject("isRegistered", isRegistered);
			result.addObject("actor", actor);

			result.addObject("requestURI", "advertisement/view.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Actor principal = this.actorService.findByPrincipal();
			Assert.isTrue(principal.getIsPremium());
			Advertisement advertisement = this.advertisementService.create();
			result = this.createEditModelAndView(advertisement);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int q) throws ParseException {
		ModelAndView result;
		try {
			Actor principal = this.actorService.findByPrincipal();
			Assert.isTrue(principal.getIsPremium());
			final Advertisement advertisement = this.advertisementService.findOne(q);
			Assert.isTrue(principal.getOwnerAdvertisement().contains(advertisement));

			Assert.notNull(advertisement);
			result = this.createEditModelAndView(advertisement);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Advertisement advertisement, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(advertisement);
		else
			try {
				if (!advertisement.getLocationUrl().contains("/maps/place/") || !advertisement.getLocationUrl().contains("google") || !advertisement.getLocationUrl().contains("@")) {

					binding.rejectValue("locationUrl", "event.location.error", "error");
					throw new IllegalArgumentException();
				}
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-mm-dd");
				Date start = formatoDelTexto.parse(advertisement.getStartDate());
				Date end = formatoDelTexto.parse(advertisement.getEndDate());
				Date today = new Date();

				if (start.after(end)) {

					binding.rejectValue("endDate", "acme.date.later", "error");
					throw new IllegalArgumentException();
				}

				Actor principal = this.actorService.findByPrincipal();
				Assert.isTrue(principal.getIsPremium());
				//				DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
				//				Date start = (Date) format.parse(advertisement.getStartDate());
				//				Date end = (Date) format.parse(advertisement.getEndDate());
				//				if (start.after(end)) {
				//					binding.rejectValue("endDate", "acme.date.later", "error");
				//					throw new IllegalArgumentException();
				//				}

				this.advertisementService.save(advertisement);
				result = new ModelAndView("redirect:/advertisement/user/list.do?q=0");
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
			result = new ModelAndView("redirect:/advertisement/user/list.do?q=1");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/unregister", method = RequestMethod.GET)
	public ModelAndView unregister(@RequestParam final int q) {
		ModelAndView result;
		final Advertisement advertisement = this.advertisementService.findOne(q);

		try {
			this.advertisementService.unregister(advertisement);
			result = new ModelAndView("redirect:/advertisement/user/list.do?q=1");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	//subir imagen

	@RequestMapping(value = "/view/image", method = RequestMethod.GET, produces = {
		MediaType.APPLICATION_OCTET_STREAM_VALUE
	})
	public HttpEntity<byte[]> downloadRecipientFileImage(@RequestParam int q) throws IOException, ServletException {

		Advertisement advertisement = advertisementService.findOne(q);
		if (advertisement == null || advertisement.getMainImg() == null || advertisement.getMainImg().length <= 0)
			throw new ServletException("No clip found/clip has not data, id=" + q);
		final HttpHeaders header = new HttpHeaders();

		// header.setContentType(new MediaType("audio", "mp3"));
		header.setContentType(new MediaType("image", "jpg"));
		header.setContentLength(advertisement.getMainImg().length);

		return new HttpEntity<byte[]>(advertisement.getMainImg(), header);
	}
	@RequestMapping(value = "image/upload", method = RequestMethod.GET)
	public ModelAndView uploadImage(@RequestParam int q) {
		ModelAndView result;

		try {
			Advertisement advertisement = advertisementService.findOne(q);
			Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
			Assert.isTrue(actor.getOwnerAdvertisement().contains(advertisement));
			result = this.createEditModelAndViewUpload(advertisement, null);
			advertisementId = advertisement.getId();

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}
	@RequestMapping(value = "/user/up", method = RequestMethod.POST)
	public ModelAndView saveCreate(@RequestParam("mainImg") final MultipartFile file) {
		ModelAndView result;

		if (file.isEmpty()) {
			result = new ModelAndView("advertisement/upload");
			result.addObject("advertisement", null);
			result.addObject("message", "file.null.error");
			result.addObject("requestURI", "user/create.do");
			return result;
		}

		else if (!file.getOriginalFilename().contains(".jpg")) {
			result = new ModelAndView("advertisement/upload");
			result.addObject("advertisement", null);
			result.addObject("message", "file.format.error");
			result.addObject("requestURI", "user/create.do");
			return result;
		} else if (file.getSize() >= 268435455) {
			result = new ModelAndView("advertisement/upload");
			result.addObject("advertisement", null);
			result.addObject("message", "file.format.error.image");
			result.addObject("requestURI", "user/create.do");
			return result;

		}

		else
			try {

				advertisementService.saveJpg(file, advertisementId);
				result = new ModelAndView("redirect:/advertisement/user/list.do?q=0");

			} catch (final Throwable th) {
				th.printStackTrace();
				result = new ModelAndView("track/create");
				result.addObject("track", null);
				result.addObject("message", "actor.commit.error");
				result.addObject("requestURI", "user/create.do");
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

	@RequestMapping(value = "/user/location", method = RequestMethod.GET)
	public ModelAndView location(@RequestParam final int p) {
		ModelAndView result;

		try {

			result = new ModelAndView("advertisement/location");

			Advertisement advertisement = advertisementService.findOne(p);

			result.addObject("advertisement", advertisement);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	protected ModelAndView createEditModelAndViewUpload(final Advertisement advertisement) {
		ModelAndView result;

		result = this.createEditModelAndView(advertisement, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewUpload(final Advertisement advertisement, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("advertisement/upload");
		result.addObject("advertisement", advertisement);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "advertisement/edit.do");

		return result;
	}

	//Buscar 

	@RequestMapping(value = "/user/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(@RequestParam("searchTerm") final String searchTerm) {
		ModelAndView result;
		List<Advertisement> advertisementSearch = new ArrayList<Advertisement>();

		try {
			result = new ModelAndView("advertisement/list");
			result.addObject("requestURI", "advertisement/user/list.do");
			advertisementSearch = this.advertisementService.advertisementSearch(searchTerm);
			result.addObject("advertisements", advertisementSearch);

		} catch (final Throwable th) {
			result = new ModelAndView("redirect:/advertisement/user/list.do"); // posible vista 404?
		}
		return result;
	}
}
