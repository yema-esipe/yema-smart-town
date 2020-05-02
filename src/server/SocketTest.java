package server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.PropertiesFileReader;

public class SocketTest {
	public static void main (String[] args) {
		
		Logger LOGGER = Logger.getLogger(SocketTest.class.getName());
		
		PropertiesFileReader file = new PropertiesFileReader();
		file.initServer();
		
		final int SERVER_PORT = Integer.parseInt(file.getProperty("serverport"));
		
		try {
			System.out.println("\n");
			LOGGER.log(Level.INFO, "*** Server program beginning ***");
			System.out.println("\n");
			
			ServerCommunication server = new ServerCommunication();
			server.start(SERVER_PORT); 
			server.stop(); 
		} catch (IOException e) {}
		
		System.out.println("\n\n");
		LOGGER.log(Level.INFO, "*** End server program ***");
	}
}
