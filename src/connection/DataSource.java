package connection;

public class DataSource {
	private static JDBCConnectionPool connection;
	
	public static JDBCConnectionPool getConnection() { //give to the client a connection available 
		try {
			connection.getConnection();
		} catch (Exception e) {}		
	}
	
	public static void putConnection() {
		try {
			connection.putConnection();
		} catch (Exception ee) {}
	}
	
	public static void closeAllConnection() {
		try {
			connection.closeAllConnection();
		} catch (Exception e) {}
	}
}
