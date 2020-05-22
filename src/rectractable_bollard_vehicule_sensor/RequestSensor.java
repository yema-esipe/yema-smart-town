package rectractable_bollard_vehicule_sensor;
import java.io.IOException;



import common.ConvertJSON;



import common.RetractableBollard;
import common.VehicleSensor;
import common.DeviceConfigNbCar;
import common.Request;
import common.Response;
public class RequestSensor {
	
	private SensorCommunication communication = new SensorCommunication();
	private ConvertJSON converter = new ConvertJSON();
	
	
	/**
	 * Méthode qui permet de récupérer les configurations établient sur bornes
	 * Renvoie un Objet de type retractablebollard
	 */
	public  VehicleSensor getSensor() { 
		Request req = new Request();
		req.setOperation_type("select");
		req.setSource("sensor");
		req.setTarget("vehiclesensor");
			
		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORT());
			Response resp = communication.sendMessage(req);
			VehicleSensor parameter = converter.JsontoVehicleSensor(resp.getValues().get(resp.getValues().size() - 1));
			
			communication.stopConnection();
			return parameter;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public  RetractableBollard getBollard() { 
		Request req = new Request();
		req.setOperation_type("select");
		req.setSource("sensor");
		req.setTarget("retractablesbollard");
			
		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORT());
			Response resp = communication.sendMessage(req);
			RetractableBollard parameter = converter.JsonToBollard(resp.getValues().get(resp.getValues().size() - 1));
			
			communication.stopConnection();
			return parameter;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	// merthode qui renvoie objet confignbcar donnant le nombre max de voiture dans la ville 
	
	public DeviceConfigNbCar getNb() {
		
		Request req = new Request();
	req.setOperation_type("select");
	req.setSource("NbCar");
	req.setTarget("DeviceConfigNbCar");
		
	try {
		communication.startConnection(communication.getADDRESS(), communication.getPORT());
		Response resp = communication.sendMessage(req);
		DeviceConfigNbCar DeviceConfigNbCar = converter.JsontoDeviceConfigNbCar(resp.getValues().get(resp.getValues().size() - 1));
		
		communication.stopConnection();
		return DeviceConfigNbCar;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	  }}
	
	
	}
	
	
		
		
		
		
		
		
		
		
		
		
		
		

	
	
		
	
	
	
		


	
	


