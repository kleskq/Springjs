package pl.lodz.uni.math.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lodz.uni.math.dto.NoteLDto;
import pl.lodz.uni.math.service.NoteService;

@Controller
public class UserPanelController {

	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "user/panel", method = RequestMethod.GET)
	public String about(Model model) {
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		List<NoteLDto> notes = noteService.getUserNotes(userName);
		model.addAttribute("notes", notes);
		return "user/userPanel";
	}
}
