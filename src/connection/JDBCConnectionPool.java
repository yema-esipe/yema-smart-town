package connection;

import java.sql.Connection;
import java.util.ArrayList;

public class JDBCConnectionPool {
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	 
	JDBCConnectionPool() {
		
	}
	
	public Connection getConnection () {
		Connection tempConnection = connections.get(0); 
		connections.remove(0);
		return tempConnection;
	}
	
	public void putConnection (Connection c) {
		connections.add(c);
	}  
	
	public void closeAllConnections() {
		
	}
	
	
}
