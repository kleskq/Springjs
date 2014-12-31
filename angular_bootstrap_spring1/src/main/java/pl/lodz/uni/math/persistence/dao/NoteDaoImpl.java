package pl.lodz.uni.math.persistence.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
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

	// columns sort map
	private static Map<Integer, String> columns = new HashMap<>();

	static {
		columns.put(0, "n.noteTitle");
		columns.put(1, "n.category.categoryName");
		columns.put(2, "n.author.userName");
		columns.put(3, "n.createDate");
		columns.put(4, "n.avrageRating");
	}

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

	@Override
	@Transactional(readOnly = true)
	public int countNotes(boolean todaysNotes) {
		EntityManager em = emf.createEntityManager();
		Query query = null;
		if (!todaysNotes) {
			query = em.createQuery("SELECT COUNT(n) FROM NoteEngine n");
		}
		else{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date maxModDate = cal.getTime();
			query = em.createQuery("SELECT COUNT(n) FROM NoteEngine n WHERE n.createDate >= :maxModDate").setParameter("maxModDate", maxModDate);
		}
		int count = (int) countData(query);

		return count;
	}

	@Override
	@Transactional(readOnly = true)
	public List<NoteEngine> getNotesForRatingList(int pageNumber,
			String searchParameter, int pageDisplayLength,
			String columntToSort, String sortDirection) {

		int start = ((pageNumber - 1) * pageDisplayLength);
		EntityManager em = emf.createEntityManager();

		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT n FROM NoteEngine n ");

		if (!searchParameter.equals("")) {
			queryString.append("WHERE n.noteTitle LIKE :searchParameter ");
		}
		if (!columntToSort.equals("")) {
			queryString.append("order by ")
					.append(columns.get(Integer.parseInt(columntToSort)))
					.append(" ").append(sortDirection);
		}
		Query query = em.createQuery(queryString.toString());
		if (!searchParameter.equals("")) {
			query.setParameter("searchParameter", "%" + searchParameter + "%");
		}

		query.setFirstResult(start);
		query.setMaxResults(pageDisplayLength);

		List<NoteEngine> notes = (List<NoteEngine>) loadData(NoteEngine.class,
				query);
		log.info(notes.size() + "");
		if (notes == null || notes.isEmpty()) {
			log.info("No notes found");
			return new ArrayList<NoteEngine>();
		}
		return notes;
	}

	@Override
	@Transactional(readOnly = true)
	public int countNotesWithParameter(String searchParameter) {
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createQuery(
						"SELECT COUNT(n) FROM NoteEngine n WHERE n.noteTitle LIKE :searchParameter")
				.setParameter("searchParameter", "%" + searchParameter + "%");
		int count = (int) countData(query);

		return count;
	}

}
