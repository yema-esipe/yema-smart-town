package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import common.DeviceAir;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;

public class AqsMapListener implements ActionListener {
	private AqsMapPanel panel;
	private CommunicationWithServer communication;
	private JButton consult;
	private JButton config;
	private DeviceAir device;
	private final String source;
	
	public AqsMapListener(AqsMapPanel panel) {
		this.panel = panel;
		this.communication = panel.getWind().getCommunication();
		source = "client";
	}

	public void actionPerformed(ActionEvent event) {

		try {
			RequestsAqsClient request = new RequestsAqsClient(communication);
			
			for (int i = 0; i < panel.getNbSensor(); i++) {
				if ((JButton)event.getSource() == panel.getSensors().get(i)) { //afficher les informations du capteur i

					JPanel infoSensor = panel.getInfoSensor();
					infoSensor.setOpaque(true);
					infoSensor.setBounds(900, 170, 250, 340);

					device = (DeviceAir) request.selectOne(i + 1, source, "deviceair");

					ArrayList<JLabel> labels = panel.getLabels();
					labels.get(0).setText("Capteur n° " + device.getId());
					Font f = new Font("Bahnschrift", Font.BOLD, 20); labels.get(0).setFont(f);


					labels.get(1).setText("<html> <center> Adresse : " +  device.getAddress());
					
					String s; String color;
					if (device.isActive()) {s = "ON"; color = "GREEN";}  else {s = "OFF"; color = "RED";}
					
					labels.get(2).setText("<html> <center> Etat actuel : " + "<font color = " + color + "> " + s + " </font>  </center></html>");

					if (device.isOnAlert()) {
						panel.getAlertImage().setVisible(true);
					} else {
						panel.getAlertImage().setVisible(false);
					}
					
					consult = panel.getConsult(); consult.addActionListener(this);
					consult.setVisible(true);

					config = panel.getConfig(); config.addActionListener(this);
					config.setVisible(true);

				}
			} 


			if ((JButton)event.getSource() == consult) {
				panel.getWind().setContentPane(new AqsConsultPanel(panel.getWind(), device));
			}
			
			if ((JButton)event.getSource() == config) {
				panel.getWind().setContentPane(new AqsConfigPanel(panel.getWind(), device, ""));
			}

			if ((JButton)event.getSource() == panel.getBack()) {
				//retourner sur la page précédente
			}

			if ((JButton)event.getSource() == panel.getCity()) {
				panel.getWind().setContentPane(new AqsCityPanel(panel.getWind(), ""));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
