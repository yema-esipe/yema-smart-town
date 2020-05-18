package client;

import java.io.IOException;

import common_aqs_client.CommunicationWithServer;



public class ClientTest {
	public static void main(String[] args) throws IOException {
		CommunicationWithServer communication = new CommunicationWithServer();
		communication.startConnection(communication.getADDRESS(), communication.getPORTClient());
		
		AqsWindows test = new AqsWindows(communication);
		
	}
}
