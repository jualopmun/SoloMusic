
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import domain.Actor;
import domain.PlayList;
import domain.Track;
import repositories.TrackRepository;
import security.LoginService;

@Service
@Transactional
public class TrackService {

	@Autowired
	private TrackRepository	trackRepository;

	@Autowired
	private LoginService	loginService;

	@Autowired
	private PlayListService	playListService;


	public TrackService() {
		super();
	}

	public Track create() {
		final Track track = new Track();
		track.setTitle(new String());
		track.setDuration(new Integer(0));
		return track;
	}

	public void delete(final Track track) {
		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Assert.isTrue(man.getUserSpace().getPlayLists().contains(this.trackRepository.comprobarTrack(track.getId())));
		this.trackRepository.delete(track);

	}

	public List<Track> findAll() {
		return this.trackRepository.findAll();
	}

	public Track findOne(final Integer arg0) {
		return this.trackRepository.findOne(arg0);
	}

//	public Track save(final Track track, final int q) {
//		Assert.notNull(track);
//		Track m = null;
//
//		final PlayList playList = this.playListService.findOne(q);
//
//		m = this.trackRepository.save(track);
//		playList.getTracks().add(m);
//		this.playListService.save(playList);
//
//		return m;
//
//	}

	public PlayList comprobarTrack(final int id) {
		return this.trackRepository.comprobarTrack(id);
	}

	public boolean exists(final Integer arg0) {
		return this.trackRepository.exists(arg0);
	}

	public void save(String title, String duration, MultipartFile file, Integer playlistId) {
		Track track = new Track();
		
		try {
			track.setDuration(new Integer(duration));
			track.setFile(file.getBytes());
			track.setTitle(title);
			
		}catch (Exception e) {
			track = null;
		}
		
		track = this.trackRepository.save(track);
		
		PlayList playList = this.playListService.findOne(playlistId);

		Collection<Track> tracks = playList.getTracks();
		tracks.add(track);
		playList.setTracks(tracks);
		
		this.playListService.save(playList);
		
	}

}
