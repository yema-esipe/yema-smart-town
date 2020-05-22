package airsensor;

import java.io.IOException;

import common.DeviceAir;
/**
 * 
 * @author elisa
 * RaisedAlertMain is a test to see an alert raised
 * sensor 2 is the simulator for that
 */
public class TestRaisedAlert {
public static void main(String[] args) throws IOException {
		
		DeviceAir device = new DeviceAir();
		device.setId(2);
		
		SensorOperation operation = new SensorOperation(device);
		try {
			operation.start();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
