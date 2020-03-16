package server;

import java.io.*;
import java.net.*;

import common.Crud;

public class ServerCommunication {
	private ServerSocket serverSocket;
	
	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException ex) {}
		
		while(true) {
			new ThreadClient(serverSocket.accept()).start();
		}
	}
	public void stop() throws IOException {
			serverSocket.close();
	}
	

public static class ThreadClient extends Thread {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private Crud crud;
	
	public ThreadClient(Socket socket) {
		this.clientSocket = socket;
		crud = new Crud();
	}
	
	public void run() {
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine;
			
			System.out.println("TEST");
			while((inputLine = in.readLine()) != null) {
				if (".".equals(inputLine)) {
					out.println("bye");
					break;
				}
				//se connecter à la bdd
				
				crud.executeUpdate(inputLine);
				out.println("bdd connection established");
				//Il faut renvoyer la reponse
				//out.println(inputLine);
			}
			
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {}
	}
}

}
