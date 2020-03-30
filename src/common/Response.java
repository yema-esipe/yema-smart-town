package common;

import java.util.ArrayList;

public class Response {
	private String response_type; 			//type de la requete initial : update/insert/delete/select
	private String response_state;			//reponse d'un update/insert/delete/select 
	private ArrayList<String> values;		//reponse d'un select
	
	public Response() {
		values = new ArrayList<String>();
		response_state = "";
		response_type = "";
	}

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}

	public String getResponse_state() {
		return response_state;
	}

	public void setResponse_state(String response_state) {
		this.response_state = response_state;
	}

	public ArrayList<String> getValues() {
		return values;
	}

	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
		
	public String toString() {
		if (this.response_type.equals("SELECT"))
			return "server_response : " + response_state + '\n' + "values : "+ values;
		if ((this.response_type.equals("UPDATE")) || (response_type.equals("INSERT")) || (response_type.equals("DELETE")))
			return "server_response_state : " + response_state;
		if (this.response_type.equals("end"))
			return "server_response_state : " + response_state;
		else
			return "server_response : Problème dans la requête";
	}
	
}
