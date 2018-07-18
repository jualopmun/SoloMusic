
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccountRepository;
import services.ActorService;
import domain.Actor;
import forms.ActorRegisterForm;

@Controller
@RequestMapping("actor")
public class ActorController extends AbstractController {

	//Services

	@Autowired
	private ActorService			actorService;

	@Autowired
	private UserAccountRepository	uar;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Actor> actors = this.actorService.findAll();

		result = new ModelAndView("actor/list");
		result.addObject("actors", actors);
		result.addObject("requestURI", "actor/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final ActorRegisterForm arf = new ActorRegisterForm();

		result = this.createEditModelAndView(arf);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final ActorRegisterForm arf, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			if (this.uar.findActorByUsername(arf.getUsername()) != null)
				binding.rejectValue("username", "acme.username.unique");
			if (arf.getUsername().length() < 5 || arf.getUsername().length() > 32)
				binding.rejectValue("username", "acme.password.size");
			if (arf.getPassword().length() < 5 || arf.getPassword().length() > 32)
				binding.rejectValue("password", "acme.password.size");
			if (!arf.getRepeatPassword().equals(arf.getPassword()))
				binding.rejectValue("repeatPassword", "acme.password.repeat");
			if (arf.getName().isEmpty())
				binding.rejectValue("name", "org.hibernate.validator.constraints.NotBlank.message");
			if (arf.getSurname().isEmpty())
				binding.rejectValue("surname", "org.hibernate.validator.constraints.NotBlank.message");
			if (arf.getEmail().isEmpty())
				binding.rejectValue("email", "javax.validator.constraints.email.message");
			if (arf.getBirthDate().isEmpty())
				binding.rejectValue("birthDate", "acme.date.invalid");
			System.out.println(binding.getAllErrors().toString());
			arf.setAcceptedTerms(false);
			result = this.createEditModelAndView(arf, "actor.commit.error");
		} else
			try {
				final Actor actor = this.actorService.reconstruct(arf, binding);
				this.actorService.hashPassword(actor);
				this.actorService.save(actor);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				System.out.println(oops.toString());
				result = this.createEditModelAndView(arf, "actor.commit.error");
			}
		return result;
	}

	//Follow other actors

	@RequestMapping(value = "/follow", method = RequestMethod.GET)
	public ModelAndView follow(@RequestParam final Integer varId) {
		final ModelAndView result;

		final Actor actor = this.actorService.findOne(varId);

		this.actorService.follow(actor);
		result = new ModelAndView("redirect:list.do");

		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final ActorRegisterForm arf) {
		ModelAndView result;

		result = this.createEditModelAndView(arf, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorRegisterForm arf, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("arf", arf);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "actor/create.do");

		return result;
	}
}
