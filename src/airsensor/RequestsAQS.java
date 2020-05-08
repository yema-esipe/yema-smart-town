package airsensor;

import java.io.IOException;
import common.Alert;
import common.ConvertJSON;
import common.DataAir;
import common.DeviceAir;
import common.DeviceConfigAir;
import common.Request;
import common.Response;

public class RequestsAQS {
	private AQSCommunication communication = new AQSCommunication();
	private ConvertJSON converter = new ConvertJSON();
	
	
	/**
	 * M�thode qui permet de r�cup�rer les configurations �tablient sur les seuils
	 * Renvoie un Objet de type DeviceConfigAir 
	 */
	public DeviceConfigAir getConfig() { 
		Request req = new Request();
		req.setOperation_type("select");
		req.setSource("sensor");
		req.setTarget("deviceconfigair");
			
		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORT());
			Response resp = communication.sendMessage(req);
			DeviceConfigAir config = converter.JsontoConfig(resp.getValues().get(resp.getValues().size() - 1));
			
			communication.stopConnection();
			return config;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * M�thode qui permet de r�cup�rer la moyenne des derniers relev�s
	 * Renvoie un objet DataAir qui a comme valeur la moyenne des relev�s
	 */
	public DataAir getAVG(DeviceAir device) { 
		Request req = new Request();
		req.setOperation_type("selectAVG");
		req.setSource("sensor " + device.getId());
		req.setTarget("dataair");
		
		String jsonData = converter.DeviceAirToJson(device);
		req.setObj(jsonData);
		
		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORT());
			Response resp = communication.sendMessage(req);
			DataAir dataAVG = converter.JsontoData(resp.getValues().get(resp.getValues().size() - 1));
			
			communication.stopConnection();
			return dataAVG;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	
	/**
	   M�thode qui r�cup�re l'alerte du capteur quand celui est en alerte
	 */
	public Alert getAlert(int id) {
		Request req = new Request();
		req.setOperation_type("selectID");
		req.setSource("sensor " + id);
		req.setTarget("alert");
		req.setObj(Integer.toString(id));
		
		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORT());
			Response resp = communication.sendMessage(req);
			Alert alert = converter.JsontoAlert(resp.getValues().get(resp.getValues().size() - 1));
			
			communication.stopConnection();
			return alert;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * M�thode qui envoie au Server le relev� du DataAir pass� en param�tre
	 */
	public void sendValues(DataAir data) {
		Request req = new Request();
		req.setOperation_type("insert");
		req.setSource("sensor " + data.getIdDeviceAir());
		req.setTarget("dataair");
		
		String jsonData = converter.DataToJson(data);
		req.setObj(jsonData);
		
		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORT());
			communication.sendMessage(req);
			
			communication.stopConnection();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * M�thode qui envoit une alerte au Server
	 * Appel AQSCommunication
	 */
	public void sendAlert(Alert alert, String type) {
		Request req = new Request();
		req.setOperation_type(type);
		req.setSource("sensor " + alert.getIdDeviceAir());
		req.setTarget("alert");
		
		String jsonData = converter.AlertToJson(alert);
		req.setObj(jsonData);
		
		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORT());
			communication.sendMessage(req);
			
			communication.stopConnection();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}
