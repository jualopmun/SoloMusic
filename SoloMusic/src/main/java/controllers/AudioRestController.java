
package controllers;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import domain.Track;
import services.TrackService;

@RestController
@RequestMapping("/audio")
public class AudioRestController {

	@Autowired
	private TrackService trackService;


	@RequestMapping(value = "/user/play/{q:.+}", method = RequestMethod.GET, consumes = {
		MediaType.ALL_VALUE
	})
	public HttpEntity<byte[]> downloadRecipientFile(@PathVariable("q") final int q) throws IOException, ServletException {

		final Track track = this.trackService.findOne(q);
		if (track == null || track.getFile() == null || track.getFile().length <= 0)
			throw new ServletException("No clip found/clip has not data, id=" + q);
		final HttpHeaders header = new HttpHeaders();

		// header.setContentType(new MediaType("audio", "mp3"));
		header.setContentType(new MediaType("audio", "vnd.mp3"));
		header.setContentLength(track.getFile().length);
		return new HttpEntity<byte[]>(track.getFile(), header);
	}

}
