
package services;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import domain.Actor;
import domain.Event;
import repositories.EventRepository;
import security.LoginService;

@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository	eventRepository;
	@Autowired
	private LoginService	loginService;

	@Autowired
	private ActorService	actorService;


	public EventService() {
		super();
	}

	public Event create() {
		final Event event = new Event();
		event.setTitle(new String());
		event.setDescription(new String());
		event.setLocationUrl(new String());

		return event;

	}

	public void delete(final Event event) {
		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Assert.isTrue(man.getUserSpace().getEvents().contains(event));
		this.eventRepository.delete(event);
		man.getUserSpace().getEvents().remove(event);
		this.actorService.save(man);
	}

	public List<Event> findAll() {
		return this.eventRepository.findAll();
	}

	public Event findOne(final Integer arg0) {
		return this.eventRepository.findOne(arg0);

	}

	public boolean exists(final Integer arg0) {
		return this.eventRepository.exists(arg0);
	}

	public Event save(final Event event) throws ParseException {
		Assert.notNull(event);
		Event m = null;

		final Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if (this.exists(event.getId())) {
			Assert.isTrue(man.getUserSpace().getEvents().contains(event));
			m = this.findOne(event.getId());

			m.setTitle(event.getTitle());
			m.setDescription(event.getDescription());

			m.setLocationUrl(event.getLocationUrl());
			m.setMainImg(event.getMainImg());
			m.setStartDate(event.getStartDate());
			m = this.eventRepository.save(m);
		} else {

			m = this.eventRepository.save(event);
			man.getUserSpace().getEvents().add(m);
			this.actorService.save(man);

		}
		return m;

	}

	public void saveJpg(final MultipartFile file, final Integer eventId) {
		Event event = eventRepository.findOne(eventId);
		Actor principal = this.actorService.findByPrincipal();
		try {

			event.setMainImg(file.getBytes());

		} catch (final Exception e) {
			event = null;
		}

		eventRepository.save(event);

	}

	private String formatDate(String date, boolean toBBDD) {
		String realDate = "";
		if (toBBDD) {
			// yyyy-mm-dd
			String[] dateSplit = date.split("-");
			realDate = realDate.concat(dateSplit[2]).concat("/").concat(dateSplit[1]).concat("/").concat(dateSplit[0]);
		} else {
			// dd/mm/yyyy
			String[] dateSplit = date.split("/");
			realDate = realDate.concat(dateSplit[2]).concat("-").concat(dateSplit[1]).concat("-").concat(dateSplit[1]);
		}
		return realDate;
	}

}
