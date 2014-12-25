package pl.lodz.uni.math.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import pl.lodz.uni.math.engine.UserEngine;

@MappedSuperclass
public class UserRole implements Serializable,Cloneable {

	private static final long serialVersionUID = 1459227105815818487L;

	@Id
	@GeneratedValue
	@Column(name = "UserRoleId")
	private int id;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private UserEngine userId;
	@Column(name = "Role")
	private String role;

	
	
	public UserRole() {
		super();
	}

	public UserRole(UserEngine userId, String role) {
		super();
		this.userId = userId;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserEngine getUserId() {
		return userId;
	}

	public void setUserId(UserEngine userId) {
		this.userId = userId;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		UserRole other = (UserRole) obj;
		if (id != other.id)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + userId + ", role=" + role
				+ "]";
	}

	
	

}
