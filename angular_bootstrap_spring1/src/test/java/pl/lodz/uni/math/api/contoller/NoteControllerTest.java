package pl.lodz.uni.math.api.contoller;

import java.util.Arrays;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;
import static org.mockito.Mockito.*;
import pl.lodz.uni.math.dto.UserDto;
import pl.lodz.uni.math.service.UserService;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:WEB-INF/spring/test-application-context-config.xml",
		"classpath:WEB-INF/spring/test-security-context-config.xml",
		"classpath:WEB-INF/spring/test-persistence-context-config.xml" })
public class NoteControllerTest extends SecurityEndpointTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

		logout();
	}

	@Autowired
	private UserService userService;

	@Test
	public void getUsers() throws Exception {
		login("testadmin", "testadmin");
		UserDto user = new UserDto();
		user.setEnabled(true);
		user.setId(1);
		user.setUserName("user");

		userService = org.mockito.Mockito.mock(UserService.class);
		when(userService.getUsers()).thenReturn(Arrays.asList(user));
		mockMvc.perform(get("/rest/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].userName", is("user")))
				.andExpect(jsonPath("$[0].enabled", is(true)));
	}

	@Test
	public void getUsersAuthenticationSuccess() throws Exception {

		login("testadmin", "testadmin");

		mockMvc.perform(MockMvcRequestBuilders.get("/rest/users").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test(expected = NestedServletException.class)
	public void getUsersAuthenticationFailed() throws Exception {

		login("admin", "admin");

		mockMvc.perform(MockMvcRequestBuilders.get("/rest/users").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test(expected = NestedServletException.class)
	public void getUsersUnauthentication() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/rest/users").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}
}
