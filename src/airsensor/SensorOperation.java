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

public class SensorOperation {
	private RequestsAqsClient request;
	private PropertiesFileReader file = new PropertiesFileReader();
	private CheckAlert check;
	private DeviceAir device;
	private final String source;
	private boolean end = false;


	public SensorOperation(DeviceAir device) {
		this.device = device;
		source = "sensor " + device.getId();
		file.initSensor();
	}

	public void start() throws InterruptedException {
		CommunicationWithServer communication = new CommunicationWithServer();
		check = new CheckAlert(device, communication);

		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORTAQS());
			request = new RequestsAqsClient(communication);
			while (!end) {
				device.getValues(device.getId());
				for (DataAir d : device.getDatas()) {
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

						Thread.sleep(device.getFrequency() * Integer.parseInt(file.getProperty("unit"))); //en seconde : *1000 en minute : *60000
					} else {
						request.delete(device.getId(), source, "dataair");
						break;
					}
				}
			}
			communication.stopConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void calculQuality(DataAirAVG dataAVG) {
		float quality = 0;

		YamlFileReader reader = new YamlFileReader();
		Index index = reader.initIndex();
		float global = 0;

		for(int key : index.getCo2().keySet()) {
			if (dataAVG.getCo2() < index.getCo2().get(key)) global = key; break;
		}

		for(int key : index.getCarbonMonoxide().keySet()) {
			if (dataAVG.getCarbonMonoxide() < index.getCarbonMonoxide().get(key)) { global = global + key; break; }
			else global = 10;
		}

		for(int key : index.getFineParticules().keySet()) {
			if (dataAVG.getFinesParticules() < index.getFineParticules().get(key)) { global = global + key; break; }
			else global = global + 10;
		}

		for(int key : index.getSulfurDioxide().keySet()) {
			if (dataAVG.getSulfurDioxide() < index.getSulfurDioxide().get(key)) { global = global + key; break; }
			else global = global + 10;
		}

		for(int key : index.getNitrogenDioxide().keySet()) {
			if (dataAVG.getNitrogenDioxide() < index.getNitrogenDioxide().get(key)) { global = global + key; break; }
			else global = global + 10;
		}

		for(int key : index.getOzone().keySet()) {
			if (dataAVG.getOzone() < index.getOzone().get(key)) { global = global + key; break; }
			else global = global + 10;
		}

		quality = global / 6;

		device.setQuality(quality);
	}
}
