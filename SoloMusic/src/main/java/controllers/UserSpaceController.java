package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.UserSpace;
import security.LoginService;
import services.UserSpaceService;

@Controller
@RequestMapping("userspace")
public class UserSpaceController  extends AbstractController {
	
	
	@Autowired
	private UserSpaceService	userSpaceService;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/user/view", method = RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView result;
		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {
			result = new ModelAndView("userspace/view");
			result.addObject("requestURI", "/user/view.do");
			result.addObject("p", man.getUserSpace());
			
			
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}
	
	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			UserSpace userSpace = userSpaceService.create();
			result = createNewModelAndView(userSpace, null);
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;
	}
	
	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {
			UserSpace userSpace = man.getUserSpace();
			result = createNewModelAndView(userSpace, null);
		} catch (Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;
	}
	
	@RequestMapping(value = "/user/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid  UserSpace userSpace,  BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createNewModelAndView(userSpace, null);
		} else {
			try {
				
				userSpaceService.save(userSpace);

				result = new ModelAndView("redirect:/userspace/user/view.do");

			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createNewModelAndView(userSpace, "actor.commit.error");
			}
		}
		return result;
	}
	
	
	protected ModelAndView createNewModelAndView(UserSpace userSpace, String message) {
		ModelAndView result;

		result = new ModelAndView("userspace/create");
		result.addObject("userspace", userSpace);

		result.addObject("message", message);
		return result;
	}
	
	
}
