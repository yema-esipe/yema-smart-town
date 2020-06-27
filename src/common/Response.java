package common;
/**
 * Response class allows to keep useful information whose be sent by the server

 * - response_type : to know if the response is about select/update/delete/insert or more specific request
 * - response_state : to know if the request is correctly performed
 * - values : for select request, it is the values of the database contained in a ArrayList, before convert in json
 */
import java.util.ArrayList;

public class Response {
	private String response_type;
	private boolean response_state;			
	private ArrayList<String> values;
	
	public Response() {
		response_type = "";
		values = null;
	}

	public String getResponse_type() {
		return response_type;
	}

	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}

	public boolean getResponse_state() {
		return response_state;
	}

	public void setResponse_state(boolean response_state) {
		this.response_state = response_state;
	}

	public ArrayList<String> getValues() {
		return values;
	}

	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
		

	public String toString() {
		if (this.response_type.equals("select"))
			return "server_response : " + response_state + '\n' + "values : "+ values.toString();
		if ((this.response_type.equals("update")) || (response_type.equals("insert")) || (response_type.equals("delete")))
			return "server_response_state : " + response_state;
		if (this.response_type.equals("end"))
			return "server_response_state : " + response_state;
		else
			return "server_response : Problème dans la requête";
	}
	
}
