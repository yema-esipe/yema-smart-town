package airsensor;

import java.io.InputStream;
import java.util.ArrayList;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import common.DataAir;
import common.DeviceAir;

public class YamlFileReader {

	public ArrayList<DataAir> initData(int id) {
		Yaml yaml = new Yaml(new Constructor(DeviceAir.class));
		InputStream inputStream = null;
		
		if (id == 1) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-1.yaml");
		}
		if (id == 2) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-2.yaml");
		}
		if (id == 3) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-3.yaml");
		}
		if (id == 4) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-4.yaml");
		}
		if (id == 5) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-5.yaml");
		}
		
		DeviceAir device = yaml.load(inputStream);

		return device.getDatas();



	}


}
