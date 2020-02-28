package connection;

import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		JDBCConnectionPool jdbc = new JDBCConnectionPool();
		DataSource source = new DataSource(jdbc);
		try {
			Statement myRequest = source.giveConnection().createStatement();
			ResultSet result = myRequest.executeQuery("SELECT NOM FROM users");
		
			while(result.next()) {
				System.out.println(result.getString(1));
			}
		} catch(Exception e) {}
		
		
	}
}
