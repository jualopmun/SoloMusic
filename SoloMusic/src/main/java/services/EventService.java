package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Donation;
import domain.Event;
import domain.Perfomance;
import domain.PlayList;
import domain.UserSpace;
import repositories.EventRepository;
import security.LoginService;

@Service
@Transactional
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ActorService actorService;
	
	public EventService() {
		super();
	}
	
	public Event create() {
		Event event = new Event();
		event.setTitle(new String());
		event.setDescription(new String());
		event.setLocationUrl(new String());
	
		
		return event;
		
	}
	
	public void delete(Event event) {
		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Assert.isTrue(man.getUserSpace().getEvents().contains(event));
		eventRepository.delete(event);
		man.getUserSpace().getEvents().remove(event);
		actorService.save(man);
	}

	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	public Event findOne(Integer arg0) {
		return eventRepository.findOne(arg0);
	}
	
	

	public boolean exists(Integer arg0) {
		return eventRepository.exists(arg0);
	}

	public Event  save(Event event) {
		Assert.notNull(event);
		Event m = null;
		
		Actor man = (Actor) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if (this.exists(event.getId())) {
			Assert.isTrue(man.getUserSpace().getEvents().contains(event));
			m = this.findOne(event.getId());

		
			m.setTitle(event.getTitle());
			m.setDescription(event.getDescription());
			m.setLocationUrl(event.getLocationUrl());
			m.setStartDate(event.getStartDate());
			m = this.eventRepository.save(m);
		} else {
			
			m = this.eventRepository.save(event);
			man.getUserSpace().getEvents().add(m);
			actorService.save(man);

		}
		return m;
	
	}
	
	
	

}
