package airsensor;

import common.DeviceAir;

public class AQSTest {

	public static void main(String[] args) {
		YamlFileReader simul = new YamlFileReader();
		System.out.println(simul.initData(1));
		//System.out.println(device.getDatas());
		
		DeviceAir device = new DeviceAir();
		device.setId(1);
		device.setAddress("Champigny");
		device.setFrequency(10);
		device.setOnAlert(false);

		SensorOperation operation = new SensorOperation(device);
		try {
			operation.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
