package pl.lodz.uni.math.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lodz.uni.math.dto.UserDto;
import pl.lodz.uni.math.engine.UserEngine;
import pl.lodz.uni.math.persistence.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserDto> getUsers() {
		List<UserEngine> users = userDao.getUsers();
		List<UserDto> userResoult = new ArrayList<UserDto>();
		for (UserEngine userEngine : users) {
			UserDto userDto = new UserDto();
			userDto.setId(userEngine.getId());
			if (userEngine.getEnabled() == 1) {
				userDto.setEnabled(true);
			} else {
				userDto.setEnabled(false);
			}

			userDto.setUserName(userEngine.getUserName());
			userResoult.add(userDto);
		}
		return userResoult;

	}

	@Override
	public boolean updateUser(UserDto userDto) {
		return userDao.updateUser(userDao.getUserByName(userDto.getUserName()));
	}

}
