package pl.lodz.uni.math.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lodz.uni.math.dto.NewNoteDto;
import pl.lodz.uni.math.dto.NoteDto;
import pl.lodz.uni.math.dto.NoteLDto;
import pl.lodz.uni.math.dto.RatingDto;
import pl.lodz.uni.math.engine.NoteEngine;
import pl.lodz.uni.math.engine.RateEngine;
import pl.lodz.uni.math.engine.UserEngine;
import pl.lodz.uni.math.persistence.dao.CategoryDao;
import pl.lodz.uni.math.persistence.dao.NoteDao;
import pl.lodz.uni.math.persistence.dao.RatingDao;
import pl.lodz.uni.math.persistence.dao.UserDao;

@Service
public class NoteServiceImpl implements NoteService {

	private static Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

	@Autowired
	private NoteDao noteDao;

	public NoteDao getNoteDao() {
		return noteDao;
	}

	public void setNoteDao(NoteDao noteDao) {
		this.noteDao = noteDao;
	}

	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Autowired
	private RatingDao ratingDao;

	public RatingDao getRatingDao() {
		return ratingDao;
	}

	public void setRatingDao(RatingDao ratingDao) {
		this.ratingDao = ratingDao;
	}

	@Override
	public long saveNote(NewNoteDto newNoteDto) {
		NoteEngine noteEngine = new NoteEngine();
		noteEngine.setNoteTitle(newNoteDto.getNoteTitle());
		noteEngine.setNoteText(newNoteDto.getNoteText());
		noteEngine.setAuthor(userDao.getUserByName(newNoteDto.getUserName()));
		Date date = new Date();
		noteEngine.setLinkId(date.getTime());
		noteEngine.setCreateDate(date);
		noteEngine.setCategory(categoryDao.getCategoryByCategoryName(newNoteDto
				.getCategory()));

		log.info(noteEngine.getLinkId() + "");
		if (noteDao.saveNote(noteEngine)) {
			return noteEngine.getLinkId();
		} else {
			return 0;
		}
	}

	@Override
	public NoteDto getNoteByCode(long code) {
		NoteEngine note = noteDao.getNoteByCode(code);

		NoteDto noteDto = new NoteDto();
		noteDto.setCreateDate(note.getCreateDate());
		noteDto.setNoteText(note.getNoteText());
		noteDto.setNoteTitle(note.getNoteTitle());
		noteDto.setUserName(note.getAuthor().getUserName());

		return noteDto;
	}

	@Override
	public List<NoteLDto> getUserNotes(String userName) {
		List<NoteEngine> notes = noteDao.getUserNotes(userDao
				.getUserByName(userName));
		log.info(notes.size() + "");
		List<NoteLDto> noteDtos = new ArrayList<>();
		for (NoteEngine noteEngine : notes) {
			noteDtos.add(new NoteLDto(noteEngine.getNoteTitle(), noteEngine
					.getCategory().getCategoryName(), noteEngine
					.getCreateDate(), noteEngine.getLinkId()));
		}
		return noteDtos;
	}

	@Override
	public boolean saveRating(String id, String rating, String name) {
		NoteEngine note = noteDao.getNoteByCode(Long.parseLong(id));
		UserEngine user = userDao.getUserByName(name);
		RateEngine rate = ratingDao.getRateByUserAndNote(user, note);
		if (rate == null) {
			return ratingDao.saveRating(new RateEngine(
					Integer.parseInt(rating), user, note));
		} else {
			rate.setRating(Integer.parseInt(rating));
			return ratingDao.saveRating(rate);
		}

	}

	@Override
	public RatingDto getRatingForNoteAndUser(long id, String name) {
		NoteEngine note = noteDao.getNoteByCode(id);
		UserEngine user = userDao.getUserByName(name);
		RateEngine rate = ratingDao.getRateByUserAndNote(user, note);

		if (rate == null) {
			return new RatingDto();
		} else {
			return new RatingDto(String.valueOf(rate.getRating()));
		}
	}

}
