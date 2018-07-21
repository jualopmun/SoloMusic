package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.PlayList;
import domain.Track;
import repositories.TrackRepository;
import security.LoginService;

@Service
@Transactional
public class TrackService {
	
	@Autowired
	private TrackRepository trackRepository;
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private PlayListService playListService;
	
	public TrackService() {
		super();
	}
	
	public Track create() {
		Track track= new Track();
		track.setTitle(new String());
		track.setDuration(new Integer(0));
		return track;
	}

	public void delete(Track track) {
		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Assert.isTrue(man.getUserSpace().getPlayLists().contains(trackRepository.comprobarTrack(track.getId())));
		trackRepository.delete(track);
		
		
	}

	public List<Track> findAll() {
		return trackRepository.findAll();
	}

	public Track findOne(Integer arg0) {
		return trackRepository.findOne(arg0);
	}

	public Track save(Track track, int q) {
		Assert.notNull(track);
		Track m = null;
		
		   PlayList playList= playListService.findOne(q);
			
			m = this.trackRepository.save(track);
			playList.getTracks().add(m);
			playListService.save(playList);

		return m;
	
	}

	public PlayList comprobarTrack(int id) {
		return trackRepository.comprobarTrack(id);
	}

	public boolean exists(Integer arg0) {
		return trackRepository.exists(arg0);
	}
	
	
	
	

}
