
package controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ActorService;
import services.TrackService;
import services.UserSpaceService;
import domain.Actor;
import domain.Track;
import domain.UserSpace;

@Controller
@RequestMapping("userspace")
public class UserSpaceController extends AbstractController {

	@Autowired
	private UserSpaceService	userSpaceService;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private TrackService		trackService;


	@RequestMapping(value = "/user/view", method = RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView result;

		try {
			result = new ModelAndView("userspace/view");
			result.addObject("requestURI", "/user/view.do");
			if (LoginService.isAnyAuthenticated()) {
				final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
				result.addObject("p", man.getUserSpace());
				result.addObject("actor", man);
			}

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			final UserSpace userSpace = this.userSpaceService.create();
			result = this.createNewModelAndView(userSpace, null);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {
			final UserSpace userSpace = man.getUserSpace();
			result = this.createNewModelAndView(userSpace, null);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;
	}

	@RequestMapping(value = "/user/spaceview", method = RequestMethod.GET)
	public ModelAndView view2(@RequestParam final int q) {
		ModelAndView result;
		Actor a;

		try {
			result = new ModelAndView("userspace/view");

			if (LoginService.isAnyAuthenticated()) {
				a = this.actorService.findByUserSpaceId(q);
				final boolean followed = a.getFollowers().contains(this.actorService.findByPrincipal());
				final boolean isPrincipal = a.getId() == this.actorService.findByPrincipal().getId();
				result.addObject("followed", followed);
				result.addObject("isPrincipal", isPrincipal);
				result.addObject("a", a);
			}

			result.addObject("requestURI", "/user/view.do");

			result.addObject("p", this.userSpaceService.findOne(q));

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		try {

			result = new ModelAndView("userspace/list");
			result.addObject("requestURI", "userspace/user/list.do");
			final List<UserSpace> userspace = this.userSpaceService.findAll();
			result.addObject("userspace", userspace);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}

	@RequestMapping(value = "/user/play", method = RequestMethod.GET, produces = {
		MediaType.APPLICATION_OCTET_STREAM_VALUE
	})
	public HttpEntity<byte[]> downloadRecipientFile(@RequestParam final int q) throws IOException, ServletException {

		final Track track = this.trackService.findOne(q);
		if (track == null || track.getFile() == null || track.getFile().length <= 0)
			throw new ServletException("No clip found/clip has not data, id=" + q);
		final HttpHeaders header = new HttpHeaders();

		//header.setContentType(new MediaType("audio", "mp3"));
		header.setContentType(new MediaType("audio", "vnd.mp3"));
		header.setContentLength(track.getFile().length);
		return new HttpEntity<byte[]>(track.getFile(), header);
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final UserSpace userSpace, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createNewModelAndView(userSpace, null);
		else
			try {

				this.userSpaceService.save(userSpace);

				result = new ModelAndView("redirect:/userspace/user/view.do");

			} catch (final Throwable th) {
				th.printStackTrace();
				result = this.createNewModelAndView(userSpace, "actor.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/user/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(@RequestParam("searchTerm") final String searchTerm) {
		ModelAndView result;
		List<UserSpace> userSpacesearch = new ArrayList<UserSpace>();

		try {
			result = new ModelAndView("userspace/list");
			result.addObject("requestURI", "userspace/user/list.do");
			userSpacesearch = this.userSpaceService.userSpacesearch(searchTerm);
			result.addObject("userspace", userSpacesearch);

		} catch (final Throwable th) {
			result = new ModelAndView("redirect:/welcome/index.do"); //posible vista 404?
		}
		return result;
	}
	
	
	//Subir imagen
	
	
	
	@RequestMapping(value = "dowload/upload", method = RequestMethod.GET)
	public ModelAndView uploadImage() {
		ModelAndView result;
		Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		try {
			 UserSpace userSpace = man.getUserSpace();
			
			result = this.createNewModelAndViewUpload(userSpace, null);

		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/welcome/index.do");

		}
		return result;

	}
	
	
	
	
	@RequestMapping(value = "/view/image", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_OCTET_STREAM_VALUE
		})
		public HttpEntity<byte[]> downloadRecipientFileImage(@RequestParam  int q) throws IOException, ServletException {

			UserSpace userSpace = userSpaceService.findOne(q);
			if (userSpace == null || userSpace.getProfileImg() == null || userSpace.getProfileImg().length <= 0)
				throw new ServletException("No clip found/clip has not data, id=" + q);
			final HttpHeaders header = new HttpHeaders();
			
			//header.setContentType(new MediaType("audio", "mp3"));
			header.setContentType(new MediaType("image", "jpg"));
			header.setContentLength(userSpace.getProfileImg().length);
			
			return new HttpEntity<byte[]>(userSpace.getProfileImg(), header);
		}

	@RequestMapping(value = "/user/up", method = RequestMethod.POST)
	public ModelAndView saveJpg(@RequestParam("profileImg") final MultipartFile file) {
		ModelAndView result;

	 if (file.isEmpty()) {
			result = new ModelAndView("userspace/upload");
			result.addObject("userspace", null);
			result.addObject("message", "file.null.error");
			result.addObject("requestURI", "user/create.do");
			return result;
		}

		else if (!file.getOriginalFilename().contains(".jpg")) {
			result = new ModelAndView("userspace/upload");
			result.addObject("userspace", null);
			result.addObject("message", "file.format.error.image");
			result.addObject("requestURI", "user/create.do");
			return result;
		} else if (file.getSize() >= 268435455) {
			result = new ModelAndView("userspace/upload");
			result.addObject("userspace", null);
			result.addObject("message", "file.size.error");
			result.addObject("requestURI", "user/create.do");
			return result;

		}

		else
			try {

			   userSpaceService.saveJpg(file);

				result = new ModelAndView("redirect:/userspace/user/view.do");

			} catch (final Throwable th) {
				th.printStackTrace();
				result = new ModelAndView("userspace/upload");
				result.addObject("userspace", null);
				result.addObject("message", "actor.commit.error");
				result.addObject("requestURI", "user/create.do");
			}
		return result;
	}
	
	
	
	

	protected ModelAndView createNewModelAndView(final UserSpace userSpace, final String message) {
		ModelAndView result;

		result = new ModelAndView("userspace/create");
		result.addObject("userSpace", userSpace);

		result.addObject("message", message);
		return result;
	}
	
	protected ModelAndView createNewModelAndViewUpload(final UserSpace userSpace, final String message) {
		ModelAndView result;

		result = new ModelAndView("userspace/upload");
		result.addObject("userSpace", userSpace);

		result.addObject("message", message);
		return result;
	}

}
