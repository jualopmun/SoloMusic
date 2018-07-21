package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import domain.PlayList;
import domain.Track;
import services.PlayListService;
import services.TrackService;

@Controller
@RequestMapping("track")
public class TrackController extends AbstractController {
	
	@Autowired
	private TrackService trackService;
	
	@Autowired
	private PlayListService playListService;
	
	public PlayList playList=null;
	
	@RequestMapping(value = "user/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int q) {
		ModelAndView result;

		try {
			Track track = trackService.create();
			playList=playListService.findOne(q);
			result = this.createEditModelAndView(track, null);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}
	
	
	@RequestMapping(value = "user/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int q) {
		ModelAndView result;
		try {

			Track track = trackService.findOne(q);
			trackService.delete(track);
			result = new ModelAndView("redirect:/userspace/user/view.do");
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Track track, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {

			result = this.createEditModelAndView(track, null);
		} else
			try {

				trackService.save(track,playList.getId());

				result = new ModelAndView("redirect:/userspace/user/view.do");

			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createEditModelAndView(track, "actor.commit.error");
			}
		return result;
	}

	
	
	
	
	protected ModelAndView createEditModelAndView(Track track) {
		ModelAndView result;

		result = this.createEditModelAndView(track, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Track track, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("track/create");
		result.addObject("track", track);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "user/create.do");

		return result;
	}

}
