package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserSpaceRepository;

@Service
@Transactional
public class UserSpaceService {
	
	@Autowired
	private UserSpaceRepository userSpaceRepository;
	
	public UserSpaceService() {
		super();
	}

	public void delete(UserSpaceRepository arg0) {
		userSpaceRepository.delete(arg0);
	}

	public List<UserSpaceRepository> findAll() {
		return userSpaceRepository.findAll();
	}

	public UserSpaceRepository findOne(Integer arg0) {
		return userSpaceRepository.findOne(arg0);
	}

	public <S extends UserSpaceRepository> S save(S arg0) {
		return userSpaceRepository.save(arg0);
	}
	
	

}
