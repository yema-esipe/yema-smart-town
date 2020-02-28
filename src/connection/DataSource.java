package connection;

import java.sql.Connection;

public class DataSource {
	private static JDBCConnectionPool pool;
	
	public static Connection getConnection() { //give to the client a connection available 
		Connection connection = null;
		try {
			connection = pool.getConnection();
		} catch (Exception e) {}
		
		return connection;
	}
	
	public static void returnConnection(Connection c) {
		try {
			pool.returnConnection(c);
		} catch (Exception ee) {}
	}
	
	public static void closeAllConnection() {
		try {
			pool.closeAllConnections();
		} catch (Exception e) {}
	}
}
