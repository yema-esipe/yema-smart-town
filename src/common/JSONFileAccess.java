package common;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONFileAccess {
	
	public JSONFileAccess() {}
	
	public void readJSON(JSONArray json) { //méthode pour lire un objet json (modification à faire pr personaliser ce qu'on veut voir)
		//JSONArray companyList = (JSONArray) jsonObject.get("Company List");
		
		Iterator<JSONObject> iterator = json.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
	public void editJSON(JSONObject jsonObject) { //methode pour modifier un objet json
		
	}
}
