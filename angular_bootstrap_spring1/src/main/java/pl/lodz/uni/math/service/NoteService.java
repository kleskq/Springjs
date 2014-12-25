package pl.lodz.uni.math.service;

import java.util.List;

import pl.lodz.uni.math.dto.NewNoteDto;
import pl.lodz.uni.math.dto.NoteDto;
import pl.lodz.uni.math.dto.NoteLDto;
import pl.lodz.uni.math.dto.RatingDto;

public interface NoteService {

	public long saveNote(NewNoteDto newNoteDto);
	public NoteDto getNoteByCode(long code);
	public List<NoteLDto> getUserNotes(String userName);
	public boolean saveRating(String id, String rating, String name);
	public RatingDto getRatingForNoteAndUser(long id, String name);
}
