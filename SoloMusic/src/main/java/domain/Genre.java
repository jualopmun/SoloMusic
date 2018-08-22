
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Genre extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String genre;


	@NotBlank
	@SafeHtml
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	// toString ---------------------------------------------------------------

	@Override
	public String toString() {
		return "Genre [genre=" + this.genre + "]";
	}

}
