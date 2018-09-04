
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import domain.Actor;
import domain.Donation;
import domain.Event;
import domain.Genre;
import domain.Perfomance;
import domain.PlayList;
import domain.UserSpace;
import repositories.UserSpaceRepository;
import security.LoginService;

@Service
@Transactional
public class UserSpaceService {

	@Autowired
	private UserSpaceRepository	userSpaceRepository;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private ActorService		actorService;


	public UserSpaceService() {
		super();
	}

	public UserSpace create() {
		final UserSpace userSpace = new UserSpace();
		userSpace.setTitle(new String());
		userSpace.setDescription(new String());
		userSpace.setContact(new String());

		return userSpace;

	}

	public void delete(final UserSpace arg0) {
		this.userSpaceRepository.delete(arg0);
	}

	public List<UserSpace> findAll() {
		return this.userSpaceRepository.findAll();
	}

	public UserSpace findOne(final Integer arg0) {
		return this.userSpaceRepository.findOne(arg0);
	}

	public boolean exists(final Integer arg0) {
		return this.userSpaceRepository.exists(arg0);
	}

	public UserSpace save(final UserSpace userSpace) {
		Assert.notNull(userSpace);
		UserSpace m = null;
		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if (this.exists(userSpace.getId())) {
			//Assert.isTrue(man.getUserSpace().getId() == userSpace.getId());
			m = this.findOne(userSpace.getId());

			m.setTitle(userSpace.getTitle());
			m.setDescription(userSpace.getDescription());
			m.setProfileImg(userSpace.getProfileImg());
			m.setContact(userSpace.getContact());
			m.setGenre(userSpace.getGenre());
			m = this.userSpaceRepository.save(m);
		} else {
			userSpace.setPerfomances(new ArrayList<Perfomance>());
			userSpace.setDonations(new ArrayList<Donation>());
			userSpace.setEvents(new ArrayList<Event>());
			userSpace.setPlayLists(new ArrayList<PlayList>());
			m = this.userSpaceRepository.save(userSpace);
			man.setUserSpace(m);
			this.actorService.save(man);

		}
		return m;
	}

	public List<UserSpace> userSpacesearch(final String text) {
		return this.userSpaceRepository.userSpacesearch(text);
	}

	public void saveJpg(final MultipartFile file) {
		UserSpace userspace = loginService.findActorByUsername(LoginService.getPrincipal().getId()).getUserSpace();

		try {

			userspace.setProfileImg(file.getBytes());

		} catch (final Exception e) {
			userspace = null;
		}

		userSpaceRepository.save(userspace);

	}

	public List<UserSpace> userSpaceGenreSearch(Genre genre) {
		return this.userSpaceRepository.userSpaceGenreSearch(genre);
	}

}
