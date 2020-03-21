package common;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONFileAccess { //classe pertinente ?
	
	public JSONFileAccess() {}
	
	public void readJSON(JSONArray json) { //méthode pour lire un objet json 
		@SuppressWarnings("unchecked")
		Iterator<JSONObject> iterator = json.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
}
