
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Notification extends DomainEntity {

	//Attributes

	private Actor			owner;
	private Actor			actor;
	private Advertisement	advertisement;
	private boolean			view;


	//Getters

	@ManyToOne(optional = true)
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	@ManyToOne(optional = true)
	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	@NotNull
	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	@NotNull
	@ManyToOne()
	public Actor getOwner() {
		return owner;
	}

	public void setOwner(Actor owner) {
		this.owner = owner;
	}

}
