
package controllers;

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

import security.LoginService;
import services.ActorService;
import services.UserSpaceService;
import domain.Actor;
import domain.UserSpace;

@Controller
@RequestMapping("userspace")
public class UserSpaceController extends AbstractController {

	@Autowired
	private UserSpaceService	userSpaceService;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private ActorService		actorService;


	@RequestMapping(value = "/user/view", method = RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView result;
		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {
			result = new ModelAndView("userspace/view");
			result.addObject("requestURI", "/user/view.do");
			result.addObject("p", man.getUserSpace());
			result.addObject("actor", man);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final UserSpace userSpace = this.userSpaceService.create();
			result = this.createNewModelAndView(userSpace, null);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {
			final UserSpace userSpace = man.getUserSpace();
			result = this.createNewModelAndView(userSpace, null);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;
	}

	@RequestMapping(value = "/user/spaceview", method = RequestMethod.GET)
	public ModelAndView view2(@RequestParam final int q) {
		ModelAndView result;

		try {
			final Actor a = this.actorService.findByUserSpaceId(q);
			final boolean followed = a.getFollowers().contains(this.actorService.findByPrincipal());

			result = new ModelAndView("userspace/view");
			result.addObject("requestURI", "/user/view.do");

			result.addObject("p", this.userSpaceService.findOne(q));
			result.addObject("followed", followed);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		try {

			result = new ModelAndView("userspace/list");
			result.addObject("requestURI", "userspace/user/list.do");
			final List<UserSpace> userspace = this.userSpaceService.findAll();
			result.addObject("userspace", userspace);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final UserSpace userSpace, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			for (final ObjectError e : binding.getAllErrors())
				System.out.println(e.toString());

			result = this.createNewModelAndView(userSpace, null);
		} else
			try {

				this.userSpaceService.save(userSpace);

				result = new ModelAndView("redirect:/userspace/user/view.do");

			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createNewModelAndView(userSpace, "actor.commit.error");
			}
		return result;
	}

	protected ModelAndView createNewModelAndView(final UserSpace userSpace, final String message) {
		ModelAndView result;

		result = new ModelAndView("userspace/create");
		result.addObject("userspace", userSpace);

		result.addObject("message", message);
		return result;
	}

}
