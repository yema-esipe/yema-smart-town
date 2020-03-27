package client;

import java.io.*; 
import java.net.*;
import common.ConvertJSON;
import common.Request;
import common.Response;
 
public class ClientCommunication {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in = null;
	private ConvertJSON converter;
	
	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		converter = new ConvertJSON();
	}
	
	public void sendMessage(Request req) throws IOException {
		
		String jsonRequest = converter.RequestToJson(req); 
		out.println(jsonRequest); //envoi la requete passée en paramètre au serveur
		
		String jsonResponse = in.readLine();
		Response resp = converter.JsontoResponse(jsonResponse);
		
		System.out.println(resp.toString());
		
	}
	
	public void stopConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}
}