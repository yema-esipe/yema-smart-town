package common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * ConvertJSON is common to server and client
 * it allows to do the conversion that we need
 * Firstly for requests and server response 
 * Secondly for the object present in requests, they have to be convert in JSON too
 *   
 */
public class ConvertJSON {
	private ObjectMapper mapper = new ObjectMapper();
	
	public ConvertJSON() {}
	
	public String RequestToJson(Request req) {
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(req);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	
	public Request JsontoRequest(String jsonString) {
		Request req = new Request();
		try {
			req = mapper.readValue(jsonString, Request.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return req;
	}
	
	public String ResponseToJson(Response resp) {
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(resp);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public Response JsontoResponse(String jsonString) {
		Response resp = new Response();
		try {
			resp = mapper.readValue(jsonString, Response.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	
	public String DeviceAirToJson(DeviceAir device) {
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(device);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public DeviceAir JsontoDeviceAir(String jsonString) {
		DeviceAir device = new DeviceAir();
		try {
			device = mapper.readValue(jsonString, DeviceAir.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return device;
	}
	
	public String AlertToJson(Alert alert) {
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(alert);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public Alert JsontoAlert(String jsonString) {
		Alert alert = new Alert();
		try {
			alert = mapper.readValue(jsonString, Alert.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return alert;
	}
	
	public String DataToJson(DataAir data) {
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public DataAir JsontoData(String jsonString) {
		DataAir data = new DataAir();
		try {
			data = mapper.readValue(jsonString, DataAir.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public String ConfigToJson(DeviceConfigAir config) {
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(config);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public DeviceConfigAir JsontoConfig(String jsonString) {
		DeviceConfigAir config = new DeviceConfigAir();
		try {
			config = mapper.readValue(jsonString, DeviceConfigAir.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	public String SaveToJson(AppliSave save) {
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(save);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public AppliSave JsontoSave(String jsonString) {
		AppliSave save = new AppliSave();
		try {
			save = mapper.readValue(jsonString, AppliSave.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public String DataAVGToJson(DataAirAVG dataAVG) {
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(dataAVG);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public DataAirAVG JsontoDataAVG(String jsonString) {
		DataAirAVG dataAVG = new DataAirAVG();
		try {
			dataAVG = mapper.readValue(jsonString, DataAirAVG.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataAVG;
	}
	public String BollardToJson(RetractableBollard bollard) {
		String jsonString = "" ;
		try { 
			jsonString = mapper.writeValueAsString(bollard);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
			
	}
	
	public String CarToJson (Car car ) {
		
		String jsonString = ""; 
		try { 
			jsonString = mapper.writeValueAsString(car);
		}catch (JsonProcessingException e) {
			e.printStackTrace();
			}
		return jsonString;
	}
	
	
	public Car JsonToCar(String jsonString) {
		Car car = new Car();
	try { car = mapper.readValue( jsonString,Car.class );
	}catch (IOException e) {
		e.printStackTrace();
	}
	return car;
	}

	public String infotrafficToJson (infotraffic info ) {
		
		String jsonString = ""; 
		try { 
			jsonString = mapper.writeValueAsString(info);
		}catch (JsonProcessingException e) {
			e.printStackTrace();
			}
		return jsonString;
	}

	public infotraffic JsonToinfotraffic(String jsonString) {
		infotraffic info = new infotraffic();
	try { info = mapper.readValue( jsonString,infotraffic.class );
	}catch (IOException e) {
		e.printStackTrace();
	}
	return info;
	}
	
		
	
	
	public RetractableBollard JsonToBollard(String jsonString) {
		RetractableBollard bollard = new RetractableBollard();
		try { bollard = mapper.readValue( jsonString ,RetractableBollard.class );
		}catch (IOException e) {
			e.printStackTrace();
		}
		return bollard;
		}
	
	public String DeviceConfigNbCartoJson(DeviceConfigNbCar device) {
		String jsonString = "";
	try {
		jsonString = mapper.writeValueAsString(device);
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	return jsonString;
		}
	
	public DeviceConfigNbCar JsontoDeviceConfigNbCar(String jsonString) {
		DeviceConfigNbCar device = new DeviceConfigNbCar();
	try {
		device = mapper.readValue(jsonString, DeviceConfigNbCar.class);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return device;
		}
    public VehicleSensor JsontoVehicleSensor(String jsonString) {
    	VehicleSensor sensor = new VehicleSensor();
    
	try {
		sensor = mapper.readValue(jsonString, VehicleSensor.class);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return sensor;
	}
    public String VehicleSensortoJson(VehicleSensor sensor) {
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(sensor);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
		}
	
	
}
