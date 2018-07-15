package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Donation extends DomainEntity{
	
	//Atributos
	private String title;
	private String description;
	private Double price;
	
	//Relation
	private Gift offersGift;
	
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
	
	@Range(min=0)
	public Double getPrice() {
		return price;
	}
	
	@NotNull
	@OneToOne
	public Gift getOffersGift() {
		return offersGift;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setOffersGift(Gift offersGift) {
		this.offersGift = offersGift;
	}
	
	
	
	

}
