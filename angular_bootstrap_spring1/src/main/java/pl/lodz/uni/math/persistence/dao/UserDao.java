package pl.lodz.uni.math.persistence.dao;

import java.util.List;

import pl.lodz.uni.math.engine.UserEngine;

public interface UserDao {

	public UserEngine getUserByName(String name);

	public List<UserEngine> getUsers();

	public boolean updateUser(UserEngine userByName);
}
