
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserSpaceRepository;
import security.LoginService;
import domain.Actor;

import domain.Donation;
import domain.Event;

import domain.Perfomance;
import domain.PlayList;
import domain.UserSpace;

@Service
@Transactional
public class UserSpaceService {

	@Autowired
	private UserSpaceRepository userSpaceRepository;

	@Autowired
	private LoginService loginService;

	@Autowired
	private ActorService actorService;

	public UserSpaceService() {
		super();
	}

	public  UserSpace create() {
		UserSpace userSpace = new UserSpace();
		userSpace.setTitle(new String());
		userSpace.setDescription(new String());
		userSpace.setProfileImg(new String());
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

	public boolean exists(Integer arg0) {
		return userSpaceRepository.exists(arg0);
	}

	public UserSpace save(UserSpace userSpace) {
		Assert.notNull(userSpace);
		UserSpace m = null;
		System.out.println(userSpace.getContact());
		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if (this.exists(userSpace.getId())) {

			m = this.findOne(userSpace.getId());

		
			m.setTitle(userSpace.getTitle());
			m.setDescription(userSpace.getDescription());
			m.setProfileImg(userSpace.getProfileImg());
			m.setContact(userSpace.getContact());
			m = this.userSpaceRepository.save(m);
		} else {
			userSpace.setPerfomances(new ArrayList<Perfomance>());
			userSpace.setDonations(new ArrayList<Donation>());
			userSpace.setEvents(new ArrayList<Event>());
			userSpace.setPlayLists(new ArrayList<PlayList>());
			m = this.userSpaceRepository.save(userSpace);
			man.setUserSpace(m);
			actorService.save(man);

		}
		return m;
	}
}
