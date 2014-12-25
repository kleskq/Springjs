package pl.lodz.uni.math.service;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.lodz.uni.math.engine.UserEngine;
import pl.lodz.uni.math.engine.UserRoleEngine;
import pl.lodz.uni.math.persistence.dao.UserDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		UserEngine domainUser = userDao.getUserByName(login);
		if (domainUser == null) {
			throw new UsernameNotFoundException("login");
		}
		boolean enabled;
		if (domainUser.getEnabled() == 1)
			enabled = true;
		else
			enabled = false;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new User(domainUser.getUserName(), domainUser.getPassword(),
				enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, getAuthorities(domainUser.getUserRoles()));
	}

	public Collection<? extends GrantedAuthority> getAuthorities(
			Set<UserRoleEngine> set) {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		for (UserRoleEngine userRole : set) {
			authList.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return authList;
	}

}
