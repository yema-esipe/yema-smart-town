package server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.PropertiesFileReader;
/**
 * 
 * this class launch the server with multi port
 *
 */
public class ServerSocketTest {
public static void main (String[] args) {
		
		Logger LOGGER = Logger.getLogger(ServerSocketTest.class.getName());
		PropertiesFileReader serveconfig = new PropertiesFileReader();
		serveconfig.initServer();
		
		final int SERVER_PORT_CLIENT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		final int SERVER_PORT_AQS = Integer.parseInt(serveconfig.getProperty("serverportAQS"));

		try {
			System.out.println("\n");
			LOGGER.log(Level.INFO, "*** Server program beginning ***");
			System.out.println("\n");
			
			ServerCommunication server = new ServerCommunication();

			server.start(SERVER_PORT_CLIENT, SERVER_PORT_AQS); 
			
		} catch (IOException e) {}
		
		System.out.println("\n\n");
		LOGGER.log(Level.INFO, "*** End server program ***");
		
	}

      
}
