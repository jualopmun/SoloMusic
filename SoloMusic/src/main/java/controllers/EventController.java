package controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		private EventService eventService;
		
		@Autowired
		private LoginService loginservice;
		
		@Autowired
		private UserSpaceService userSpaceService;
		
	
		@RequestMapping(value = "/user/view", method = RequestMethod.GET)
		public ModelAndView view(@RequestParam int p) {
			ModelAndView result;
		
			try {
				Collection<Event> event= userSpaceService.findOne(p).getEvents();
				result = new ModelAndView("event/view");
				
				result.addObject("event", event);
				
				
				
			} catch (Throwable e) {
				result = new ModelAndView("redirect:/welcome/index.do");
			}

			return result;
		}

}
