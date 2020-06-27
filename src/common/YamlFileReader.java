package common;

import java.io.InputStream;
import java.util.ArrayList;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 * YamlFileReader allows to recover the data from a yaml file and put them in different object
 * @author elisa
 *
 */
public class YamlFileReader {

	/** recover the first ten data for simulation and put them in DataAir object */
	public ArrayList<DataAir> datainit1(int id) {
		Yaml yaml = new Yaml(new Constructor(DeviceAir.class));
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-simulation-init.yaml");		
		DeviceAir device = (DeviceAir) yaml.load(inputStream);
		
		device.setId(id);
		for (DataAir d: device.getDatas()) {
			d.setIdDeviceAir(id);
		}

		return device.getDatas();

	}
	
	/** recover the others data for simulation and put them in DataAir object */
	public ArrayList<DataAir> datainit2(int id) {
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
		
		DeviceAir device = (DeviceAir) yaml.load(inputStream);

		return device.getDatas();

	}
	
	/** recover the index data for each indicator and put them in an index object */
	public Index initIndex() {
		Yaml yaml = new Yaml(new Constructor(Index.class));
		InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-index.yaml");
		
		Index index = (Index) yaml.load(inputStream);
		return index;
	}
	
	/** recover the sensor configuration to replace the user input and put it in a DeviceConfigAir object */
	public DeviceConfigAir initDeviceConfig() {
		Yaml yaml = new Yaml(new Constructor(DeviceConfigAir.class));
		InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream("ressources/aqs-configAir.yaml");
		
		DeviceConfigAir config = (DeviceConfigAir) yaml.load(inputStream);
		
		return config;
	}
	/**load data to car */
     public Car initCarConfig() { 
		Yaml yaml = new Yaml(new Constructor(Car.class));
		InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream("ressources/AddCartest");
		
		Car config = (Car) yaml.load(inputStream);
		
		return config; }
}
