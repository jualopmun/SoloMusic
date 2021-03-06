
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Advertisement;
import domain.Folder;
import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository			actorRepository;

	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private LoginService			loginService;


	public ActorService() {
		super();
	}

	public Actor create() {

		// TODO: generación automática del UserSpace, Folders, etc.
		final Actor actor = new Actor();

		actor.setOwnerAdvertisement(new ArrayList<Advertisement>());
		actor.setRegistersAdvertisement(new ArrayList<Advertisement>());
		actor.setFolders(new ArrayList<Folder>());
		actor.setFolloweds(new ArrayList<Actor>());
		actor.setFollowers(new ArrayList<Actor>());

		Authority a = new Authority();
		a.setAuthority(Authority.USER);
		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));
		account.setBanned(false);
		actor.setUserAccount(account);

		return actor;
	}

	public boolean exists(final Integer arg0) {
		return this.actorRepository.exists(arg0);
	}

	public List<Actor> findAll() {
		return this.actorRepository.findAll();
	}

	public Actor findOne(final Integer arg0) {
		return this.actorRepository.findOne(arg0);
	}

	public Actor save(Actor actor) {

		return this.actorRepository.save(actor);

	}

	public void follow(final Actor a) {
		final Actor principal = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		principal.getFolloweds().add(a);
		this.save(principal);
		a.getFollowers().add(principal);
		this.save(a);
	}

	public void unfollow(final Actor a) {
		final Actor principal = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		a.getFollowers().remove(principal);
		this.save(a);
		principal.getFolloweds().remove(a);
		this.save(principal);
	}

	// Other methods

	public Actor findByPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		final Actor a = this.actorRepository.findByUserAccountId(userAccount.getId());
		return a;
	}

	public void hashPassword(final Actor a) {
		final String oldPs = a.getUserAccount().getPassword();
		final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		final String hash = encoder.encodePassword(oldPs, null);
		final UserAccount old = a.getUserAccount();
		old.setPassword(hash);
		final UserAccount newOne = this.userAccountRepository.save(old);
		a.setUserAccount(newOne);
	}

	public Actor findByUserAccountId(final int id) {
		return this.actorRepository.findByUserAccountId(id);
	}

	public Actor findByUserSpaceId(final int id) {
		return this.actorRepository.findByUserSpaceId(id);
	}

	// Hacerte premium
	public Actor hacerPremium() {
		Actor a = loginService.findActorByUsername(LoginService.getPrincipal().getId());
		a.setIsPremium(true);

		return actorRepository.save(a);

	}

	public Actor hacernoPremium() {
		Actor a = loginService.findActorByUsername(LoginService.getPrincipal().getId());
		a.setIsPremium(false);

		return actorRepository.save(a);

	}

	public Actor encontrarActor(String username) {
		return actorRepository.encontrarActor(username);
	}

}
