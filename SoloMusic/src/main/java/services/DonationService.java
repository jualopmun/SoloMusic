package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Donation;
import repositories.DonationRepository;

@Service
@Transactional
public class DonationService {
	
	@Autowired
	private DonationRepository donationRepository;
	
	public DonationService() {
		super();
	}

	public void delete(Donation arg0) {
		donationRepository.delete(arg0);
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

	public <S extends Donation> S save(S arg0) {
		return donationRepository.save(arg0);
	}
	
	

}
