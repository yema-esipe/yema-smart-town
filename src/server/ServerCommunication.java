package server;

import java.io.*;
import java.net.*;

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
}
