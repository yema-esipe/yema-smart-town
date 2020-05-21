package client;

import java.io.IOException;

import common_aqs_client.CommunicationWithServer;

public class AqsComLaunch {
	public AqsComLaunch() throws IOException {
		CommunicationWithServer communication = new CommunicationWithServer();
		communication.startConnection(communication.getADDRESS(), communication.getPORTClient());
		
		AqsWindows aqs = new AqsWindows(communication);
	}
}
