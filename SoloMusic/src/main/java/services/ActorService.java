package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Actor;
import repositories.ActorRepository;

@Service
@Transactional
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;
	
	

	public ActorService() {
		super();
	}


	public boolean exists(Integer arg0) {
		return actorRepository.exists(arg0);
	}

	public List<Actor> findAll() {
		return actorRepository.findAll();
	}

	public Actor findOne(Integer arg0) {
		return actorRepository.findOne(arg0);
	}

	public <S extends Actor> S save(S arg0) {
		return actorRepository.save(arg0);
	}
	
	

}
