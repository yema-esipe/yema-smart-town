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
	private final String source;
	Connection connection;
	public SensorOperation (VehicleSensor sensor) {
		this.sensor = sensor;
		source = "sensor " + sensor.getId();
		file.initVSensor();
	
	}
	
	public void start() throws InterruptedException {
		SensorCommunication communication = new SensorCommunication();
		DeviceConfigNbCar requestNb = new RequestSensor(communication).getNb();

		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORT());
			Request request = new Request();
			boolean end;
			while (!end) {
				
				 
				 CarDAO daocar = new CarDAO();
				 nbVehicule = daocar.select(connection).size();
				 request.setOperation_type("select");
				 request.setTarget("Alert"); 
				 
				 
				 
				
				
				
				
					
				}
			
			communication.stopConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
	

}
