package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCConnectionPool {
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	private InfoAdmin infos;
	
	private Connection myConnection;
	
	JDBCConnectionPool() {
		 try {
			 Class.forName(infos.getDriver());
			 for (int i = 0; i < 10; i++) {
				 myConnection = infos.getDriver().getConnection(infos.getUrl(), infos.getid(), infos.getmdp());
			 }
		 } catch (Exception e) {}
	}
		
	public synchronized Connection getConnection () {
		while (connections.isEmpty()) {
			System.out.println("Veuillez patientez");
		}
		Connection tempConnection = connections.get(0); 
		connections.remove(0);
		return tempConnection;
	}
	
	public synchronized void returnConnection (Connection c) {
		connections.add(c);
	}  
	
	public synchronized void closeAllConnections() {
		try {
			myConnection.close()
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
