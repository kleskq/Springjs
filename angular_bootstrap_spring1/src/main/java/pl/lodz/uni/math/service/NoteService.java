package pl.lodz.uni.math.service;

import java.util.List;

import pl.lodz.uni.math.dto.NewNoteDto;
import pl.lodz.uni.math.dto.NoteDto;
import pl.lodz.uni.math.dto.NoteLDto;
import pl.lodz.uni.math.dto.NoteRatingJsonDto;
import pl.lodz.uni.math.dto.RatingDto;
import pl.lodz.uni.math.persistence.dao.exception.BadNoteCodeException;

public interface NoteService {

	public long saveNote(NewNoteDto newNoteDto);
	public NoteDto getNoteByCode(long code) throws BadNoteCodeException;
	public List<NoteLDto> getUserNotes(String userName);
	public boolean saveRating(String id, String rating, String name);
	public RatingDto getRatingForNoteAndUser(long id, String name);
	public NoteRatingJsonDto getNotes(int pageNumber,String searchParameter,int pageDisplayLength, String columntToSort, String sortDirection);
}
