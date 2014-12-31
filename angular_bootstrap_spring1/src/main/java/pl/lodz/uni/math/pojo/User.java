package pl.lodz.uni.math.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import pl.lodz.uni.math.engine.NoteEngine;
import pl.lodz.uni.math.engine.RateEngine;
import pl.lodz.uni.math.engine.UserEngine;
import pl.lodz.uni.math.engine.UserRoleEngine;

@MappedSuperclass
public class User implements Serializable, Cloneable {

	private static final long serialVersionUID = 3904900191776469823L;

	@Id
	@GeneratedValue
	@Column(name = "UserId")
	private int id;
	@Column(name = "UserName", unique = true)
	private String userName;
	@Column(name = "Password")
	private String password;
	@Column(name = "Enabled")
	private int enabled;
	@OneToMany(mappedBy = "userId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<UserRoleEngine> userRoles;
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<NoteEngine> notes;
	@OneToMany(mappedBy = "evaluator", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<RateEngine> rates;

	public User() {
		super();
	}

	public User(int id, String userName, String password, int enabled,
			Set<UserRoleEngine> userRoles, Set<NoteEngine> notes,
			Set<RateEngine> rates) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.userRoles = userRoles;
		this.notes = notes;
		this.rates = rates;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Set<UserRoleEngine> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoleEngine> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<NoteEngine> getNotes() {
		return notes;
	}

	public void setNotes(Set<NoteEngine> notes) {
		this.notes = notes;
	}

	public Set<RateEngine> getRates() {
		return rates;
	}

	public void setRates(Set<RateEngine> rates) {
		this.rates = rates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + enabled;
		result = prime * result + id;
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (enabled != other.enabled)
			return false;
		if (id != other.id)
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", enabled=" + enabled + ", userRoles="
				+ userRoles + ", notes=" + notes + ", rates=" + rates + "]";
	}

	@Override
	public UserEngine clone() {
		return new UserEngine(id, userName, password, enabled, userRoles,
				notes, rates);
	}
}
