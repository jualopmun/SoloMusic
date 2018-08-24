
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import repositories.AdvertisementRepository;
import domain.Actor;
import domain.Advertisement;
import domain.PlayList;
import domain.Track;

@Service
@Transactional
public class AdvertisementService {

	@Autowired
	private AdvertisementRepository	advertisermentRepository;

	@Autowired
	private ActorService			actorService;


	public AdvertisementService() {
		super();
	}

	public Advertisement create() {
		final Advertisement ad = new Advertisement();

		ad.setActorOwener(this.actorService.findByPrincipal());
		ad.setActorRegisters(new ArrayList<Actor>());

		return ad;
	}

	public void delete(final Advertisement arg0) {
		this.advertisermentRepository.delete(arg0);
	}

	public boolean exists(final Integer arg0) {
		return this.advertisermentRepository.exists(arg0);
	}

	public List<Advertisement> findAll() {
		return this.advertisermentRepository.findAll();
	}

	public Advertisement findOne(final Integer arg0) {
		return this.advertisermentRepository.findOne(arg0);
	}

	public <S extends Advertisement> S save(final S arg0) {
		return this.advertisermentRepository.save(arg0);
	}

	public Advertisement register(final Advertisement a) {
		final Actor principal = this.actorService.findByPrincipal();
		a.getActorRegisters().add(principal);
		return this.save(a);
	}

	public Advertisement unregister(final Advertisement a) {
		final Actor principal = this.actorService.findByPrincipal();
		a.getActorRegisters().remove(principal);
		return this.save(a);
	}
	
	public void saveJpg( final MultipartFile file, final Integer advertisementid) {
		Advertisement advertisement = advertisermentRepository.findOne(advertisementid);
		Actor principal = this.actorService.findByPrincipal();
		try {

			advertisement.setMainImg(file.getBytes());;
			
		} catch (final Exception e) {
			advertisement = null;
		}
		

		advertisement = this.advertisermentRepository.save(advertisement);

		principal.getOwnerAdvertisement().add(advertisement);

		actorService.save(principal);

	}
	
	public List<Advertisement> advertisementSearch(String text) {
		return this.advertisermentRepository.advertisementSearch(text);
	}

}
