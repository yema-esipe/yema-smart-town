package server;

import java.io.*;
import java.net.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.Crud;

public class ServerCommunication {
	private ServerSocket serverSocket;
		
	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {}
		
	    while (true) {
	    	System.out.println("Serveur en attente");
	         new ThreadClient(serverSocket.accept()).start();
	    }
	}
	
	public void stop() throws IOException {
			serverSocket.close();
	}

	private static class ThreadClient extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private Crud crud;
 
        public ThreadClient(Socket socket) {
            this.clientSocket = socket;
        }
 
        @SuppressWarnings("unchecked")
		public void run()  {
	    try {
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());  
		JSONObject request;
		crud = new Crud();
		
		while ((request = (JSONObject) in.readObject()) != null) {
		/*if (".".equals(inputLine)) {
				JSONObject obj = new JSONObject();
				obj.put("Etat", "FINAL");
				
				JSONArray json = new JSONArray();
				json.add(obj);
				
				out.writeObject(json);
				
				break;
			}*/
			
						
			//il faut ajouter une condition - if cest un select, if cest un update/delete/insert
			String s = (String) request.get("select");
			
			JSONArray json = crud.executeSelect(s);	
			
			// envoyer
			out.writeObject(json); //on renvoie un JSON donc un Object
			out.flush();
		}
	    
		in.close();
		out.close();
		clientSocket.close();
	    } catch (IOException e) {} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    }
}
