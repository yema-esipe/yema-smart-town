package rectractable_bollard_vehicule_sensor;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import airsensor.CheckAlert;
import common.Alert;
import common.Car;
import common.DataAir;
import common.DataAirAVG;
import common.DeviceAir;
import common.DeviceConfigNbCar;
import common.Request;
import common.VehicleSensor;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;
import common.RetractableBollard;
import connection.PropertiesFileReader;
import rectractable_bollard_vehicule_sensor.RequestSensor;
import server.CarDAO;

public class SensorOperation {
	
	private RetractableBollard bollard ;
	private RequestSensor request;
	private VehicleSensor sensor; 
	private PropertiesFileReader file = new PropertiesFileReader();
	private int nbVehicule; 
	 private Connection connection;
	
	
	
	


	


	public void start(RetractableBollard bollard) throws InterruptedException {
		SensorCommunication communication = new SensorCommunication();
		

		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORT());
			Request request = new Request();
			boolean end = true;
			while (!end) {
				
				 
				 CarDAO daocar = new CarDAO();
				
				nbVehicule = daocar.select(connection).size();
				 request.setOperation_type("selectnbmax");
				 request.setTarget("infotraffic"); 
				 request.setSource("");
				 
				 ArrayList<String> list = communication.sendMessage(request).getValues();
				 
				 ArrayList<Integer> numbers = new ArrayList<Integer>();

				 for(int i = 0; i < list.size(); i++) {
				    numbers.add(Integer.parseInt(list.get(i)));   
				 }
				int Max = numbers.get(1);
				if (Max >nbVehicule) { 
					System.out.println(" reussi");
			}
				else {break;
				}
				}
				 
				 communication.stopConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
	


