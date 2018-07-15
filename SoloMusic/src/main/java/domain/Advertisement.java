package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Advertisement extends DomainEntity {
	
	
	//Atributos
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private String locationUrl;
	private String mainImg;
	private Double price;
	private Actor actorOwener;
	private Collection<Actor> actorRegisters;
	
	
	//Getters and setters
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	
	
	@NotBlank
	@Length(max=300)
	public String getDescription() {
		return description;
	}
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}
	
	
	
	@URL
	public String getLocationUrl() {
		return locationUrl;
	}
	
	
	//De momento no pongo @URL ya que es una subida de imagen
	public String getMainImg() {
		return mainImg;
	}
	
	@Range(min=0)
	public Double getPrice() {
		return price;
	}
	
	@NotNull
	@ManyToOne
	public Actor getActorOwener() {
		return actorOwener;
	}
	
	@NotNull
	@ManyToMany
	public Collection<Actor> getActorRegisters() {
		return actorRegisters;
	}
	
	
	
	
	public void setActorOwener(Actor actorOwener) {
		this.actorOwener = actorOwener;
	}

	

	public void setActorRegisters(Collection<Actor> actorRegisters) {
		this.actorRegisters = actorRegisters;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setLocationUrl(String locationUrl) {
		this.locationUrl = locationUrl;
	}
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	

}
