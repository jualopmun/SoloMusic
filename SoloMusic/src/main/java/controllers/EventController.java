package controllers;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Event;
import domain.UserSpace;
import forms.ActorRegisterForm;
import junit.framework.Assert;
import security.LoginService;
import services.EventService;
import services.UserSpaceService;

@Controller
@RequestMapping("event")
public class EventController extends AbstractController {

	@Autowired
	private EventService eventService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserSpaceService userSpaceService;

	@RequestMapping(value = "/user/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int p) {
		ModelAndView result;

		try {
			Collection<Event> event = userSpaceService.findOne(p).getEvents();
			result = new ModelAndView("event/view");
			
			//Para que salga el boton de crear tiene que estar autenticado y compprobar que es su espacio
			if(LoginService.isAnyAuthenticated()) {
				 Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
				 result.addObject("actor", actor);
				 UserSpace userSpace=userSpaceService.findOne(p);
				 result.addObject("userSpace", userSpace);
			}

			result.addObject("event", event);

		} catch (Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "user/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		 Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {
		    Event event = eventService.create();
			result = this.createEditModelAndView(event, null);
			result.addObject("actor", actor);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;
	
	}
	
	@RequestMapping(value = "user/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int p) {
		ModelAndView result;
		 Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {
			
		    Event event = eventService.findOne(p);
		   
			result = this.createEditModelAndView(event, null);
			result.addObject("actor", actor);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;
	
	}
	
	@RequestMapping(value = "user/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int p) {
		ModelAndView result;
		Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {
			
		    Event event = eventService.findOne(p);
		    eventService.delete(event);
		    result = new ModelAndView("redirect:/event/user/view.do?p="+actor.getUserSpace().getId());
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;
	
	}
	
	
	
	@RequestMapping(value = "/user/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid  Event event,  BindingResult binding) {
		ModelAndView result;
		 Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if (binding.hasErrors()) {
			
			result = this.createEditModelAndView(event, null);
		} else
			try {

				this.eventService.save(event);

				result = new ModelAndView("redirect:/event/user/view.do?p="+actor.getUserSpace().getId());

			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createEditModelAndView(event, "actor.commit.error");
			}
		return result;
	}
	
	protected ModelAndView createEditModelAndView( Event event) {
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

			return result;
	}

}
