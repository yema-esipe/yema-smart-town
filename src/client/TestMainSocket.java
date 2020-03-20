package client;

import java.io.IOException;

public class TestMainSocket {
	public static void main(String[] args) {
		
		try {
			ClientCommunication client = new ClientCommunication();
			client.startConnection("127.0.0.1", 2222);

			client.sendMessage("select * from users"); //msg1 contient la reponse de "test socket"
			//client.sendMessage(".");
			client.stopConnection();
			
			//System.out.println(msg1 + " " + " envoy� par client");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
