package airsensor;

import common.DeviceAir;
import common.YamlFileReader;

public class AQSTest2 {
	public static void main(String[] args) {
		YamlFileReader simul = new YamlFileReader();
		System.out.println(simul.initIndex());
		DeviceAir device = new DeviceAir();
		device.setId(2);
		device.setAddress("Rue Saint Simon");
		device.setActive(false);
		device.setFrequency(10);
		device.setOnAlert(false);

		
		SensorOperation operation = new SensorOperation(device);
		try {
			operation.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	
	}
}
