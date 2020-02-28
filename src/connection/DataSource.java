package connection;

import java.sql.Connection;

public class DataSource {
	private static JDBCConnectionPool pool;
	
	public DataSource() {
		pool = new JDBCConnectionPool();
	}
	
	public static Connection giveConnection() { //give to the client a connection available 
		return pool.giveConnection();
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
