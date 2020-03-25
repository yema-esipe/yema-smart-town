package server;

import java.io.IOException;

public class SocketTest {
	public static void main (String[] args) {
		try {
			ServerCommunication server = new ServerCommunication();
			server.start(3996);
			System.out.println("et ici ?");
			server.stop(); 
		} catch (IOException e) {}
	}
}
