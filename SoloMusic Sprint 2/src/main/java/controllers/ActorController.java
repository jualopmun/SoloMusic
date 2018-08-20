
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Advertisement;
import security.LoginService;
import services.ActorService;
import services.AdvertisementService;

@Controller
@RequestMapping("actor")
public class ActorController extends AbstractController {

	// Services

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdvertisementService	advertisementService;

	@Autowired
	private LoginService			loginService;


	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final String q) {
		final ModelAndView result;

		final Actor principal = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Collection<Actor> actors = new ArrayList<Actor>();
		if (q.equals("followers")) {
			actors = principal.getFollowers();
		} else if (q.equals("followeds")) {
			actors = principal.getFolloweds();
		}

		result = new ModelAndView("actor/list");
		result.addObject("actors", actors);
		result.addObject("principal", principal);
		result.addObject("varid", q);
		result.addObject("requestURI", "actor/list.do");

		return result;
	}

	@RequestMapping(value = "/advertisement/list", method = RequestMethod.GET)
	public ModelAndView listRegistered(@RequestParam final int q) {
		final ModelAndView result;
		final Advertisement advertisement = this.advertisementService.findOne(q);
		final Collection<Actor> actors = new ArrayList<Actor>();
		for (final Actor a : this.actorService.findAll())
			if (a.getRegistersAdvertisement().contains(advertisement))
				actors.add(a);

		result = new ModelAndView("actor/list");
		result.addObject("actors", actors);
		result.addObject("requestURI", "actor/list.do");

		return result;
	}

	// Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final Actor actor = this.actorService.create();

		result = this.createEditModelAndView(actor);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Actor actor, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			for (final ObjectError e : binding.getAllErrors())
				System.out.println(e.toString());
			result = this.createEditModelAndView(actor);
		} else
			try {
				//				if (actorService.encontrarActor(actor.getUserAccount().getUsername()).getId() > 0) {
				//
				//					binding.rejectValue("userAccount.username", "actor.surname.error", "error");
				//					throw new IllegalArgumentException();
				//				}

				actor.setIsPremium(false);
				Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				actor.getUserAccount().setPassword(encoder.encodePassword(actor.getUserAccount().getPassword(), null));
				this.actorService.save(actor);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(actor, "actor.commit.error");
			}
		return result;
	}

	// Follow other actors

	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView follow(@RequestParam final Integer q) {
		ModelAndView result;
		final Actor actor = this.actorService.findOne(q);

		try {
			this.actorService.follow(actor);
			result = new ModelAndView("redirect:/userspace/user/spaceview.do?q=" + actor.getUserSpace().getId());
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/unfollow", method = RequestMethod.GET)
	public ModelAndView unfollow(@RequestParam final Integer q) {
		ModelAndView result;
		final Actor actor = this.actorService.findOne(q);

		try {
			this.actorService.unfollow(actor);
			result = new ModelAndView("redirect:/userspace/user/spaceview.do?q=" + actor.getUserSpace().getId());
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/userspace/user/spaceview.do?q=" + actor.getUserSpace().getId());
		}

		return result;
	}

	//Premium
	@RequestMapping(value = "/premium", method = RequestMethod.GET)
	public ModelAndView premium() {
		final ModelAndView result;
		final Actor actor = this.actorService.findByPrincipal();
		result = new ModelAndView("actor/premium");
		result.addObject("actor", actor);
		//result.addObject("hacerPremium", actorService.hacerPremium());
		return result;
	}

	@RequestMapping(value = "/user/premium", method = RequestMethod.GET)
	public ModelAndView goPremium() {
		ModelAndView result;

		try {
			this.actorService.hacerPremium();
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/user/nopremium", method = RequestMethod.GET)
	public ModelAndView gonoPremium() {
		ModelAndView result;

		try {
			this.actorService.hacernoPremium();
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	// Ancillary methods

	protected ModelAndView createEditModelAndView(final Actor actor) {
		ModelAndView result;

		result = this.createEditModelAndView(actor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Actor actor, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", actor);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "actor/create.do");

		return result;
	}
}
