package pl.lodz.uni.math.persistence.dao;

import pl.lodz.uni.math.engine.UserEngine;

public interface UserDao {

	public UserEngine getUserByName(String name);
}
