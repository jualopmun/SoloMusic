package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Gift;
import repositories.GiftRepository;

@Service
@Transactional
public class GiftService {
	
	@Autowired
	private GiftRepository giftRepository;
	
	public GiftService() {
		super();
	}

	public void delete(Gift arg0) {
		giftRepository.delete(arg0);
	}

	public boolean exists(Integer arg0) {
		return giftRepository.exists(arg0);
	}

	public List<Gift> findAll() {
		return giftRepository.findAll();
	}

	public Gift findOne(Integer arg0) {
		return giftRepository.findOne(arg0);
	}

	public <S extends Gift> S save(S arg0) {
		return giftRepository.save(arg0);
	}
	
	
	

}
