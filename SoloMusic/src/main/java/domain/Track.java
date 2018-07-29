package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Lob;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Track extends DomainEntity {
	
	//Artributos
	private String title;
	private Integer duration;
	private byte[] file;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	@Lob
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	
	

}
