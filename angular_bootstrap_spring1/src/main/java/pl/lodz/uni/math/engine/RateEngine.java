package pl.lodz.uni.math.engine;

import javax.persistence.Entity;
import javax.persistence.Table;

import pl.lodz.uni.math.pojo.Rate;

@Entity
@Table(name = "Rate")
public class RateEngine extends Rate {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8205827341015652177L;

	public RateEngine() {
		super();
	}

}
