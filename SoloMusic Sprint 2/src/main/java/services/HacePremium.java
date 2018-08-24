
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Actor;
import repositories.ActorRepository;
import security.LoginService;

@Service
@Transactional
public class HacePremium {

	@Autowired
	private static ActorRepository	actorRepository;

	@Autowired
	private static LoginService		loginService;


	// Hacerte premium
	public static Actor hacerPremium() {
		Actor a = loginService.findActorByUsername(LoginService.getPrincipal().getId());
		a.setIsPremium(true);

		return actorRepository.save(a);

	}

}
