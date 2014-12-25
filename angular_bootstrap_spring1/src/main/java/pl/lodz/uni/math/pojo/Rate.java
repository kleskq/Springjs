package pl.lodz.uni.math.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import pl.lodz.uni.math.engine.UserEngine;

@MappedSuperclass
public class Rate implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "RateId")
	private int rateId;
	@Column(name = "Rating")
	private int rating; // 1 - 5
	@ManyToOne
	@JoinColumn(name = "UserId")
	private UserEngine evaluator;
	@ManyToOne
	@JoinColumn(name = "NoteId")
	private UserEngine note;

	public int getRateId() {
		return rateId;
	}

	public void setRateId(int rateId) {
		this.rateId = rateId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public UserEngine getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(UserEngine evaluator) {
		this.evaluator = evaluator;
	}

	public UserEngine getNote() {
		return note;
	}

	public void setNote(UserEngine note) {
		this.note = note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rateId;
		result = prime * result + rating;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rate other = (Rate) obj;
		if (rateId != other.rateId)
			return false;
		if (rating != other.rating)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rate [rateId=" + rateId + ", rating=" + rating + ", evaluator="
				+ evaluator + ", note=" + note + "]";
	}

}
