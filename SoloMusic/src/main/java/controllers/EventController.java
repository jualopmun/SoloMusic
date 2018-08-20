
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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


	@RequestMapping(value = "/user/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int p) {
		ModelAndView result;

		try {
			final Collection<Event> event = this.userSpaceService.findOne(p).getEvents();
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

}
