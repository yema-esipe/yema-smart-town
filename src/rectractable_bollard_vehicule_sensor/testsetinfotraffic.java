package rectractable_bollard_vehicule_sensor;


import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import client.ClientCommunication;
import common.Car;
import common.ConvertJSON;

import common.Request;
import common.RetractableBollard;
import common.YamlFileReader;
import common.infotraffic;
import connection.PropertiesFileReader;

public class testsetinfotraffic {
	
	public static void main(String[] args) throws IOException {
		
		Logger LOGGER = Logger.getLogger(testAddCar.class.getName());
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
		Yaml yaml = new Yaml(new Constructor(infotraffic.class));
	
		InputStream inputStream = yaml.getClass().getClassLoader().getResourceAsStream("ressources/testsetinfotraffic.yaml");
		infotraffic info   =  (infotraffic) yaml.load(inputStream);
		ConvertJSON converter = new ConvertJSON();
	
		
		Request req = new Request();
		req.setObj(converter.infotrafficToJson(info));
		
		
		
		
		req.setOperation_type("insert");
		req.setTarget("infotraffic");
		req.setSource("");
		
		client.sendMessage(req); 
		System.out.println("Ok"); 
		
		
	}
	}
