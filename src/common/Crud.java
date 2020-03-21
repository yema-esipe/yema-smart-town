package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import connection.DataSource;

public class Crud {
	private DataSource source;
	
	public Crud() {
		source = new DataSource();
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public JSONArray executeSelect(String request) {		//return a list with the select result 
		JSONArray json = null;
		try {
			Connection connection = source.giveConnection();
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery(request);
			ResultSetMetaData mRes = result.getMetaData();
			json = new JSONArray();
			
			while(result.next()) {
				JSONObject obj = new JSONObject();
				String name = mRes.getColumnName(1);
				
				obj.put(name, result.getString(name));
				json.add(obj);
			}

			source.returnConnection(connection);
			source.closeAllConnection();
		} catch(Exception e) {}
		return json;
	}
	
	@SuppressWarnings("static-access")
	public JSONArray executeUpdate(String request) {					// accomplished update/insert/delete request 
		JSONArray json = new JSONArray();

		try {
			Connection connection = source.giveConnection();
			Statement myRequest = connection.createStatement();
			myRequest.executeUpdate(request);
			
			JSONObject obj = new JSONObject();
			obj.put("Etat", "requete executee");
			
			json.add(obj);
						
			source.returnConnection(connection);
			source.closeAllConnection();
		} catch(Exception e) {}
		return json;
	}
	
}
