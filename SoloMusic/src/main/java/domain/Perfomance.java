package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Perfomance extends DomainEntity {
	
	//Atributos
	private String title;
	private String description;
	private String videoUrl;
	
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
	
	@NotBlank
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	
	

}