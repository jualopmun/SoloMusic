
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class PlayList extends DomainEntity {

	//Atributos
	private String				title;
	private String				description;

	//Relations
	private Collection<Track>	tracks;


	@NotBlank
	@Length(max = 50)
	@SafeHtml
	public String getTitle() {
		return title;
	}

	@NotBlank
	@Length(max = 300)
	@SafeHtml
	public String getDescription() {
		return description;
	}

	@NotNull
	@OneToMany
	public Collection<Track> getTracks() {
		return tracks;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTracks(Collection<Track> tracks) {
		this.tracks = tracks;
	}

}
