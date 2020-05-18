package common;

import java.io.InputStream;
import java.util.ArrayList;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

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
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-1.yaml");
		}
		if (id == 4) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-1.yaml");
		}
		if (id == 5) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-1.yaml");
		}
		if (id == 6) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-1.yaml");
		}
		if (id == 7) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-1.yaml");
		}
		if (id == 8) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-1.yaml");
		}
		if (id == 9) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-1.yaml");
		}
		if (id == 10) {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-1.yaml");
		}
		
		DeviceAir device = yaml.load(inputStream);

		return device.getDatas();

	}

	public Index initIndex() {
		Yaml yaml = new Yaml(new Constructor(Index.class));
		InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-index.yaml");
		
		Index index = yaml.load(inputStream);
		return index;
	}

}
