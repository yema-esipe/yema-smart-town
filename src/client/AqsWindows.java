package client;

import java.io.IOException;
import javax.swing.JFrame;
import common_aqs_client.CommunicationWithServer;

/**
 * AqsWindows is the common windows for all aqs panel
 * @author elisa
 * the communication with the server is unique and recovered thanks to this class
 */

@SuppressWarnings("serial")
public class AqsWindows extends JFrame{
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;
	private CommunicationWithServer communication;
		
	public AqsWindows(CommunicationWithServer communication) throws IOException {
		super();
		this.communication = communication;
		this.setSize(WIDTH, HEIGHT);
		
		this.setContentPane(new AqsMapPanel(this));
					
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public CommunicationWithServer getCommunication() {
		return communication;
	}

}
