package common_aqs_client;

import java.io.IOException;
import java.util.ArrayList;

import common.Alert;
import common.AppliSave;
import common.ConvertJSON;
import common.DataAir;
import common.DataAirAVG;
import common.DeviceAir;
import common.DeviceConfigAir;
import common.Request;
import common.Response;

/**
 * RequestsAqsClient is common to clients and aqs because they have similar requests
 * @author elisa
 * this class allows to factor some requests which are already 'ready'
 */
public class RequestsAqsClient {
	private CommunicationWithServer communication;
	private ConvertJSON converter = new ConvertJSON();
	
	public RequestsAqsClient(CommunicationWithServer communication) {
		this.communication = communication;
	}
	

	public void insert(Object obj, String source, String target) {
		Request req = new Request();
		req.setOperation_type("insert");
		req.setSource(source);
		req.setTarget(target);
		String json = null;
		if ((target == "deviceair") || (target == "dataairavg")) json = converter.DeviceAirToJson((DeviceAir) obj); 
		if (target == "alert") json = converter.AlertToJson((Alert) obj);
		if (target == "applisave") json = converter.SaveToJson((AppliSave) obj);
		if (target == "dataair") json = converter.DataToJson((DataAir) obj);
		
		req.setObj(json);

		try {
			if(target == "alert")  System.out.println(req);
			communication.sendMessage(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void update(Object obj, String source, String target) {
		Request req = new Request();
		req.setOperation_type("update");
		req.setSource(source);
		req.setTarget(target);
		
		String json = null;
		if (target == "deviceair") json = converter.DeviceAirToJson((DeviceAir) obj); 
		if (target == "alert") json = converter.AlertToJson((Alert) obj);
		if (target == "deviceconfigair") json = converter.ConfigToJson((DeviceConfigAir) obj);
		
		req.setObj(json);
		
		try {
			if(target == "alert")  System.out.println(req);
			communication.sendMessage(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public void delete(Object obj, String source, String target) {
		Request req = new Request();
		req.setOperation_type("delete");
		req.setSource(source);
		req.setTarget(target);
		
		String json = null;
		if (target == "deviceair") {
			json = converter.DeviceAirToJson((DeviceAir) obj); 
			req.setObj(json);
		} else {
			String id = String.valueOf((Integer) obj);
			req.setObj(id);
		}
			
		try {
			if(target == "alert")  System.out.println(req);
			communication.sendMessage(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object selectOne(int id, String source, String target) {
		Request req = new Request();
		req.setOperation_type("selectID");
		req.setSource(source);
		req.setTarget(target);
		req.setObj(Integer.toString(id));

		Object obj = null;
		try {
			//System.out.println(req);
			Response resp = communication.sendMessage(req);
			if (target == "deviceconfigair") obj = converter.JsontoConfig(resp.getValues().get(0));
			if (target == "alert") obj = converter.JsontoAlert(resp.getValues().get(0));
			if (target == "deviceair") obj = converter.JsontoDeviceAir(resp.getValues().get(0));
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	
	public ArrayList<String> getInformationDeviceAir() {
		Request req = new Request();
		req.setOperation_type("selectInformation");
		req.setSource("sensor ");
		req.setTarget("deviceair");
				
		try {
			//System.out.println(req);
			Response resp = communication.sendMessage(req);
			ArrayList<String> information = resp.getValues();
			return information;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<AppliSave> getAppliSave() {
		Request req = new Request();
		req.setOperation_type("select");
		req.setSource("sensor ");
		req.setTarget("applisave");
				
		try {
			//System.out.println(req);
			Response resp = communication.sendMessage(req);
			ArrayList<String> saveString = resp.getValues();
			ArrayList<AppliSave> save = new ArrayList<AppliSave>();
			
			for (int i = 0; i < saveString.size(); i++) {
				save.add(converter.JsontoSave(saveString.get(i)));
			}
			
			return save;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<DataAirAVG> getDataAVG(int id) {
		Request req = new Request();
		req.setOperation_type("selectID");
		req.setSource("sensor " + id);
		req.setTarget("dataairavg");
		String idDevice = String.valueOf(id);
		req.setObj(idDevice);
		
		try {
		//	System.out.println(req);
			Response resp = communication.sendMessage(req);
			ArrayList<String> dataAVGString = resp.getValues();
			ArrayList<DataAirAVG> dataAVG = new ArrayList<DataAirAVG>();
			
			for (int i = 0; i < dataAVGString.size(); i++) {
				dataAVG.add(converter.JsontoDataAVG(dataAVGString.get(i)));
			}
			return dataAVG;			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}		
}
