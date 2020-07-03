package client;

import java.io.*; 
import java.net.*;
import common.ConvertJSON;
import common.Request;
import common.Response;
/**
 * ClientCommunication implements client socket connection with the server
 * */
public class ClientCommunication {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in = null;
	private ConvertJSON converter;
	
/** 
 * startConnection open the connection with the server.
 * ip and port are those of the server
 */
	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		converter = new ConvertJSON();
	}
	
/**
 * sendMessage send the request of the client to the server
 * First, 'req' is translate into a JSON stream thanks to the converter object (ref. ConvertJSON)
 * Secondly, this stream is sending
 * Then, 'resp' is the response of the server, it is converting too, to have an new object : Response
 * To finish, sendMessage display this object 
 */
	public void sendMessage(Request req) throws IOException {
		
		String jsonRequest = converter.RequestToJson(req); 
		out.println(jsonRequest);
		
		String jsonResponse = in.readLine();
		Response resp = converter.JsontoResponse(jsonResponse);
		
		System.out.println(resp.toString());
		
	}
public Response sendMessageresp(Request req) throws IOException {
		
		String jsonRequest = converter.RequestToJson(req); 
		out.println(jsonRequest);
		
		String jsonResponse = in.readLine();
		Response resp = converter.JsontoResponse(jsonResponse);
		
		return resp;
	}
	
	/* test d'envoi de message en stringjson*/
	public void send(String msg) throws IOException {
		out.println(msg);	
	}
	/* test d'envoi de message en stringjson*/
	public String get() throws IOException {
		String jsonResponse = in.readLine();	
		return jsonResponse;	
	}
	
/**
 * stopConnection is called when client doesn't have request anymore
 * connection between server and client is closed
 */
	 
	public void stopConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}
}