package pl.lodz.uni.math.api.contoller;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import pl.lodz.uni.math.service.NoteService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
		"classpath:WEB-INF/spring/test-application-context-config.xml",
		"classpath:WEB-INF/spring/test-security-context-config.xml",
		"classpath:WEB-INF/spring/test-persistence-context-config.xml" })
public class NoteControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private NoteService noteService;

	@Test
	public void findAll_ShouldAddTodoEntriesToModelAndRenderTodoListView()
			throws Exception {


		mockMvc.perform(get("/note/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(view().name("public/noteReader"))
				.andExpect(forwardedUrl("/WEB-INF/views/public/noteReader.jsp"))
				.andExpect(model().attribute("note", hasProperty("noteTitle", is("title1"))))
				.andExpect(
						model().attribute("note",
								hasProperty("noteText", is("text"))))
				.andExpect(
						model().attribute("note",
								hasProperty("userName", is("user"))));

		verify(noteService, times(1)).getNoteByCode(1);
	}
}
