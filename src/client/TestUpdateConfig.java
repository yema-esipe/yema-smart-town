package client;

import java.io.IOException;

import common.DeviceAir;
import common.DeviceConfigAir;
import common.YamlFileReader;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;

/**
 * UpdateConfigMain is a technical test about the sensor configuration 
 * @author elisa
 * To replace the user data, configuration is recorded in yaml file in resources called 'aqs-configAir.yaml'
 */

public class TestUpdateConfig {
	public static void main(String[] args) {
		YamlFileReader yaml = new YamlFileReader();
		CommunicationWithServer communication = new CommunicationWithServer();
		
		try {
			communication.startConnection(communication.getADDRESS(), communication.getPORTClient());
			RequestsAqsClient request = new RequestsAqsClient(communication);
			DeviceConfigAir config = yaml.initDeviceConfig();
			
			for (int i = 1; i <= 3; i++) {
				config.setIdDevice(i);
				request.update(config, "clientTEST", "deviceconfigair");
				
				DeviceAir device = (DeviceAir) request.selectOne(i, "clientTEST", "deviceair");
				device.setFrequency(5);
				
				request.update(device, "ClientTEST", "deviceair");
			}
			
			communication.stopConnection();

		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
}
