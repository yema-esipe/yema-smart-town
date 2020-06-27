package rectractable_bollard_vehicule_sensor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import client.ClientCommunication;
import common.Request;
import connection.PropertiesFileReader;

public class TestBollard {
	
	public static void main(String[] args) throws IOException {
		
		Logger LOGGER = Logger.getLogger(TestBollard.class.getName());
		PropertiesFileReader serveconfig = new PropertiesFileReader();
		serveconfig.initServer();
		
		final int SERVER_PORT = Integer.parseInt(serveconfig.getProperty("serverportClient"));
		final String SERVER_ADDRESS = serveconfig.getProperty("serveraddress");
		
		ClientCommunication client = new ClientCommunication();
		try {
			client.startConnection(SERVER_ADDRESS, SERVER_PORT);
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			LOGGER.log(Level.WARNING, "Erreur de connexion client");
		}
		
		Request req = new Request();
		
		req.setOperation_type("select");
		req.setTarget("vehiclesensor");
		req.setSource("client");
		client.sendMessage(req); 
		System.out.println("Ok"); 
		
	
		
		
		
		
	}

}
