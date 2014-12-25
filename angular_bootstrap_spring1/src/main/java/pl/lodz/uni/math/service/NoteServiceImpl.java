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
import pl.lodz.uni.math.engine.NoteEngine;
import pl.lodz.uni.math.persistence.dao.CategoryDao;
import pl.lodz.uni.math.persistence.dao.NoteDao;
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
}
