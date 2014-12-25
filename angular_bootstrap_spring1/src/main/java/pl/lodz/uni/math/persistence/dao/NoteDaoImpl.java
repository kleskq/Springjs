package pl.lodz.uni.math.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.lodz.uni.math.engine.NoteEngine;
import pl.lodz.uni.math.engine.UserEngine;
import pl.lodz.uni.math.persistence.base.BaseDao;
import pl.lodz.uni.math.persistence.dao.exception.UpdateDeleteException;

@Repository
public class NoteDaoImpl extends BaseDao implements NoteDao {

	private static Logger log = LoggerFactory.getLogger(NoteDaoImpl.class);

	@Override
	@Transactional
	public boolean saveNote(NoteEngine noteEngine) {
		boolean success = false;

		try {
			success = updateSingleData(noteEngine);
		} catch (UpdateDeleteException e) {
			log.error("Error saving note with id: " + noteEngine.getNoteId());
		}
		log.info(noteEngine.getNoteTitle() + " " + noteEngine.getNoteText()
				+ " user: " + noteEngine.getAuthor().getUserName() + " "
				+ noteEngine.getAuthor().getId());
		return success;
	}

	@Transactional(readOnly = true)
	@Override
	public List<NoteEngine> getNotes() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT c FROM NoteEngine c");

		List<NoteEngine> notes = (List<NoteEngine>) loadData(NoteEngine.class,
				query);

		if (notes == null || notes.isEmpty()) {
			log.info("No notes found");
		}
		return notes;
	}

	@Transactional(readOnly = true)
	@Override
	public NoteEngine getNoteByCode(long code) {
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createQuery(
						"SELECT c FROM NoteEngine c WHERE c.linkId = :code")
				.setParameter("code", code).setMaxResults(1);
		List<NoteEngine> notes = (List<NoteEngine>) loadData(NoteEngine.class,
				query);

		if (notes == null || notes.isEmpty()) {
			log.info("No notes found");
			return null;
		}
		return notes.get(0);
	}

	@Transactional(readOnly = true)
	@Override
	public List<NoteEngine> getUserNotes(UserEngine user) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(
				"SELECT c FROM NoteEngine c WHERE c.author in :user")
				.setParameter("user", user);
		List<NoteEngine> notes = (List<NoteEngine>) loadData(NoteEngine.class,
				query);

		if (notes == null || notes.isEmpty()) {
			log.info("No notes found");
			return new ArrayList<NoteEngine>();
		}
		return notes;

	}

}
