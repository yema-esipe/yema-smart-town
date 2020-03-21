package client;

import java.io.*;
import java.net.*;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.JSONFileAccess;

public class ClientCommunication {
	private Socket clientSocket;
	private PrintWriter out;
	private ObjectInputStream in = null;
	private JSONFileAccess file;
	
	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new ObjectInputStream(clientSocket.getInputStream());
		file = new JSONFileAccess();
	}
	
	public void sendMessage(String msg) throws IOException {
		out.println(msg); //envoi la requete passée en paramètre au serveur
		Object resp = null;
		try {
			resp = in.readObject(); // donne la réponse du serveur
			
			if (resp == null) System.err.println("Erreur");
			else {
				JSONArray json = (JSONArray) resp;
				
				//affichage de la réponse
				file.readJSON(json);
				
			}
		} catch(ClassNotFoundException e) {
		    System.err.println("Classe inconnue : ");
		    System.exit(1);
		}
	}
	
	public void stopConnection() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}
}