package server;

import java.io.*;
import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
 
        public ThreadClient(Socket socket) {
            this.clientSocket = socket;
        }
 
        public void run()  {
	    try {
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  
		String inputLine;
		JSONParser parser = new JSONParser();
		
		while ((inputLine = in.readLine()) != null) {
			if (".".equals(inputLine)) {
				//out.println("bye");
				break;
			}
			
			Object obj = parser.parse(new FileReader("\\Users\\elisa\\git\\yema-smart-town\\src\\client\\Test.json"));
			 			
		   out.writeObject(obj); //on renvoie un JSON donc un Object
		   out.flush();
		}
	    
		in.close();
		out.close();
		clientSocket.close();
	    } catch (IOException e) {} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    }
}
