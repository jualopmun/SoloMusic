
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Donation;
import services.DonationService;

@Controller
@RequestMapping("donation")
public class DonationController extends AbstractController {

	@Autowired
	private DonationService donationService;


	@RequestMapping(value = "user/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		try {
			Donation donation = donationService.create();
			result = this.createEditModelAndView(donation, null);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "user/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int q) {
		ModelAndView result;

		try {

			Donation donation = donationService.findOne(q);

			result = this.createEditModelAndView(donation, null);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "user/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int q) {
		ModelAndView result;
		try {

			Donation donation = donationService.findOne(q);
			donationService.delete(donation);
			result = new ModelAndView("redirect:/userspace/user/view.do");
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Donation donation, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {

			result = this.createEditModelAndView(donation, null);
		} else
			try {

				donationService.save(donation);

				result = new ModelAndView("redirect:/userspace/user/view.do");

			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createEditModelAndView(donation, "actor.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(Donation donation) {
		ModelAndView result;

		result = this.createEditModelAndView(donation, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Donation donation, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("donation/create");
		result.addObject("donation", donation);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "user/create.do");

		return result;
	}

}
