package server;

import java.io.IOException;

public class SocketTest {
	public static void main (String[] args) {
		try {
			ServerCommunication server = new ServerCommunication();
			server.start(3775);
			server.stop(); 
		} catch (IOException e) {}
	}
}
