package pl.lodz.uni.math.engine;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import pl.lodz.uni.math.pojo.User;

@Entity
@Table(name = "User")
public class UserEngine extends User {

	private static final long serialVersionUID = 3031842147214950602L;

	public UserEngine(int id, String userName, String password, int enabled,
			Set<UserRoleEngine> userRoles, Set<NoteEngine> notes,
			Set<RateEngine> rates) {
		super(id, userName, password, enabled, userRoles, notes, rates);
	}

	public UserEngine() {
		super();
	}

}
