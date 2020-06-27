package connection;
import java.io.IOException; 
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
	private  Properties applicationProperties;

	public PropertiesFileReader() {
		applicationProperties = new Properties();
	}
	
	public void initJDBC() {
		  try (InputStream input = getClass().getClassLoader().getResourceAsStream("ressources/application.properties")) {

	            // load a properties file
			  applicationProperties.load(input);
			//  System.out.println(getProperty("url"));
	           
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
	
	public void initServer() {
		  try (InputStream input = getClass().getClassLoader().getResourceAsStream("ressources/serveconfig.properties")) {

	            // load a properties file
			  applicationProperties.load(input);
			//  System.out.println(getProperty("url"));
	           
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	}
	
	public void initSensor() {
		  try (InputStream input = getClass().getClassLoader().getResourceAsStream("ressources/aqs-config.properties")) {

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

	
	public void initVSensor() { 
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("ressources/Bollard.properties")) {

            // load a properties file
		  applicationProperties.load(input);
		//  System.out.println(getProperty("url"));
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }}
	


}
