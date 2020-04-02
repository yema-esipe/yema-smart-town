package common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * ConvertJSON is common to server and client
 * it allows to do the four conversion that we need :
 *  - request-jsonString (1 -> client side) 
 *  - jsonString-request (2 -> server side) 
 *  - response-jsonString (3 -> server side) 
 *  - jsonString-response (4 -> client side) 
 */
public class ConvertJSON {
	private ObjectMapper mapper = new ObjectMapper();
	
	public ConvertJSON() {}
	
	public String RequestToJson(Request req) {
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(req);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	
	public Request JsontoRequest(String jsonString) {
		Request req = new Request();
		try {
			req = mapper.readValue(jsonString, Request.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return req;
	}
	
	public String ResponseToJson(Response resp) {
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(resp);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public Response JsontoResponse(String jsonString) {
		Response resp = new Response();
		try {
			resp = mapper.readValue(jsonString, Response.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}
	
}
