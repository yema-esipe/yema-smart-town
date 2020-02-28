package common;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
	private  Properties applicationProperties;

	public PropertiesFileReader() {
		applicationProperties = new Properties();
	}
	
	public void init() {
		  try (InputStream input = getClass().getClassLoader().getResourceAsStream("ressources/application.properties")) {

	            // load a properties file
			  applicationProperties.load(input);
	           
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
	
	public String getProperty(String key) {
		return applicationProperties.getProperty(key);
	}


}
