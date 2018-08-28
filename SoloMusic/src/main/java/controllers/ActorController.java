
package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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
import services.NotificationService;

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

	@Autowired
	private NotificationService		notificationService;


	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final String q) {
		ModelAndView result;
		try {
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
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/advertisement/list", method = RequestMethod.GET)
	public ModelAndView listRegistered(@RequestParam final int q) {
		ModelAndView result;
		try {
			Actor principal = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
			final Advertisement advertisement = this.advertisementService.findOne(q);
			Assert.isTrue(principal.getOwnerAdvertisement().contains(advertisement));
			final Collection<Actor> actors = new ArrayList<Actor>();
			for (final Actor a : this.actorService.findAll())
				if (a.getRegistersAdvertisement().contains(advertisement))
					actors.add(a);

			result = new ModelAndView("actor/list");
			result.addObject("actors", actors);
			result.addObject("requestURI", "actor/list.do");
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	// Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Actor actor = this.actorService.create();

			result = this.createEditModelAndView(actor);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Actor actor, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			for (ObjectError e : binding.getAllErrors()) {
				System.out.println(e.toString());
			}

			result = this.createEditModelAndView(actor);
		} else
			try {
				SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
				Date start = parser.parse(actor.getBirthdate());

				if (!start.before(new Date())) {
					binding.rejectValue("birthdate", "actor.birthdate.error", "error");
					throw new IllegalArgumentException();
				}
				if (actorService.encontrarActor(actor.getUserAccount().getUsername()) != null) {

					binding.rejectValue("userAccount.username", "actor.surname.error", "error");
					throw new IllegalArgumentException();
				}

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
			this.notificationService.createActor(actor);
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
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	//Premium
	@RequestMapping(value = "/premium", method = RequestMethod.GET)
	public ModelAndView premium() {
		ModelAndView result;
		try {
			final Actor actor = this.actorService.findByPrincipal();
			result = new ModelAndView("actor/premium");
			result.addObject("actor", actor);
			//result.addObject("hacerPremium", actorService.hacerPremium());
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}
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
