
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Gift extends DomainEntity {

	//Atributos
	private String	address;
	private String	idNumber;
	private String	phone;


	//Getters and Setters

	@NotBlank
	@SafeHtml
	public String getAddress() {
		return address;
	}

	@NotBlank
	@SafeHtml
	public String getIdNumber() {
		return idNumber;
	}
	@NotBlank//+34 653 666 411 ó +34 653-666-411
	@Pattern(regexp = "^(0034|\\+34)?(\\d\\d\\d)-? ?(\\d\\d)-? ?(\\d)-? ?(\\d)-? ?(\\d\\d)$")
	public String getPhone() {
		return phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
