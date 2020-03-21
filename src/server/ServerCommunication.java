package server;

import java.io.*;
import java.net.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import common.Crud;

public class ServerCommunication {
	private ServerSocket serverSocket;
		
	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {}
		
	    while (true)
	         new ThreadClient(serverSocket.accept()).start();
	}
	
	public void stop() throws IOException {
			serverSocket.close();
	}

	private static class ThreadClient extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream out;
        private BufferedReader in;
        private Crud crud;
 
        public ThreadClient(Socket socket) {
            this.clientSocket = socket;
        }
 
        public void run()  {
	    try {
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
		String inputLine;
		crud = new Crud();
		//JSONParser parser = new JSONParser();
		
		while ((inputLine = in.readLine()) != null) {
			if (".".equals(inputLine)) {
				//out.println("bye");
				break;
			}
			//-> inputLine est une requete SQL, on se connecte � la BDD gr�ce au CRUD et on fait la requete (valid�)
			JSONArray json = crud.executeSelect(inputLine);
			
			//�diter l'obj JSON pour y mettre la r�ponse de la requete (apr�s un select par exemple)
			//Object obj = parser.parse(new FileReader("\\Users\\elisa\\git\\yema-smart-town\\src\\client\\Test.json")); //chemin � rectifier pour y avoir acc�s depuis n'importe quel ordi
			
			
			// envoyer
			out.writeObject(json); //on renvoie un JSON donc un Object
			out.flush();
		}
	    
		in.close();
		out.close();
		clientSocket.close();
	    } catch (IOException e) {}
	}
    }
}
