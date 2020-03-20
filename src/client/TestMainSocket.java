package client;

import java.io.IOException;

public class TestMainSocket {
	public static void main(String[] args) {
		
		try {
			ClientCommunication client = new ClientCommunication();
			client.startConnection("127.0.0.1", 8888);

			String msg1 = client.sendMessage("test socker");
			String fin = client.sendMessage(".");
			client.stopConnection();
			System.out.println(msg1 + " " + " envoyé par client");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
