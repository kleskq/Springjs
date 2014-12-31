package pl.lodz.uni.math.persistence.dao;

import java.util.List;

import pl.lodz.uni.math.engine.NoteEngine;
import pl.lodz.uni.math.engine.UserEngine;

public interface NoteDao {

	public boolean saveNote(NoteEngine noteEngine);

	public List<NoteEngine> getNotes();

	public NoteEngine getNoteByCode(long code);

	public List<NoteEngine> getUserNotes(UserEngine userName);

	public int countNotes(boolean todaysNotes);

	public List<NoteEngine> getNotesForRatingList(int pageNumber,
			String searchParameter, int pageDisplayLength, String columntToSort, String sortDirection);

	public int countNotesWithParameter(String searchParameter);

}
