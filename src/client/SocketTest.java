package client;

import java.io.IOException;

public class SocketTest {
	public static void main(String[] args) throws IOException{
		
		ClientCommunication client = new ClientCommunication();
		client.startConnection("127.0.0.1", 8888);
		
		String msg1 = client.sendMessage("hello");
		String msg2 = client.sendMessage("world");
		String fin = client.sendMessage(".");
		client.stopConnection();
		System.out.println(msg1 + " " + msg2 + " envoyé par client");
	}	
}
