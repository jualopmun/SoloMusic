
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.UserSpaceRepository;
import domain.UserSpace;

@Service
@Transactional
public class UserSpaceService {

	@Autowired
	private UserSpaceRepository	userSpaceRepository;


	public UserSpaceService() {
		super();
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

	public <S extends UserSpace> S save(final S arg0) {
		return this.userSpaceRepository.save(arg0);
	}

}
