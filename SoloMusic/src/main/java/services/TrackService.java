package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Track;
import repositories.TrackRepository;

@Service
@Transactional
public class TrackService {
	
	@Autowired
	private TrackRepository trackRepository;
	
	public TrackService() {
		super();
	}

	public void delete(Track arg0) {
		trackRepository.delete(arg0);
	}

	public List<Track> findAll() {
		return trackRepository.findAll();
	}

	public Track findOne(Integer arg0) {
		return trackRepository.findOne(arg0);
	}

	public <S extends Track> S save(S arg0) {
		return trackRepository.save(arg0);
	}
	
	

}
