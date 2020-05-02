package connection;
import java.io.IOException; 
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {
	private  Properties applicationProperties;

	public PropertiesFileReader() {
		applicationProperties = new Properties();
	}
	
	public void init(String path) {
		  try (InputStream input = getClass().getClassLoader().getResourceAsStream(path)) {

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
