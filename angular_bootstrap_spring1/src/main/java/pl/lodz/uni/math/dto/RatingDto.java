package pl.lodz.uni.math.dto;

public class RatingDto {
	private String rating;

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public RatingDto() {
		super();
	}

	public RatingDto(String rating) {
		super();
		this.rating = rating;
	}

}
