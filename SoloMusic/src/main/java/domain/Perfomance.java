
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Perfomance extends DomainEntity {

	//Atributos
	private String	title;
	private String	description;
	private String	videoUrl;


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

	@NotBlank
	public String getVideoUrl() {
		return this.videoUrl;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public void setVideoUrl(final String videoUrl) {
		this.videoUrl = videoUrl;
	}

}
