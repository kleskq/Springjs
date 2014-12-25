package pl.lodz.uni.math.persistence.base;

import java.util.List;

public interface QueryString {
	public String getStatement();

	public List<QueryParameter> getParameters();
}
