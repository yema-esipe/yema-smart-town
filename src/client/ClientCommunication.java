package client;

import java.io.*;
import java.net.*;

public class ClientCommunication {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	public String sendMessage(String msg) throws IOException {
		out.println(msg); //envoi la requete passé en paramètre au serveur
		String resp = in.readLine(); //censé donné une réponse du serveur 
		return resp;
	}
	
	public void stopConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}
}