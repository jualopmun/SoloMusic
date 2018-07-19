package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Event;
import domain.Perfomance;
import repositories.PerformanceRepository;
import security.LoginService;

@Service
@Transactional
public class PerformanceService {
	
	@Autowired
	private PerformanceRepository performanceRepository;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ActorService actorService;
	
	public PerformanceService() {
		super();
	}
	
	
	public Perfomance create() {
		
		Perfomance perfomance= new Perfomance();
		perfomance.setTitle(new String());
		perfomance.setDescription(new String());
		perfomance.setVideoUrl(new String());
		
		return perfomance;
	}

	public void delete(Perfomance perfomance) {
		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Assert.isTrue(man.getUserSpace().getPerfomances().contains(perfomance));
		performanceRepository.delete(perfomance);
		man.getUserSpace().getPerfomances().remove(perfomance);
		actorService.save(man);
	}

	public boolean exists(Integer arg0) {
		return performanceRepository.exists(arg0);
	}

	public List<Perfomance> findAll() {
		return performanceRepository.findAll();
	}

	public Perfomance findOne(Integer arg0) {
		return performanceRepository.findOne(arg0);
	}

	public Perfomance save(Perfomance perfomance) {
		Assert.notNull(perfomance);
		Perfomance m = null;
		
		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if (this.exists(perfomance.getId())) {
			Assert.isTrue(man.getUserSpace().getPerfomances().contains(perfomance));
			m = this.findOne(perfomance.getId());

		
			m.setTitle(perfomance.getTitle());
			m.setDescription(perfomance.getDescription());
			m.setVideoUrl(perfomance.getVideoUrl());
			m = this.performanceRepository.save(m);
		} else {
			
			m = this.performanceRepository.save(perfomance);
			man.getUserSpace().getPerfomances().add(m);
			actorService.save(man);

		}
		return m;
	}
	
	

}
