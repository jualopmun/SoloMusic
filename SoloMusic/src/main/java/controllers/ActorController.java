
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final ActorRegisterForm arf = new ActorRegisterForm();

		result = this.createEditModelAndView(arf);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorRegisterForm arf, final BindingResult binding) {
		ModelAndView result;

		if (!arf.isAcceptedTerms())
			binding.rejectValue("acceptedTerms", "acme.accepted.terms");
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
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
