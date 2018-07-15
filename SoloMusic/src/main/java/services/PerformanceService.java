package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Perfomance;
import repositories.PerformanceRepository;

@Service
@Transactional
public class PerformanceService {
	
	@Autowired
	private PerformanceRepository performanceRepository;
	
	public PerformanceService() {
		super();
	}

	public void delete(Perfomance arg0) {
		performanceRepository.delete(arg0);
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

	public <S extends Perfomance> S save(S arg0) {
		return performanceRepository.save(arg0);
	}
	
	

}
