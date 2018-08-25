
package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
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
import domain.Event;
import domain.UserSpace;
import security.LoginService;
import services.EventService;
import services.UserSpaceService;

@Controller
@RequestMapping("event")
public class EventController extends AbstractController {

	@Autowired
	private EventService		eventService;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private UserSpaceService	userSpaceService;

	public Integer				eventId;


	@RequestMapping(value = "/user/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int p) {
		ModelAndView result;

		try {
			final List<Event> event = (List<Event>) this.userSpaceService.findOne(p).getEvents();
			Collections.reverse(event);
			result = new ModelAndView("event/view");

			//Para que salga el boton de crear tiene que estar autenticado y compprobar que es su espacio
			if (LoginService.isAnyAuthenticated()) {
				final Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
				result.addObject("actor", actor);
				final UserSpace userSpace = this.userSpaceService.findOne(p);
				result.addObject("userSpace", userSpace);
			}

			result.addObject("event", event);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "user/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {
			final Event event = this.eventService.create();
			result = this.createEditModelAndView(event, null);
			result.addObject("actor", actor);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "/user/location", method = RequestMethod.GET)
	public ModelAndView location(@RequestParam final int p) {
		ModelAndView result;

		try {

			result = new ModelAndView("event/location");

			Event event = eventService.findOne(p);

			result.addObject("event", event);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "user/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int p) {
		ModelAndView result;
		Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {

			final Event event = this.eventService.findOne(p);
			Assert.isTrue(actor.getUserSpace().getEvents().contains(event));

			result = this.createEditModelAndView(event, null);
			result.addObject("actor", actor);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "user/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int p) {
		ModelAndView result;
		final Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {

			final Event event = this.eventService.findOne(p);
			this.eventService.delete(event);
			result = new ModelAndView("redirect:/event/user/view.do?p=" + actor.getUserSpace().getId());
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final Event event, final BindingResult binding) {
		ModelAndView result;
		final Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if (binding.hasErrors())
			result = this.createEditModelAndView(event, null);
		else
			try {

				SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
				Date start = parser.parse(event.getStartDate());

				if (start.before(new Date())) {
					binding.rejectValue("startDate", "event.startDate.error", "error");
					throw new IllegalArgumentException();
				}
				if (!event.getLocationUrl().contains("/maps/place/") || !event.getLocationUrl().contains("google") || !event.getLocationUrl().contains("@")) {

					binding.rejectValue("locationUrl", "event.location.error", "error");
					throw new IllegalArgumentException();
				}

				this.eventService.save(event);

				result = new ModelAndView("redirect:/event/user/view.do?p=" + actor.getUserSpace().getId());

			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createEditModelAndView(event, "actor.commit.error");
			}
		return result;
	}

	//Subida de imagen
	@RequestMapping(value = "image/upload", method = RequestMethod.GET)
	public ModelAndView uploadImage(@RequestParam int q) {
		ModelAndView result;

		try {
			Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
			Event event = eventService.findOne(q);
			Assert.isTrue(actor.getUserSpace().getEvents().contains(event));
			result = this.createEditModelAndViewUpload(event, null);
			result.addObject("actor", actor);
			eventId = event.getId();

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "/user/up", method = RequestMethod.POST)
	public ModelAndView saveCreate(@RequestParam("mainImg") final MultipartFile file) {
		ModelAndView result;

		if (file.isEmpty()) {
			result = new ModelAndView("event/upload");
			result.addObject("event", null);
			result.addObject("message", "file.null.error");
			result.addObject("requestURI", "user/create.do");
			return result;
		}

		else if (!file.getOriginalFilename().contains(".jpg")) {
			result = new ModelAndView("event/upload");
			result.addObject("event", null);
			result.addObject("message", "file.format.error");
			result.addObject("requestURI", "user/create.do");
			return result;
		} else if (file.getSize() >= 268435455) {
			result = new ModelAndView("event/upload");
			result.addObject("event", null);
			result.addObject("message", "file.format.error.image");
			result.addObject("requestURI", "user/create.do");
			return result;

		}

		else
			try {
				Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());

				eventService.saveJpg(file, eventId);
				result = new ModelAndView("redirect:/event/user/view.do?p=" + actor.getUserSpace().getId());

			} catch (final Throwable th) {
				th.printStackTrace();
				result = new ModelAndView("track/create");
				result.addObject("track", null);
				result.addObject("message", "actor.commit.error");
				result.addObject("requestURI", "user/create.do");
			}
		return result;
	}

	@RequestMapping(value = "/view/image", method = RequestMethod.GET, produces = {
		MediaType.APPLICATION_OCTET_STREAM_VALUE
	})
	public HttpEntity<byte[]> downloadRecipientFileImage(@RequestParam int q) throws IOException, ServletException {

		Event event = eventService.findOne(q);
		if (event == null || event.getMainImg() == null || event.getMainImg().length <= 0)
			throw new ServletException("No clip found/clip has not data, id=" + q);
		final HttpHeaders header = new HttpHeaders();

		// header.setContentType(new MediaType("audio", "mp3"));
		header.setContentType(new MediaType("image", "jpg"));
		header.setContentLength(event.getMainImg().length);

		return new HttpEntity<byte[]>(event.getMainImg(), header);
	}

	protected ModelAndView createEditModelAndView(final Event event) {
		ModelAndView result;

		result = this.createEditModelAndView(event, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Event event, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("event/create");
		result.addObject("event", event);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "user/create.do");
		Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		result.addObject("actor", actor);

		return result;
	}

	protected ModelAndView createEditModelAndViewUpload(final Event event, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("event/upload");
		result.addObject("event", event);
		result.addObject("message", messageCode);

		return result;
	}

}
