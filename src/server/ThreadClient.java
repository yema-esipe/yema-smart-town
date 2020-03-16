package server;

import java.io.*;
import java.net.*;

public class ThreadClient extends Thread {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public ThreadClient(Socket socket) {
		this.clientSocket = socket;
	}
	
	public void run() {
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine;
			
			while((inputLine = in.readLine()) != null) {
				if (".".equals(inputLine)) {
					out.println("bye");
					break;
				}
				out.println(inputLine);
			}
			
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {}
	}
}
