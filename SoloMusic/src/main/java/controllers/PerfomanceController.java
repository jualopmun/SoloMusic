
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Perfomance;
import domain.UserSpace;
import security.LoginService;
import services.PerformanceService;
import services.UserSpaceService;

@Controller
@RequestMapping("perfomance")
public class PerfomanceController extends AbstractController {

	@Autowired
	private PerformanceService	performanceService;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private UserSpaceService	userSpaceService;


	@RequestMapping(value = "/user/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int p) {
		ModelAndView result;

		try {
			final Collection<Perfomance> perfomance = this.userSpaceService.findOne(p).getPerfomances();
			result = new ModelAndView("perfomance/view");

			// Para que salga el boton de crear tiene que estar autenticado y compprobar que
			// es su espacio
			if (LoginService.isAnyAuthenticated()) {
				final Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
				result.addObject("actor", actor);
				final UserSpace userSpace = this.userSpaceService.findOne(p);
				result.addObject("userSpace", userSpace);
			} else {
				UserSpace userSpace = this.userSpaceService.findOne(p);
				result.addObject("userSpace", userSpace);
			}

			result.addObject("perfomance", perfomance);

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
			final Perfomance perfomance = this.performanceService.create();
			result = this.createEditModelAndView(perfomance, null);
			result.addObject("actor", actor);
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

			Perfomance perfomance = this.performanceService.findOne(p);
			Assert.isTrue(actor.getUserSpace().getPerfomances().contains(perfomance));

			result = this.createEditModelAndView(perfomance, null);
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

			final Perfomance perfomance = this.performanceService.findOne(p);
			this.performanceService.delete(perfomance);
			result = new ModelAndView("redirect:/perfomance/user/view.do?p=" + actor.getUserSpace().getId());
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final Perfomance perfomance, final BindingResult binding) {
		ModelAndView result;
		final Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if (binding.hasErrors())
			result = this.createEditModelAndView(perfomance, null);
		else
			try {

				if (!perfomance.getVideoUrl().contains("https://www.youtube.com/watch?v=")) {

					binding.rejectValue("videoUrl", "perfomance.videoUrl.error", "error");
					throw new IllegalArgumentException();
				}

				if (perfomance.getVideoUrl().contains("&")) {

					binding.rejectValue("videoUrl", "perfomance.videoUrl.error", "error");
					throw new IllegalArgumentException();

				}

				//perfomance.setVideoUrl(perfomance.getVideoUrl().replace("https://www.youtube.com/watch?v=", ""));

				this.performanceService.save(perfomance);

				result = new ModelAndView("redirect:/perfomance/user/view.do?p=" + actor.getUserSpace().getId());

			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createEditModelAndView(perfomance, "actor.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Perfomance perfomance) {
		ModelAndView result;

		result = this.createEditModelAndView(perfomance, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Perfomance perfomance, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("perfomance/create");
		result.addObject("perfomance", perfomance);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "user/create.do");
		final Actor actor = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		result.addObject("actor", actor);
		return result;
	}

}
