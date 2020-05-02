package connection;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCConnectionPool {
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	private Connection myConnection;
	private PropertiesFileReader file = new PropertiesFileReader();
	
	public JDBCConnectionPool() {
		 try {
			 file.initJDBC();
			 String driver = file.getProperty("driver");
			 
			 Class.forName(driver);

			 for (int i = 0; i < 6; i++) {  
				 myConnection = DriverManager.getConnection(file.getProperty("url"), file.getProperty("id"), file.getProperty("password"));
				 connections.add(myConnection);

			 }
		 } catch (Exception e) {
			 System.out.println("erreur connection");
				e.printStackTrace();

		 }
	}
	
	/**
	 * giveConnection method gives connections to clients whose need it
	 * if there isn't any connection available, they have to wait
	 */
	public synchronized Connection giveConnection () {

		while (connections.isEmpty()) {
			System.out.println("Veuillez patientez");
		}
		Connection tempConnection = connections.get(0); 
		connections.remove(0);
		return tempConnection;
	}
	
	/**
	 * returnConnection method allows to return the connection used by a client
	 * thanks to that, the connection is available again
	*/
	public synchronized void returnConnection (Connection c) {
		connections.add(c);
	}  
	
	/**
	 * closeAllConnection method allows to close all the connection used by different clients
	*/
	public synchronized void closeAllConnections() {
		try {
			for(Connection c : connections) {
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
