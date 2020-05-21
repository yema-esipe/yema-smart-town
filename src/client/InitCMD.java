package client;

import java.io.IOException;
import java.util.ArrayList;

import common.DataAir;
import common.DeviceAir;
import common.DeviceConfigAir;
import common.YamlFileReader;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;

/**
 * InitCMD is only for the demonstration but it is also a request test
 * @author elisa
 * it is principally in order to avoid to change each time the data in the database, more quick
 */
public class InitCMD {
	public static void main(String[] args) {
		YamlFileReader yaml = new YamlFileReader();
		CommunicationWithServer communication = new CommunicationWithServer();

		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORTClient());
			RequestsAqsClient request = new RequestsAqsClient(communication);

			
			for (int i = 1; i <= 3; i++) {
				/** Sensor reset */
				DeviceAir device = (DeviceAir) request.selectOne(i, "clientTEST", "deviceair");
				device.setFrequency(10);
				device.setOnAlert(false);
				device.setActive(true);
				device.setQuality(0);
				request.update(device, "clientTEST", "deviceair");
				
				/**all alerts are deleted*/
				request.delete(i, "clientTEST", "alert");
				
				/** all data and average are deleted */
				request.delete(i, "clientTEST", "dataair");
				
				/** sensor configuration reset */
				DeviceConfigAir config = new DeviceConfigAir();
				config.setIdDevice(i);
				config.setCo2(0);
				config.setCarbonMonoxide(0);
				config.setFinesParticules(0);
				config.setSulfurDioxide(0);
				config.setNitrogenDioxide(0);
				config.setOzone(0);

				request.update(config, "clientTEST", "deviceconfigair");
				
				/**we put ten data for each sensor */
				ArrayList<DataAir> dataInit  = yaml.datainit1(i);
				
				for (DataAir d: dataInit) {
					request.insert(d, "clientTEST", "dataair");
				}	
			}

			communication.stopConnection();

		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
