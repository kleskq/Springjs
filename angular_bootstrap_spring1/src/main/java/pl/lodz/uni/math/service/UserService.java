package pl.lodz.uni.math.service;

import java.util.List;

import pl.lodz.uni.math.dto.UserDto;

public interface UserService {

	public List<UserDto> getUsers();

	public boolean updateUser(UserDto userDto);
}
