package pl.lodz.uni.math.dto;

import java.util.Date;

public class UserDto {
	private int id;
	private String userName;
	private boolean enabled;

	public UserDto() {
		super();
	}

	public UserDto(int id, String userName, boolean enabled) {
		super();
		this.id = id;
		this.userName = userName;
		this.enabled = enabled;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
