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
		
	 //creer un JSON qui a comme clé select et valeur la requete
		obj.put(resq, msg);
		
		//gerer les erreurs
		
		return obj;
	}
	
}
