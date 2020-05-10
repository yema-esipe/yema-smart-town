package client;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import connection.PropertiesFileReader;

public class TestUnitCalculEC {
	
	public static void main(String[] args) throws IOException {
		
		Logger LOGGER = Logger.getLogger(FenCalculEC.class.getName());
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
		
		
		/*sérialisation map json*/
		
		HashMap<String, String> request = new HashMap<>();
		request.put("operation_type", "selectnbpassengeravg");
		request.put("target", "datapollution");
		//request.put("date_debut", "2020-05-01");
		//request.put("date_fin", "2020-05-07");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonrequest = "";
		
		jsonrequest = mapper.writeValueAsString(request);
		client.send(jsonrequest);
		String s = client.get();
		System.out.println("Ok");
		System.out.println(s);
		System.out.println("fintest");
		
		
		
		
	}

}
