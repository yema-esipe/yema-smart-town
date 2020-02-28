package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.PropertiesFileReader;

public class JDBCConnectionPool {
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	private Connection myConnection;
	private PropertiesFileReader file = new PropertiesFileReader();
	
	public JDBCConnectionPool() {
		 System.out.println("test1");

		 try {
			 file.init();
			 System.out.println(file.getProperty("driver"));
			 
			 String driver = file.getProperty("driver");
			 Class.forName(driver);
			 
			 System.out.println("après driver");
			 
			 System.out.println("test5");
			 for (int i = 0; i < 10; i++) {
				 myConnection = DriverManager.getConnection(file.getProperty("url"), file.getProperty("id"), file.getProperty("password"));
				 connections.add(myConnection);
			 }
		 } catch (Exception e) {
			 System.out.println("erreur connection");

		 }
	}
		
	public synchronized Connection giveConnection () {
		 System.out.println("test2");

		/*while (connections.isEmpty()) {
			System.out.println("Veuillez patientez");
		}*/
		Connection tempConnection = connections.get(0); 
		connections.remove(0);
		return tempConnection;
	}
	
	public synchronized void returnConnection (Connection c) {
		connections.add(c);
	}  
	
	public synchronized void closeAllConnections() {
		try {
			myConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
