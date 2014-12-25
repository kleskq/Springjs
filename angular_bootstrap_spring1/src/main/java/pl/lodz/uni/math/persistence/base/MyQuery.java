package pl.lodz.uni.math.persistence.base;

import java.util.ArrayList;
import java.util.List;

public class MyQuery implements QueryString {

	private String query;
	private List<QueryParameter> parameters;

	public MyQuery(String query, ArrayList<QueryParameter> parameters) {
		this.parameters = parameters;
		this.query = query;
	}

	public String getStatement() {
		StringBuffer statement = new StringBuffer(query);

		return statement.toString();
	}

	public List<QueryParameter> getParameters() {

		return parameters;
	}

}
