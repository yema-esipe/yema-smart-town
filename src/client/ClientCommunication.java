package client;

import java.io.*;
import java.net.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.JSONFileAccess;

public class ClientCommunication {
	private Socket clientSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in = null;
	private JSONFileAccess file;
	
	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
		file = new JSONFileAccess();
	}
	
	public void sendMessage(String msg) throws IOException {
		//etape de transition msg en JSON
		JSONObject obj = new JSONObject();
		obj = file.convertJSON(msg); //la requete est mise sous JSON
		
		
		out.writeObject(obj); //envoi la requete passée en paramètre au serveur
		Object resp = null;
		try {
			resp = in.readObject(); // donne la réponse du serveur
			
			if (resp == null) System.err.println("Erreur reponse null");
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