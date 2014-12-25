package pl.lodz.uni.math.engine;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import pl.lodz.uni.math.pojo.Note;

@Entity
@Table(name = "Note")
public class NoteEngine extends Note {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1954997127951542865L;

	public NoteEngine() {
		super();
	}

	public NoteEngine(int noteId, long linkId, Date createDate,
			String noteTitle, String noteText, UserEngine author,
			CategoryEngine category, Set<RateEngine> rates) {
		super(noteId, linkId, createDate, noteTitle, noteText, author,
				category, rates);
		// TODO Auto-generated constructor stub
	}

}
