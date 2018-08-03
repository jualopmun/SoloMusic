
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Event extends DomainEntity {

	//Atributos
	private String	title;
	private String	description;
	private String	locationUrl;
	private Date	startDate;


	//Geters and setters
	@NotBlank
	@Length(max = 50)
	public String getTitle() {
		return this.title;
	}

	@NotBlank
	@Length(max = 300)
	public String getDescription() {
		return this.description;
	}

	public String getLocationUrl() {
		return this.locationUrl;
	}

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Future
	public Date getStartDate() {
		return this.startDate;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public void setLocationUrl(final String locationUrl) {
		this.locationUrl = locationUrl;
	}
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

}
