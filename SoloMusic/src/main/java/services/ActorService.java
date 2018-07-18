
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Actor;
import domain.Advertisement;
import domain.Folder;
import forms.ActorRegisterForm;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository			actorRepository;

	@Autowired
	private Validator				validator;

	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private UserSpaceService		userSpaceService;


	public ActorService() {
		super();
	}

	public Actor create() {
		final Authority a = new Authority();
		a.setAuthority(Authority.USER);
		final UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));
		account.setBanned(false);

		//TODO: generación automática del UserSpace, Folders, etc.
		final Actor actor = new Actor();
		actor.setUserAccount(account);
		actor.setOwnerAdvertisement(new ArrayList<Advertisement>());
		actor.setRegistersAdvertisement(new ArrayList<Advertisement>());
		actor.setFolders(new ArrayList<Folder>());
		actor.setFolloweds(new ArrayList<Actor>());
		actor.setFollowers(new ArrayList<Actor>());

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

	public <S extends Actor> S save(final S arg0) {
		return this.actorRepository.save(arg0);
	}

	public void follow(final Actor a) {
		final Actor principal = this.findByPrincipal();
		principal.getFolloweds().add(a);
		this.save(principal);
		a.getFollowers().add(principal);
		this.save(a);
	}

	public void unfollow(final Actor a) {
		final Actor principal = this.findByPrincipal();
		a.getFollowers().remove(principal);
		this.save(a);
		principal.getFolloweds().remove(a);
		this.save(principal);
	}

	//Other methods

	public Actor findByPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		final Actor a = this.actorRepository.findByUserAccountId(userAccount.getId());
		return a;
	}

	public Actor reconstruct(final ActorRegisterForm arf, final BindingResult binding) {
		Actor result;

		result = this.create();
		result.getUserAccount().setUsername(arf.getUsername());
		result.getUserAccount().setPassword(arf.getPassword());
		result.setName(arf.getName());
		result.setSurname(arf.getSurname());
		result.setEmail(arf.getEmail());

		final String[] campos = arf.getBirthDate().split("/");
		final String day = campos[0].trim();
		final String month = campos[1].trim();
		final String year = campos[2].trim();
		final DateTime dt = new DateTime(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
		result.setBirthDate(dt.toDate());

		this.validator.validate(result, binding);

		return result;
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
}
