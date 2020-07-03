package rectractable_bollard_vehicule_sensor;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import client.ClientCommunication;
import common.Car;
import common.ConvertJSON;
import common.Request;
import common.RetractableBollard;
import connection.PropertiesFileReader;
import server.CarDAO;

public class TestBollard {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		Logger LOGGER = Logger.getLogger(TestBollard.class.getName());
		/*PropertiesFileReader serveconfig = new PropertiesFileReader();
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
		}*/
		Yaml yaml = new Yaml(new Constructor(RetractableBollard.class));
		
		InputStream inputStream = yaml.getClass().getClassLoader().getResourceAsStream("ressources/TestBollard.yaml");
		RetractableBollard bollard  =  (RetractableBollard) yaml.load(inputStream);
		ConvertJSON converter = new ConvertJSON();
		
		 
		 bollard.setState(false);
		 bollard.setId(1);
		 SensorOperation operation =new  SensorOperation();
		 Car car =new Car();
		 car.setId(10);
		 car.setIsInTheCity(false);
		 operation.start(bollard,car);
		 System.out.println("good");
		
		
		
	
		

	
	
	      } 
	}
