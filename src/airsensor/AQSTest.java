package airsensor;


import common.DeviceAir;
import common.YamlFileReader;

public class AQSTest {
	public static void main(String[] args) {
		DeviceAir device = new DeviceAir();
		device.setId(1);
		device.setAddress("Rue Cheret");
		device.setActive(false);
		device.setFrequency(10);
		device.setOnAlert(false);

		YamlFileReader simul = new YamlFileReader();
		System.out.println(simul.initData(1));

		
		SensorOperation operation = new SensorOperation(device);
		try {
			operation.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
