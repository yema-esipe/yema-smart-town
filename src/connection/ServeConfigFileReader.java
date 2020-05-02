package connection;

import java.io.IOException; 
import java.io.InputStream;
import java.util.Properties;

public class ServeConfigFileReader {

	private  Properties applicationProperties;

	public ServeConfigFileReader() {
		applicationProperties = new Properties();
	}
	
	public void init() {
		  try (InputStream input = getClass().getClassLoader().getResourceAsStream("ressources/serveconfig.properties")) {
			  // load a properties file
			  applicationProperties.load(input);
			  //  System.out.println(getProperty("url"));   
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
	
	public String getProperty(String key) {
		return applicationProperties.getProperty(key);
	}
	
}
