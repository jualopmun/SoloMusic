package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Genre;
import repositories.GenreRepository;

@Service
@Transactional
public class GenreService {
	
	@Autowired
	private GenreRepository	genreRepository;
	
	
	public boolean exists(final Integer arg0) {
		return this.genreRepository.exists(arg0);
	}

	public List<Genre> findAll() {
		return this.genreRepository.findAll();
	}

	public Genre findOne(final Integer arg0) {
		return this.genreRepository.findOne(arg0);
	}
	


}
