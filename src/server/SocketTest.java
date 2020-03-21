package server;

import java.io.IOException;

public class SocketTest {
	public static void main (String[] args) {
		try {
			ServerCommunication server = new ServerCommunication();
			server.start(2595);
			server.stop(); 
		} catch (IOException e) {}
	}
}
