package pl.lodz.uni.math.persistence.dao;

import pl.lodz.uni.math.engine.NoteEngine;
import pl.lodz.uni.math.engine.RateEngine;
import pl.lodz.uni.math.engine.UserEngine;

public interface RatingDao {

	public boolean saveRating(RateEngine ratingEgine);

	public RateEngine getRateByUserAndNote(UserEngine user, NoteEngine note);
}
