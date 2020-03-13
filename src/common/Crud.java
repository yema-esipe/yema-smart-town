package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DataSource;

public class Crud {
	private DataSource source;
	
	public Crud() {
		source = new DataSource();
	}
	
	@SuppressWarnings("static-access")
	public ArrayList<String> executeSelect(String request) {		//return a list with the select result
		ArrayList<String> list = new ArrayList<String>();
		try {
			Connection connection = source.giveConnection();
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery(request);
			
			while(result.next()) {
				list.add(result.getString(1));
			}
			
			source.returnConnection(connection);
			source.closeAllConnection();
		} catch(Exception e) {}
		return list;
	}
	
	@SuppressWarnings("static-access")
	public void executeUpdate(String request) {					// accomplished update/insert/delete request 
		try {
			Connection connection = source.giveConnection();
			Statement myRequest = connection.createStatement();
			myRequest.executeUpdate(request);
						
			source.returnConnection(connection);
			source.closeAllConnection();
		} catch(Exception e) {}
	}
	
}
