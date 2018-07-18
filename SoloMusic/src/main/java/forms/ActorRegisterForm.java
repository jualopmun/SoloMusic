
package forms;


public class ActorRegisterForm {

	//Attributes

	private String	username;
	private String	password;
	private String	repeatPassword;
	private String	name;
	private String	surname;
	private String	email;
	private String	birthDate;
	private boolean	acceptedTerms;


	//Getters

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getRepeatPassword() {
		return this.repeatPassword;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getEmail() {
		return this.email;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public boolean isAcceptedTerms() {
		return this.acceptedTerms;
	}

	//Setters

	public void setUsername(final String username) {
		this.username = username;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setRepeatPassword(final String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setBirthDate(final String birthDate) {
		this.birthDate = birthDate;
	}

	public void setAcceptedTerms(final boolean acceptedTerms) {
		this.acceptedTerms = acceptedTerms;
	}
}
