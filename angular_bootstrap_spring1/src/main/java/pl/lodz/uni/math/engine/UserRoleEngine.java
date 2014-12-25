package pl.lodz.uni.math.engine;

import javax.persistence.Entity;
import javax.persistence.Table;

import pl.lodz.uni.math.pojo.UserRole;

@Entity
@Table(name = "Userrole")
public class UserRoleEngine extends UserRole {

	private static final long serialVersionUID = -3791716487074193734L;

	
	public UserRoleEngine() {
		super();
	}


	public UserRoleEngine(UserEngine user, String role) {
		super(user, role);
	}


}
