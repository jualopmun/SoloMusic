
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PerformanceRepository;
import security.LoginService;
import domain.Actor;
import domain.Perfomance;

@Service
@Transactional
public class PerformanceService {

	@Autowired
	private PerformanceRepository	performanceRepository;

	@Autowired
	private LoginService			loginService;

	@Autowired
	private ActorService			actorService;


	public PerformanceService() {
		super();
	}

	public Perfomance create() {

		final Perfomance perfomance = new Perfomance();
		perfomance.setTitle(new String());
		perfomance.setDescription(new String());
		perfomance.setVideoUrl(new String());

		return perfomance;
	}

	public void delete(final Perfomance perfomance) {
		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Assert.isTrue(man.getUserSpace().getPerfomances().contains(perfomance));
		this.performanceRepository.delete(perfomance);
		man.getUserSpace().getPerfomances().remove(perfomance);
		this.actorService.save(man);
	}

	public boolean exists(final Integer arg0) {
		return this.performanceRepository.exists(arg0);
	}

	public List<Perfomance> findAll() {
		return this.performanceRepository.findAll();
	}

	public Perfomance findOne(final Integer arg0) {
		return this.performanceRepository.findOne(arg0);
	}

	public Perfomance save(final Perfomance perfomance) {
		Assert.notNull(perfomance);
		Perfomance m = null;

		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
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
			this.actorService.save(man);

		}
		return m;
	}

}
