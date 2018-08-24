
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Advertisement extends DomainEntity {

	//Atributos

	private String				title;
	private String				description;
	private String				startDate;
	private String				endDate;
	private String				locationUrl;
	private byte[]				mainImg;
	private Double				price;
	private Actor				actorOwener;
	private Collection<Actor>	actorRegisters;


	//Getters and setters

	@NotBlank
	@Length(max = 50)
	@SafeHtml
	public String getTitle() {
		return this.title;
	}

	@NotBlank
	@Length(max = 300)
	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	@NotBlank
	@SafeHtml
	public String getStartDate() {
		return this.startDate;
	}

	@NotBlank
	@SafeHtml
	public String getEndDate() {
		return this.endDate;
	}

	@URL
	@SafeHtml
	public String getLocationUrl() {
		return this.locationUrl;
	}

	//De momento no pongo @URL ya que es una subida de imagen
	@Column(columnDefinition = "LONGBLOB")
	public byte[] getMainImg() {
		return this.mainImg;
	}

	@Range(min = 0)
	public Double getPrice() {
		return this.price;
	}

	@NotNull
	@ManyToOne
	public Actor getActorOwener() {
		return this.actorOwener;
	}

	@NotNull
	@ManyToMany
	public Collection<Actor> getActorRegisters() {
		return this.actorRegisters;
	}

	public void setActorOwener(final Actor actorOwener) {
		this.actorOwener = actorOwener;
	}

	public void setActorRegisters(final Collection<Actor> actorRegisters) {
		this.actorRegisters = actorRegisters;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}
	public void setLocationUrl(final String locationUrl) {
		this.locationUrl = locationUrl;
	}
	public void setMainImg(final byte[] mainImg) {
		this.mainImg = mainImg;
	}
	public void setPrice(final Double price) {
		this.price = price;
	}

}
