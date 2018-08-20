
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PlayListRepository;
import security.LoginService;
import domain.Actor;
import domain.PlayList;
import domain.Track;

@Service
@Transactional
public class PlayListService {

	@Autowired
	private PlayListRepository	playListRepository;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private ActorService		actorService;


	public PlayListService() {

		super();
	}

	public PlayList create() {
		final PlayList playList = new PlayList();
		playList.setTitle(new String());
		playList.setDescription(new String());
		playList.setTracks(new ArrayList<Track>());

		return playList;
	}

	public void delete(final PlayList playList) {
		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Assert.isTrue(man.getUserSpace().getPlayLists().contains(playList));
		this.playListRepository.delete(playList);
		man.getUserSpace().getPlayLists().remove(playList);
		this.actorService.save(man);
	}

	public List<PlayList> findAll() {
		return this.playListRepository.findAll();
	}

	public PlayList findOne(final Integer arg0) {
		return this.playListRepository.findOne(arg0);
	}

	public boolean exists(final Integer arg0) {
		return this.playListRepository.exists(arg0);
	}

	public PlayList save(final PlayList playlist) {
		Assert.notNull(playlist);
		PlayList m = null;

		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
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
			this.actorService.save(man);

		}
		return m;
	}

}
