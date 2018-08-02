
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {

	// Attributes
	// ====================================================================================
	//Actor=User

	private String						name;
	private String						surname;
	private String						email;
	private Date						birthDate;
	private Boolean						isPremium;
	//Relations
	private Collection<Advertisement>	ownerAdvertisement;
	private Collection<Advertisement>	registersAdvertisement;
	private UserSpace					userSpace;
	private Collection<Folder>			folders;
	private UserAccount					userAccount;
	private Collection<Actor>			followers;
	private Collection<Actor>			followeds;


	@NotNull
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotNull
	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@NotNull
	@Email
	@NotBlank
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	public Boolean getIsPremium() {
		return this.isPremium;
	}

	public void setIsPremium(final Boolean isPremium) {
		this.isPremium = isPremium;
	}

	@NotNull
	@OneToMany(mappedBy = "actorOwener")
	public Collection<Advertisement> getOwnerAdvertisement() {
		return this.ownerAdvertisement;
	}

	public void setOwnerAdvertisement(final Collection<Advertisement> ownerAdvertisement) {
		this.ownerAdvertisement = ownerAdvertisement;
	}

	@NotNull
	@ManyToMany(mappedBy = "actorRegisters")
	public Collection<Advertisement> getRegistersAdvertisement() {
		return this.registersAdvertisement;
	}

	public void setRegistersAdvertisement(final Collection<Advertisement> registersAdvertisement) {
		this.registersAdvertisement = registersAdvertisement;
	}

	//TODO: crear un UserSpace automáticamente con cada nuevo usuario
	//	@NotNull
	@OneToOne
	public UserSpace getUserSpace() {
		return this.userSpace;
	}

	public void setUserSpace(final UserSpace userSpace) {
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
	@Valid
	@ManyToMany(mappedBy = "followeds")
	public Collection<Actor> getFollowers() {
		return this.followers;
	}

	public void setFollowers(final Collection<Actor> followers) {
		this.followers = followers;
	}

	@NotNull
	@Valid
	@ManyToMany(cascade = CascadeType.ALL)
	public Collection<Actor> getFolloweds() {
		return this.followeds;
	}

	public void setFolloweds(final Collection<Actor> followeds) {
		this.followeds = followeds;
	}

}
