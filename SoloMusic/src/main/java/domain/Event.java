
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Event extends DomainEntity {

	//Atributos
	private String	title;
	private String	description;
	private String	locationUrl;
	private String	startDate;


	//Geters and setters
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

	public String getLocationUrl() {
		return this.locationUrl;
	}

	@NotBlank
	@SafeHtml
	public String getStartDate() {
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
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

}
