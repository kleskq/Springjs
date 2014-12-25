package pl.lodz.uni.math.controllers.pub;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.lodz.uni.math.controllers.user.NoteController;
import pl.lodz.uni.math.dto.NoteDto;
import pl.lodz.uni.math.dto.NoteLDto;
import pl.lodz.uni.math.dto.RatingDto;
import pl.lodz.uni.math.service.NoteService;

@Controller
public class PublicNoteController {

	private static final Logger logger = LoggerFactory
			.getLogger(NoteController.class);

	@Autowired
	private NoteService noteService;

	public NoteService getNoteService() {
		return noteService;
	}

	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}

	@RequestMapping(value = "/note/{id:.+}", method = RequestMethod.GET)
	public ModelAndView showNote(@PathVariable("id") long id, Locale locale) {
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		logger.debug("Note id " + id);
		NoteDto note = noteService.getNoteByCode(id);
		String date = dateFormat.format(note.getCreateDate());
		ModelAndView model = new ModelAndView();
		model.setViewName("public/noteReader");
		model.addObject("note", note);
		model.addObject("date", date);
		RatingDto rate = noteService.getRatingForNoteAndUser(id,
				SecurityContextHolder.getContext().getAuthentication()
						.getName());
		model.addObject("command", rate);
		return model;

	}

	@RequestMapping(value = "/noteRate", method = RequestMethod.POST)
	public ModelAndView rateNote(
			@ModelAttribute("command") RatingDto ratingDto,
			HttpServletRequest request) {
		logger.info(ratingDto.getRating());
		String referer = request.getHeader("Referer");
		String id = referer.substring(referer.lastIndexOf('/') + 1);
		noteService.saveRating(id, ratingDto.getRating(), SecurityContextHolder
				.getContext().getAuthentication().getName());
		return new ModelAndView("redirect:/note/" + id);
	}

}
