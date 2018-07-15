package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Event;
import repositories.EventRepository;

@Service
@Transactional
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	public EventService() {
		super();
	}
	
	public void delete(Event arg0) {
		eventRepository.delete(arg0);
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Event findOne(Integer arg0) {
		return eventRepository.findOne(arg0);
	}

	public <S extends Event> S save(S arg0) {
		return eventRepository.save(arg0);
	}
	
	
	

}
