
package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Notification;
import security.LoginService;
import services.NotificationService;

@Controller
@RequestMapping("/notification")
public class NotificationController extends AbstractController {

	@Autowired
	NotificationService	notificationService;
	@Autowired
	LoginService		loginService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		try {
			final Actor principal = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
			List<Notification> notifications = notificationService.findByOwner(principal);

			result = new ModelAndView("notification/list");
			result.addObject("notifications", notifications);
			result.addObject("principal", principal);
			result.addObject("requestURI", "notification/list.do");
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int q) {
		ModelAndView result;
		try {

			Notification notificacion = this.notificationService.findOne(q);
			this.notificationService.markView(notificacion);

			result = new ModelAndView("redirect:/notification/list.do");
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/notification/list.do");
		}

		return result;
	}

	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public Boolean notifications() {
		try {
			final Actor principal = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
			List<Notification> notifications = notificationService.findByOwner(principal);

			return !notifications.isEmpty();
		} catch (final Throwable e) {
			return false;
		}

	}

}
