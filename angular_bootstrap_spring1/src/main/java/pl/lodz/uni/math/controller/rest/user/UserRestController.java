package pl.lodz.uni.math.controller.rest.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.uni.math.dto.UserDto;
import pl.lodz.uni.math.service.UserService;

@RestController
@RequestMapping(value = "/rest")
public class UserRestController {
	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public List<UserDto> getUsers() {
		return userService.getUsers();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public boolean saveUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }
}