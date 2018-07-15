package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.PlayList;
import repositories.PlayListRepository;

@Service
@Transactional
public class PlayListService {
	
	@Autowired
	private PlayListRepository playListRepository;
	
	public PlayListService() {
		
		super();
	}

	public void delete(PlayList arg0) {
		playListRepository.delete(arg0);
	}

	public List<PlayList> findAll() {
		return playListRepository.findAll();
	}

	public PlayList findOne(Integer arg0) {
		return playListRepository.findOne(arg0);
	}

	public <S extends PlayList> S save(S arg0) {
		return playListRepository.save(arg0);
	}
	
	
	
	

}
