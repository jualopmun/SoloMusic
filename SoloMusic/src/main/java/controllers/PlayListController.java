
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PlayListService;
import domain.PlayList;

@Controller
@RequestMapping("playlist")
public class PlayListController extends AbstractController {

	@Autowired
	private PlayListService	playListService;


	@RequestMapping(value = "user/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		try {
			final PlayList playList = this.playListService.create();
			result = this.createEditModelAndView(playList, null);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "user/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int q) {
		ModelAndView result;
		try {

			final PlayList playList = this.playListService.findOne(q);
			this.playListService.delete(playList);
			result = new ModelAndView("redirect:/userspace/user/view.do");
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final PlayList playList, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(playList, null);
		else
			try {

				this.playListService.save(playList);

				result = new ModelAndView("redirect:/userspace/user/view.do");

			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createEditModelAndView(playList, "actor.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final PlayList playList) {
		ModelAndView result;

		result = this.createEditModelAndView(playList, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final PlayList playList, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("playlist/create");
		result.addObject("playList", playList);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "user/create.do");

		return result;
	}

}
