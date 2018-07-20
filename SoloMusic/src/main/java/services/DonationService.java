package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Donation;
import domain.Event;
import domain.Gift;
import repositories.DonationRepository;
import security.LoginService;

@Service
@Transactional
public class DonationService {

	@Autowired
	private DonationRepository donationRepository;

	@Autowired
	private LoginService loginService;

	@Autowired
	private ActorService actorService;

	public DonationService() {
		super();
	}
	
	public Donation create() {
		Donation donation= new Donation();
		donation.setTitle(new String());
		donation.setDescription(new String());
		donation.setPrice(new Double(0.));
		return donation;
	}

	public void delete(Donation donation) {
		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Assert.isTrue(man.getUserSpace().getDonations().contains(donation));
		donationRepository.delete(donation);
		man.getUserSpace().getDonations().remove(donation);
		actorService.save(man);
	}

	public boolean exists(Integer arg0) {
		return donationRepository.exists(arg0);
	}

	public List<Donation> findAll() {
		return donationRepository.findAll();
	}

	public Donation findOne(Integer arg0) {
		return donationRepository.findOne(arg0);
	}

	public Donation save(Donation donation) {
		Assert.notNull(donation);
		Donation m = null;

		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
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
			actorService.save(man);

		}
		return m;
	}

}
