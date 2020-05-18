package client;

import java.io.IOException;
import javax.swing.JFrame;

import common.DeviceAir;
import common_aqs_client.CommunicationWithServer;

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
		//this.setContentPane(new AqsCityPanel(this));

		/* A supprimer 
		DeviceAir device = new DeviceAir();
		device.setActive(false);
		device.setAddress("Rue Cheret");
		device.setFrequency(10);
		device.setOnAlert(false);
		this.setContentPane(new AqsConfigPanel(this, device, ""));	*/
			
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//communication.stopConnection();

		this.setVisible(true);

	}

	public CommunicationWithServer getCommunication() {
		return communication;
	}

}
