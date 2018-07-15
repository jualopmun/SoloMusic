package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Advertisement;
import repositories.AdvertisermentRepository;

@Service
@Transactional
public class AdvertisementService {
	
	@Autowired
	private AdvertisermentRepository advertisermentRepository;
	
    public AdvertisementService() {
		super();
	}


	public void delete(Advertisement arg0) {
		advertisermentRepository.delete(arg0);
	}

	public boolean exists(Integer arg0) {
		return advertisermentRepository.exists(arg0);
	}

	public List<Advertisement> findAll() {
		return advertisermentRepository.findAll();
	}

	public Advertisement findOne(Integer arg0) {
		return advertisermentRepository.findOne(arg0);
	}

	public <S extends Advertisement> S save(S arg0) {
		return advertisermentRepository.save(arg0);
	}
	
	

}
