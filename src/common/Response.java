package common;
/**
 * Response class allows to keep useful information whose be sent by the server
 * - response_type : to know if the response is about select/update/delete/insert client request
 * - response_state : to know if the request is correctly performed
 * - values : for select request, it is the values of the database contained in a ArrayList
 * It concerned two sides (server response and client reception) --> common
 */
import java.util.ArrayList;

public class Response {
	private String response_type;
	private boolean response_state;			
	private ArrayList<Object> values;		
	
	public Response() {
		values = new ArrayList<Object>();
		response_type = "";
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

	public ArrayList<Object> getValues() {
		return values;
	}

	public void setValues(ArrayList<Object> values) {
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
