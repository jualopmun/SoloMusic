
package domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	// Attributes
	// ====================================================================================
	//Actor=User
	
	
	private String name;
	private String surname;
	private String email;
	private Date birthDate;
	private Boolean isPremium;
	//Relations
	private Collection<Advertisement> ownerAdvertisement;
	private Collection<Advertisement> registersAdvertisement;
	private UserSpace userSpace;
	private Collection<Folder>	folders;
	private UserAccount			userAccount;
	private Collection<Actor> followers;
	private Collection<Actor> followeds;
	
	

	@NotNull
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull
	@NotBlank
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@NotNull
	@Email
	@NotBlank
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}
	
	@NotNull
	@OneToMany(mappedBy="actorOwener")
	public Collection<Advertisement> getOwnerAdvertisement() {
		return ownerAdvertisement;
	}

	public void setOwnerAdvertisement(Collection<Advertisement> ownerAdvertisement) {
		this.ownerAdvertisement = ownerAdvertisement;
	}
	
	@NotNull
	@ManyToMany(mappedBy="actorRegisters")
	public Collection<Advertisement> getRegistersAdvertisement() {
		return registersAdvertisement;
	}

	public void setRegistersAdvertisement(Collection<Advertisement> registersAdvertisement) {
		this.registersAdvertisement = registersAdvertisement;
	}
	
	@NotNull
	@OneToOne
	public UserSpace getUserSpace() {
		return userSpace;
	}

	public void setUserSpace(UserSpace userSpace) {
		this.userSpace = userSpace;
	}

	@NotNull
	@OneToMany
	public Collection<Folder> getFolders() {
		return this.folders;
	}

	public void setFolders(final Collection<Folder> folders) {
		this.folders = folders;
	}

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	@NotNull
	@ManyToMany(cascade=CascadeType.ALL)
	public Collection<Actor> getFollowers() {
		return followers;
	}

	public void setFollowers(Collection<Actor> followers) {
		this.followers = followers;
	}
	
	@NotNull
	@ManyToMany(cascade=CascadeType.ALL)
	public Collection<Actor> getFolloweds() {
		return followeds;
	}

	public void setFolloweds(Collection<Actor> followeds) {
		this.followeds = followeds;
	}


	
	
}
