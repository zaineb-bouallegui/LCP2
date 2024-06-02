package externaldatabaseconnector.impl;

import externaldatabaseconnector.interfaces.PreparedStatementCreator;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mendix.systemwideinterfaces.javaactions.parameters.TemplateParameterType.*;

public class PreparedStatementCreatorImpl implements PreparedStatementCreator {
	private static final String VALUE = "value";

	@Override
	public PreparedStatement create(String query, Connection connection) throws SQLException {
		return connection.prepareStatement(query);
	}
	//Using JSON Object for named Query parameters
	public PreparedStatement create(String sql, String queryParametersJSON, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		JSONObject queryParametersJSONObject = (JSONObject) JSONValue.parse(queryParametersJSON);
		addPreparedStatementParameters(sql, preparedStatement, queryParametersJSONObject);
		return preparedStatement;
	}
	private void addPreparedStatementParameters(String sql,
												PreparedStatement preparedStatement,JSONObject queryParametersJSONObject) throws SQLException, IllegalArgumentException {
		Matcher m = Pattern.compile( "[?]" ).matcher(sql);

		List<String> parameterCount = new ArrayList<>();
		while (m.find()) {
			parameterCount.add(m.group());
		}

		for (int i = 0; i < parameterCount.size(); i++) {
			addParameter(preparedStatement, i, queryParametersJSONObject);
		}
	}
	private void addParameter(PreparedStatement preparedStatement,
							  int i,
							  JSONObject queryParametersJSON) throws SQLException {
		JSONObject paramObject = (JSONObject) queryParametersJSON.get((i + 1) + "");
		try {
			switch (paramObject.get("dataType").toString()) {
				case "INTEGER":
					preparedStatement.setLong(i + 1, Long.parseLong(paramObject.get(VALUE).toString()));
					break;
				case "STRING":
					preparedStatement.setString(i + 1, paramObject.get(VALUE).toString());
					break;
				case "BOOLEAN":
					preparedStatement.setBoolean(i + 1, Boolean.parseBoolean(paramObject.get(VALUE).toString()));
					break;
				case "DECIMAL":
					BigDecimal bigDecimal = new BigDecimal(paramObject.get(VALUE).toString());
					preparedStatement.setBigDecimal(i + 1, bigDecimal);
					break;
				case "DATETIME":
					long epochTime = Long.parseLong(paramObject.get(VALUE).toString());
					java.util.Date date = new java.util.Date(epochTime);
					preparedStatement.setTimestamp(i + 1, new Timestamp(date.getTime()));
					break;
				default:
					throw new IllegalArgumentException(String.format("The parameter type '%s' is invalid, for the parameter named '%s'.", paramObject.get("dataType"), paramObject.get("name").toString()));
			}
		} catch (Exception exception) {

			//If catched exception is of type IllegalArgumentException, do not alter message throw it as it is.
			if(exception.getClass().equals(IllegalArgumentException.class)){
				throw exception;
			}

			String message = String.format("An error has occurred while handling the parameter with name: '%s', \nCause : %s",
					paramObject.get("name").toString(), exception.toString());
			throw new SQLException(message, exception);
		}
	}
}

