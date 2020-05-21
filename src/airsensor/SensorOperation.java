package airsensor;

import java.io.IOException;
import java.util.ArrayList;

import common.DataAir;
import common.DataAirAVG;
import common.DeviceAir;
import common.Index;
import common.YamlFileReader;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;
import connection.PropertiesFileReader;
/**
 * 
 * @author elisa
 * SensorOperation is a class which have the responsibility to start a sensor
 * All the sensor process is here and one call to this class matches with only one sensor 'device'
 * take the parameters recording in resources file called 'aqs-config.properties'
 */
public class SensorOperation {
	private RequestsAqsClient request;
	private PropertiesFileReader file = new PropertiesFileReader();
	private YamlFileReader yaml = new YamlFileReader();
	private CheckAlert check;
	private DeviceAir device;
	private ArrayList<DataAir> datas;
	private final String source;
	private boolean end = false;


	public SensorOperation(DeviceAir device) {
		this.device = device;
		source = "sensor " + device.getId();
		file.initSensor();
	}
	/**
	 * start the sensor
	 * 
	 * 1) need a server connection to send request 'communication', requestsAqsClient uses this connection all the time
	 * 2) need to always be active even if the client disable the sensor
	 * 3) recover all the simulation data and send one by one to the server, computing average, taking the frequency sensor
	 * 4) check the quality of the device for each statement and send it
	 * 5) check alert for each data 
	 * 
	 */
	public void start() throws InterruptedException {
		CommunicationWithServer communication = new CommunicationWithServer();
		check = new CheckAlert(device, communication);

		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORTAQS());
			request = new RequestsAqsClient(communication);
			while (!end) {
				Thread.sleep(5000); // to limit the database request when sensor is OFF
				
				datas = yaml.datainit2(device.getId());
				
				for (DataAir d : datas) {
					device = (DeviceAir) request.selectOne(device.getId(), source, "deviceair");
					if (device.isActive()) {

						/** BDD UPDATE */
						request.insert(d, source,"dataair");
						request.insert(device, source, "dataairavg");
						ArrayList<DataAirAVG> dataAVG = request.getDataAVG(device.getId());
						
						if (dataAVG.size() > 0) {
							this.calculQuality(dataAVG.get(0));
							request.update(device, source, "deviceair");
						}
						
						check.checkAlert(dataAVG);

						Thread.sleep(device.getFrequency() * Integer.parseInt(file.getProperty("unit"))); //We can change unit easily in the file: for second it's 1000 and for minute 60 000
					} else {
						//request.delete(device.getId(), source, "dataair"); 
						// -> allow to limit the data database size but need to be suspended for the demonstration to have data recorded before the demo
						break;
					}
				}
			}
			communication.stopConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * calculQuality allow to have the sensor quality each time data are sending to the server
	 * @param dataAVG -> it is a list with all average which is calculated, ordered by more recent date
	 * 
	 * Referenced to the air quality index values which are recording in resources file 'aqs-index.yaml'
	 * the value of the key represent the end of the interval
	 */
	public void calculQuality(DataAirAVG dataAVG) {
		float quality = 0;

		YamlFileReader reader = new YamlFileReader();
		Index index = reader.initIndex();
		float global = 0;

		for(int key : index.getCo2().keySet()) {
			if (dataAVG.getCo2() <= index.getCo2().get(key)) {global = key; break;}
		}

		for(int key : index.getCarbonMonoxide().keySet()) {
			if (dataAVG.getCarbonMonoxide() <= index.getCarbonMonoxide().get(key)) { global = global + key; break; }
		}

		for(int key : index.getFineParticules().keySet()) {
			if (dataAVG.getFinesParticules() <= index.getFineParticules().get(key)) { global = global + key; break; }
		}

		for(int key : index.getSulfurDioxide().keySet()) {
			if (dataAVG.getSulfurDioxide() <= index.getSulfurDioxide().get(key)) { global = global + key; break; }
		}

		for(int key : index.getNitrogenDioxide().keySet()) {
			if (dataAVG.getNitrogenDioxide() <= index.getNitrogenDioxide().get(key)) { global = global + key; break; }
		}

		for(int key : index.getOzone().keySet()) {
			if (dataAVG.getOzone() <= index.getOzone().get(key)) { global = global + key; break; }
		}

		quality = global / 6;

		device.setQuality(quality);
	}
	
}
