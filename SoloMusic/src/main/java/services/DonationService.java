
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DonationRepository;
import security.LoginService;
import domain.Actor;
import domain.Donation;

@Service
@Transactional
public class DonationService {

	@Autowired
	private DonationRepository	donationRepository;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private ActorService		actorService;


	public DonationService() {
		super();
	}

	public Donation create() {
		final Donation donation = new Donation();
		donation.setTitle(new String());
		donation.setDescription(new String());
		donation.setPrice(new Double(0.));
		return donation;
	}

	public void delete(final Donation donation) {
		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Assert.isTrue(man.getUserSpace().getDonations().contains(donation));
		this.donationRepository.delete(donation);
		man.getUserSpace().getDonations().remove(donation);
		this.actorService.save(man);
	}

	public boolean exists(final Integer arg0) {
		return this.donationRepository.exists(arg0);
	}

	public List<Donation> findAll() {
		return this.donationRepository.findAll();
	}

	public Donation findOne(final Integer arg0) {
		return this.donationRepository.findOne(arg0);
	}

	public Donation save(final Donation donation) {
		Assert.notNull(donation);
		Donation m = null;

		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if (this.exists(donation.getId())) {
			Assert.isTrue(man.getUserSpace().getDonations().contains(donation));
			m = this.findOne(donation.getId());

			m.setTitle(donation.getTitle());
			m.setDescription(donation.getDescription());
			m.setPrice(donation.getPrice());

			m = this.donationRepository.save(m);
		} else {

			m = this.donationRepository.save(donation);

			man.getUserSpace().getDonations().add(m);
			this.actorService.save(man);

		}
		return m;
	}

}
