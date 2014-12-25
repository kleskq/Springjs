package pl.lodz.uni.math.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.lodz.uni.math.engine.NoteEngine;
import pl.lodz.uni.math.engine.RateEngine;
import pl.lodz.uni.math.engine.UserEngine;
import pl.lodz.uni.math.persistence.base.BaseDao;
import pl.lodz.uni.math.persistence.dao.exception.UpdateDeleteException;

@Repository
public class RatingDaoImpl extends BaseDao implements RatingDao {
	private static Logger log = LoggerFactory.getLogger(RatingDaoImpl.class);

	@Override
	@Transactional
	public boolean saveRating(RateEngine ratingEgine) {
		boolean success = false;
		try {
			success = updateSingleData(ratingEgine);
		} catch (UpdateDeleteException e) {
			log.error("Error saving note with id: " + ratingEgine.getRateId());
		}
		return success;
	}

	@Override
	@Transactional
	public RateEngine getRateByUserAndNote(UserEngine user, NoteEngine note) {
		EntityManager em = emf.createEntityManager();
		Query query = em
				.createQuery(
						"SELECT c FROM RateEngine c WHERE c.evaluator = :user AND c.note = :note")
				.setParameter("user", user).setParameter("note", note).setMaxResults(1);
		List<RateEngine> rate = (List<RateEngine>) loadData(RateEngine.class,
				query);

		if (rate == null || rate.isEmpty()) {
			log.info("No rate found");
			return null;
		}
		return rate.get(0);
	}

}
