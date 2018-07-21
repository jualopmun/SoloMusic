package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Perfomance;
import domain.PlayList;
import domain.Track;
import javassist.runtime.Desc;
import repositories.PlayListRepository;
import security.LoginService;

@Service
@Transactional
public class PlayListService {

	@Autowired
	private PlayListRepository playListRepository;

	@Autowired
	private LoginService loginService;

	@Autowired
	private ActorService actorService;

	public PlayListService() {

		super();
	}

	public PlayList create() {
		PlayList playList = new PlayList();
		playList.setTitle(new String());
		playList.setDescription(new String());
		playList.setTracks(new ArrayList<Track>());

		return playList;
	}

	public void delete(PlayList playList) {
		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Assert.isTrue(man.getUserSpace().getPlayLists().contains(playList));
		playListRepository.delete(playList);
		man.getUserSpace().getPlayLists().remove(playList);
		actorService.save(man);
	}

	public List<PlayList> findAll() {
		return playListRepository.findAll();
	}

	public PlayList findOne(Integer arg0) {
		return playListRepository.findOne(arg0);
	}
	
	

	public boolean exists(Integer arg0) {
		return playListRepository.exists(arg0);
	}

	public PlayList save(PlayList playlist) {
		Assert.notNull(playlist);
		PlayList m = null;
		
		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if (this.exists(playlist.getId())) {
			Assert.isTrue(man.getUserSpace().getPlayLists().contains(playlist));
			m = this.findOne(playlist.getId());

		
			m.setTitle(playlist.getTitle());
			m.setDescription(playlist.getDescription());
			
			m = this.playListRepository.save(m);
		} else {
			playlist.setTracks(new ArrayList<Track>());
			m = this.playListRepository.save(playlist);
			man.getUserSpace().getPlayLists().add(m);
			actorService.save(man);

		}
		return m;
	}

}
