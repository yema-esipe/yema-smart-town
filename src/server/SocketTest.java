package server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SocketTest {
	public static void main (String[] args) {
		
		Logger LOGGER = Logger.getLogger(SocketTest.class.getName());
		
		try {
			System.out.println("\n");
			LOGGER.log(Level.INFO, "*** Fin Programme Serveur ***");
			System.out.println("\n");
			
			ServerCommunication server = new ServerCommunication();
			server.start(8888); 
			server.stop(); 
		} catch (IOException e) {}
		
		System.out.println("\n\n");
		LOGGER.log(Level.INFO, "*** Fin Programme Serveur ***");
	}
}
