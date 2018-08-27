
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Advertisement;
import domain.Notification;
import repositories.NotificationRepository;
import security.LoginService;

@Service
@Transactional
public class NotificationService {

	@Autowired
	private NotificationRepository	notificationRepository;

	@Autowired
	private LoginService			loginService;


	public Notification save(Notification entity) {
		Assert.notNull(entity);
		return notificationRepository.save(entity);
	}

	public Notification findOne(Integer id) {
		Assert.notNull(id);
		Assert.isTrue(notificationRepository.exists(id));
		return notificationRepository.findOne(id);
	}

	public List<Notification> findByOwner(Actor owner) {
		Assert.notNull(owner);
		return notificationRepository.findByOwnerAndViewFalse(owner);
	}

	public void createActor(Actor actor) {
		Assert.notNull(actor);
		Notification not = new Notification();

		final Actor principal = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		not.setOwner(actor);
		not.setActor(principal);
		not.setView(false);

		not = save(not);
		System.out.println(not.getId());
	}

	public void createAdvertisement(Advertisement advertisement) {
		Assert.notNull(advertisement);
		Notification not = new Notification();

		not.setOwner(advertisement.getActorOwener());
		not.setAdvertisement(advertisement);

		save(not);
	}

	public void markView(Notification notification) {
		Assert.notNull(notification);
		notification.setView(true);
		save(notification);
	}

}
