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
public class InitIHM {
	public static void main(String[] args) {
		YamlFileReader yaml = new YamlFileReader();
		CommunicationWithServer communication = new CommunicationWithServer();
		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORTClient());
			RequestsAqsClient request = new RequestsAqsClient(communication);
			DeviceConfigAir config = yaml.initDeviceConfig();

			/** sensor 1 and 2 configuration */
			for (int i = 1; i <= 2; i++) {
				config.setIdDevice(i);
				request.update(config, "clientTest", "deviceconfigair");
			}
			
			/** sensor 3 configuration reset */			
			config.setIdDevice(3); 
			config.setCo2(0); config.setCarbonMonoxide(0); config.setFinesParticules(0);
			config.setNitrogenDioxide(0); config.setSulfurDioxide(0); config.setOzone(0);
			
			request.update(config, "clientTEST", "deviceconfigair");

			for(int i = 1; i <= 3; i++) {
				/** sensor reset */
				DeviceAir device = (DeviceAir) request.selectOne(i, "clientTEST", "deviceair");
				device.setFrequency(5);
				device.setQuality(0);
				device.setActive(false);
				device.setOnAlert(false);

				request.update(device, "clientTEST", "deviceair");
				
				/**all the alerts are deleted*/
				request.delete(i, "clientTEST", "alert");
				
				/** all the data and average and deleted */
				request.delete(i, "clientTEST", "dataair");

				/** insertion of 10 data for each sensor **/
				ArrayList<DataAir> dataInit  = yaml.datainit1(i);

				for (DataAir d: dataInit) {
					request.insert(d, "clientTEST", "dataair");
				}
			}
			System.out.println("INIT IHM");

			communication.stopConnection();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
