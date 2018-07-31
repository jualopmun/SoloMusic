package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

	public PlayList playList = null;

	@RequestMapping(value = "user/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int q) {
		ModelAndView result;

		try {
			Track track = trackService.create();
			playList = playListService.findOne(q);
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
			for (PlayList playlist : playListService.findAll()) {
				if (playlist.getTracks().contains(track)) {
					Collection<Track> tracks = playlist.getTracks();
					tracks.remove(track);
					playlist.setTracks(tracks);

					playListService.save(playlist);
				}
			}

			trackService.delete(track);
			result = new ModelAndView("redirect:/userspace/user/view.do");
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public ModelAndView saveCreate(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) {
		ModelAndView result;

		if (title.isEmpty()) {
			result = new ModelAndView("track/create");
			result.addObject("track", null);
			result.addObject("message", "track.title.error");

			result.addObject("requestURI", "user/create.do");
			return result;
		} else if (file.isEmpty()) {
			result = new ModelAndView("track/create");
			result.addObject("track", null);
			result.addObject("message", "file.null.error");
			result.addObject("requestURI", "user/create.do");
			return result;
		}

		else if (!file.getOriginalFilename().contains(".mp3")) {
			result = new ModelAndView("track/create");
			result.addObject("track", null);
			result.addObject("message", "file.format.error");
			result.addObject("requestURI", "user/create.do");
			return result;
		} else if (file.getSize() >= 268435455) {
			result = new ModelAndView("track/create");
			result.addObject("track", null);
			result.addObject("message", "file.size.error");
			result.addObject("requestURI", "user/create.do");
			return result;

		}

		else
			try {

				trackService.save(title, file, playList.getId());

				result = new ModelAndView("redirect:/userspace/user/view.do");

			} catch (final Throwable th) {
				th.printStackTrace();
				result = new ModelAndView("track/create");
				result.addObject("track", null);
				result.addObject("message", "actor.commit.error");
				result.addObject("requestURI", "user/create.do");
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
