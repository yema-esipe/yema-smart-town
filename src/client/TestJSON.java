package client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
import java.io.FileReader;
import java.util.Iterator;
 
/**
 * @author Crunchify.com
 * How to Read JSON Object From File in Java?
 */
 
public class TestJSON {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("\\Users\\elisa\\git\\yema-smart-town\\src\\client\\Test.json"));
 
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;
 
			// A JSON array. JSONObject supports java.util.List interface.
			JSONArray companyList = (JSONArray) jsonObject.get("Company List");
 
			Iterator<JSONObject> iterator = companyList.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}