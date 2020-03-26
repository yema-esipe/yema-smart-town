package common;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONFileAccess { //classe pertinente ?
	
	public JSONFileAccess() {}
	
	public void readJSON(JSONArray json) { //méthode pour lire un objet json 
		@SuppressWarnings("unchecked")
		
		Iterator<JSONObject> iterator = json.iterator(); //contient les objets json du array
		
		while (iterator.hasNext()) {
			JSONObject obj = iterator.next();
			if (obj.containsKey("fin")) {
				System.out.println(obj.get("fin"));
			} else if (obj.containsKey("update")) {
				System.out.println(obj.get("update"));
			} else {
				System.out.println(obj);
			}
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject convertJSON(String msg) {
		JSONObject obj = new JSONObject();

		String resq = "";
		int i = 0;
		
		//definir si cest un select ou update/insert/delete (on récupère le 1er mot)
		while (msg.charAt(i) != ' ') {
			resq = resq + msg.charAt(i);
			i++;
		}

		if ((resq.equals("insert")) || (resq.equals("update")) || (resq.equals("delete"))) {
			resq = "update";
		}
		//la clé du json nous dit si cest un executeupdate ou bien un executeselect
		obj.put(resq, msg);
				
		return obj;
	}
	
}
