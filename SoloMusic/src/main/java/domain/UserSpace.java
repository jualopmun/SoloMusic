package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class UserSpace extends DomainEntity {
	
	//Atributos
	private String title;
	private String description;
	private String profileImg;
	private String contact;
	
	//Relaciones
	private Collection<Event> events;
	private Collection<Perfomance> perfomances;
	private Collection<Donation> donations;
	private Collection<PlayList> playLists;
	
	
	//Getters
	
	@NotBlank
	@Length(max=50)
	public String getTitle() {
		return title;
	}
	
	
	@NotBlank
	@Length(max=300)
	public String getDescription() {
		return description;
	}
	
	//De momento no pongo @URL ya que es una subida de imagen
	public String getProfileImg() {
		return profileImg;
	}
	
	public String getContact() {
		return contact;
	}
	
	@NotNull
	@OneToMany
	public Collection<Event> getEvents() {
		return events;
	}
	@NotNull
	@OneToMany
	public Collection<Perfomance> getPerfomances() {
		return perfomances;
	}
	@NotNull
	@OneToMany
	public Collection<Donation> getDonations() {
		return donations;
	}
	@NotNull
	@OneToMany
	public Collection<PlayList> getPlayLists() {
		return playLists;
	}
	
	
	//Setters
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setEvents(Collection<Event> events) {
		this.events = events;
	}
	public void setPerfomances(Collection<Perfomance> perfomances) {
		this.perfomances = perfomances;
	}
	public void setDonations(Collection<Donation> donations) {
		this.donations = donations;
	}
	public void setPlayLists(Collection<PlayList> playLists) {
		this.playLists = playLists;
	}
	
	
	
	
	
	

}
