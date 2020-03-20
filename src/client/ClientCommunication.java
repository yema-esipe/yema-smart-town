package client;

import java.io.*;
import java.net.*;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ClientCommunication {
	private Socket clientSocket;
	private PrintWriter out;
	private ObjectInputStream in = null;
	
	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new ObjectInputStream(clientSocket.getInputStream());
	}
	
	public void sendMessage(String msg) throws IOException {
		out.println(msg); //envoi la requete passé en paramètre au serveur
		Object resp = null;
		try {
			resp = in.readObject(); //censé donné une réponse du serveur
			
			if (resp == null) System.err.println("Erreur");
			else {
				JSONObject jsonObject = (JSONObject) resp;
				//affichage de la réponse
				
				JSONArray companyList = (JSONArray) jsonObject.get("Company List");
				Iterator<JSONObject> iterator = companyList.iterator();
				while (iterator.hasNext()) {
					System.out.println(iterator.next()); 
				}
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