package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		JDBCConnectionPool jdbc = new JDBCConnectionPool();
		DataSource source = new DataSource();
		try {
			Connection connection = source.giveConnection();
			Statement myRequest = connection.createStatement();
			ResultSet result = myRequest.executeQuery("SELECT NOM FROM users");
		
			while(result.next()) {
				System.out.println(result.getString(1));
			}
			
			myRequest.executeUpdate("Update users set nom = 'KONE' Where prenom = 'Yaya'");
			
			source.returnConnection(connection);
			source.closeAllConnection();
		} catch(Exception e) {}
		
		
	}
}
