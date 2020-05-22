package airsensor;

import java.io.IOException;

import common.DeviceAir;
/**
 * 
 * @author elisa
 * LaunchAlertMain is a test to see an alert launch
 * Sensor 1 is the simulator for that
 */
public class TestLaunchAlert {
public static void main(String[] args) throws IOException {
		
		DeviceAir device = new DeviceAir();
		device.setId(1);
		
		SensorOperation operation = new SensorOperation(device);
		try {
			operation.start();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
