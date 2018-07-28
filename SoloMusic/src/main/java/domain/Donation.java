
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Donation extends DomainEntity {

	//Atributos
	private String	title;
	private String	description;
	private Double	price;

	//Relation
	private Gift	offersGift;


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

	@Range(min = 0)
	public Double getPrice() {
		return this.price;
	}

	@OneToOne
	public Gift getOffersGift() {
		return this.offersGift;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	public void setOffersGift(final Gift offersGift) {
		this.offersGift = offersGift;
	}

}
