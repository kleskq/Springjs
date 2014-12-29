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
import pl.lodz.uni.math.dto.NoteRatingDto;
import pl.lodz.uni.math.dto.NoteRatingJsonDto;
import pl.lodz.uni.math.dto.RatingDto;
import pl.lodz.uni.math.engine.NoteEngine;
import pl.lodz.uni.math.engine.RateEngine;
import pl.lodz.uni.math.engine.UserEngine;
import pl.lodz.uni.math.persistence.dao.CategoryDao;
import pl.lodz.uni.math.persistence.dao.NoteDao;
import pl.lodz.uni.math.persistence.dao.RatingDao;
import pl.lodz.uni.math.persistence.dao.UserDao;
import pl.lodz.uni.math.persistence.dao.exception.BadNoteCodeException;

@Service
public class NoteServiceImpl implements NoteService {

	private static Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

	@Autowired
	private NoteDao noteDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private RatingDao ratingDao;

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

		if (noteDao.saveNote(noteEngine)) {
			return noteEngine.getLinkId();
		} else {
			return 0;
		}
	}

	@Override
	public NoteDto getNoteByCode(long code) throws BadNoteCodeException {
		NoteEngine note = noteDao.getNoteByCode(code);
		if (note != null) {
			NoteDto noteDto = new NoteDto();
			noteDto.setCreateDate(note.getCreateDate());
			noteDto.setNoteText(note.getNoteText());
			noteDto.setNoteTitle(note.getNoteTitle());
			noteDto.setUserName(note.getAuthor().getUserName());
			// log.info(note.getAvrageRating()+" note rating");
			return noteDto;
		} else {
			throw new BadNoteCodeException("No Note found with the given id");
		}

	}

	@Override
	public List<NoteLDto> getUserNotes(String userName) {
		List<NoteEngine> notes = noteDao.getUserNotes(userDao
				.getUserByName(userName));
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

	@Override
	public NoteRatingJsonDto getNotes(int pageNumber, String searchParameter,
			int pageDisplayLength, String columntToSort, String sortDirection) {
		log.info(pageNumber + " " + searchParameter + " " + pageDisplayLength);
		List<NoteRatingDto> noteList = createDto(noteDao.getNotesForRatingList(
				pageNumber, searchParameter, pageDisplayLength, columntToSort,
				sortDirection));
		NoteRatingJsonDto notesJsonObject = new NoteRatingJsonDto();
		notesJsonObject.setAaData(noteList);
		int count = noteDao.countNotes();
		if (searchParameter.equals("")) {
			notesJsonObject.setiTotalDisplayRecords(count);
		} else {
			notesJsonObject.setiTotalDisplayRecords(noteDao
					.countNotesWithParameter(searchParameter));
		}
		notesJsonObject.setiTotalRecords(count);
		return notesJsonObject;
	}

	// funkcja przerabia Engine na Dto
	private List<NoteRatingDto> createDto(List<NoteEngine> notes) {
		List<NoteRatingDto> dtos = new ArrayList<NoteRatingDto>();
		for (NoteEngine note : notes) {
			NoteRatingDto noteRatingDto = new NoteRatingDto();
			noteRatingDto.setNoteTitle(note.getNoteTitle());
			noteRatingDto.setAuthor(note.getAuthor().getUserName());
			noteRatingDto.setCategory(note.getCategory().getCategoryName());
			noteRatingDto.setCode(Long.toString(note.getLinkId()));
			noteRatingDto.setRating(Double.toString(note.getAvrageRating()));
			noteRatingDto.setCreateDate(note.getCreateDate().toString());
			dtos.add(noteRatingDto);
		}

		return dtos;
	}

}
