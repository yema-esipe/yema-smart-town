package airsensor;

import java.io.IOException;

import common.DeviceAir;
/**
 * 
 * @author elisa
 * ChangeAlertMain is a test to see an alert launch and change
 * Sensor 3 is the simulator for that
 */
public class TestChangeAlert {
public static void main(String[] args) throws IOException {
		
		DeviceAir device = new DeviceAir();
		device.setId(3);
		
		SensorOperation operation = new SensorOperation(device);
		try {
			operation.start();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
