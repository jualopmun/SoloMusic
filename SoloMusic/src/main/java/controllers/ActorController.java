
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import domain.Actor;
import forms.ActorRegisterForm;

@Controller
@RequestMapping("actor")
public class ActorController extends AbstractController {

	//Services

	@Autowired
	private ActorService	actorService;


	//	@Autowired
	//	private UserAccountRepository	uar;

	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final String q) {
		final ModelAndView result;
		final Actor principal = this.actorService.findByPrincipal();
		Collection<Actor> actors = new ArrayList<Actor>();
		if (q == "followers")
			actors = principal.getFollowers();
		else
			actors = principal.getFolloweds();

		result = new ModelAndView("actor/list");
		result.addObject("actors", actors);
		result.addObject("varid", q);
		result.addObject("requestURI", "actor/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final ActorRegisterForm actor = new ActorRegisterForm();

		result = this.createEditModelAndView(actor);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorRegisterForm actor, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			//			if (this.uar.findActorByUsername(actor.getUserAccount().getUsername()) != null)
			//				binding.rejectValue("userAccount.username", "acme.username.unique");
			//			if (actor.getUserAccount().getUsername().length() < 5 || actor.getUserAccount().getUsername().length() > 32)
			//				binding.rejectValue("userAccount.username", "acme.password.size");
			//			if (actor.getUserAccount().getPassword().length() < 5 || actor.getUserAccount().getPassword().length() > 32)
			//				binding.rejectValue("userAccount.password", "acme.password.size");
			//			if (!actor.getRepeatPassword().equals(actor.getPassword()))
			//				binding.rejectValue("repeatPassword", "acme.password.repeat");
			//			if (actor.getName().isEmpty())
			//				binding.rejectValue("name", "org.hibernate.validator.constraints.NotBlank.message");
			//			if (actor.getSurname().isEmpty())
			//				binding.rejectValue("surname", "org.hibernate.validator.constraints.NotBlank.message");
			//			if (actor.getEmail().isEmpty())
			//				binding.rejectValue("email", "javax.validator.constraints.email.message");
			//			if (actor.getBirthDate().toString().isEmpty())
			//				binding.rejectValue("birthDate", "acme.date.invalid");
			//			System.out.println(binding.getAllErrors().toString());
			actor.setAcceptedTerms(false);
			result = this.createEditModelAndView(actor);
		} else
			try {
				final Actor a = this.actorService.reconstruct(actor, binding);
				this.actorService.hashPassword(a);
				this.actorService.save(a);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				System.out.println(oops.toString());
				result = this.createEditModelAndView(actor, "actor.commit.error");
			}
		return result;
	}

	//Follow other actors

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

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final ActorRegisterForm actor) {
		ModelAndView result;

		result = this.createEditModelAndView(actor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorRegisterForm actor, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", actor);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "actor/create.do");

		return result;
	}
}
