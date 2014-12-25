package pl.lodz.uni.math.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lodz.uni.math.dto.NewNoteDto;
import pl.lodz.uni.math.service.CategoryService;
import pl.lodz.uni.math.service.NoteService;

@Controller
public class NoteController {
	@Autowired
	private NoteService noteService;

	public NoteService getNoteService() {
		return noteService;
	}

	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}

	@Autowired
	private CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@RequestMapping(value = { "/user" }, method = RequestMethod.GET)
	public String createNewNote(Model model) {
		model.addAttribute("command", new NewNoteDto());
		model.addAttribute("categoryList", categoryService.getCategories());
		return "user/note";
	}

	@RequestMapping(value = "/user/addNote", method = RequestMethod.POST)
	public String createNewNote(
			@ModelAttribute("command") NewNoteDto newNoteDto, Model model) {
		model.addAttribute("newNoteDto", newNoteDto);
		newNoteDto.setUserName(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		long id = noteService.saveNote(newNoteDto);

		if (id == 0) {
			return "user/note";
		} else {
			return "redirect:/note/" + id;
		}
	}
}
