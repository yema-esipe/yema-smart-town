package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import common.DeviceAir;
import common.DeviceConfigAir;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;
/**
 * 
 * @author elisa
 * AqsConfigListener is in relation with AqsConfigPanel -> represents its ActionListener for all the button of the panel
 */
public class AqsConfigListener implements ActionListener {
	private JButton validateF;
	private JTextField value;
	private int indicator;
	private DeviceConfigAir config;
	
	private CommunicationWithServer com;
	private DeviceAir device;
	private AqsConfigPanel panel;
	private final String source;

	public AqsConfigListener(AqsConfigPanel panel, DeviceAir device) {
		this.panel = panel;
		this.device = device;
		source = "client";
	}
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent event) {
		com = panel.getWind().getCommunication();

		try {
			RequestsAqsClient request = new RequestsAqsClient(com);

			/** First Panel */
			
			/** Sensor state update -> check if the sensor is on alert or not */
			if ((JButton)event.getSource() == panel.getUpdateState()) { 
				String change = panel.getChange();
				if (change.equals("Désactiver le capteur")) {
					if (device.isOnAlert()) {
						JLabel response = panel.getResponseState(); response.setVisible(true);
						response.setText("<html> <font size=4> Impossible - Capteur en alerte </font> </html>");
					} else {
						device.setActive(false); request.update(device, source, "deviceair");
						panel.getWind().setContentPane(new AqsConfigPanel(panel.getWind(), device, "Le capteur a été désactivé"));
					}

				} else if (change.equals("Activer le capteur")) {
					device.setActive(true);	request.update(device, source, "deviceair");					
					panel.getWind().setContentPane(new AqsConfigPanel(panel.getWind(), device, "Le capteur a été activé"));
				}
			} 

			/** Sensor frequency update asking -> check if the sensor is active or not */
			if ((JButton)event.getSource() == panel.getUpdateFrequency()) {
				JLabel newValue = panel.getAskFreqency();
				newValue.setVisible(true);
				
				if (device.isActive()) {
					newValue.setText("<html> <font size=5> Erreur - Capteur en marche </font> </html>");
				} else {
					value = panel.getNewF();
					value.setVisible(true);

					validateF = panel.getValidateF();
					validateF.setVisible(true); validateF.addActionListener(this);
				}
			}
			
			/** sensor frequency update */
			if ((JButton)event.getSource() == validateF) {
				int frequency = Integer.valueOf(value.getText()); System.out.println(frequency);
				if ((frequency > 0) && (frequency < 11)) {
					device.setFrequency(frequency);
					request.update(device, source, "deviceair");
					panel.getWind().setContentPane(new AqsConfigPanel(panel.getWind(), device, "La modification a été validée"));
				} else {
					JLabel response = panel.getResponseFrequency(); response.setVisible(true);
					response.setText("<html> <font size=4> Erreur de saisie </font> </html>");
				}
			}
			
			/** Second Panel */
			
			/** Sensor indicator update asking -> check if the sensor is active or not */
			ArrayList<JButton> indicButton = panel.getIndicButton();
			for(int i = 0; i < indicButton.size(); i++) {
				if ((JButton)event.getSource() == indicButton.get(i)) {
					if (device.isActive()) panel.getResponseAlert().setVisible(true);
					else {
						config = (DeviceConfigAir) request.selectOne(device.getId(), source, "deviceconfigair");
						if (i == 0) {
							panel.getNewValueIndic().setBounds(500, 220, 100, 70);
							panel.getValueIndic().setBounds(590, 240, 40, 25);
							panel.getValidateIndic().setBounds(670, 240, 40, 25);
							indicator = 0;
						}
						
						if (i == 1) {
							panel.getNewValueIndic().setBounds(500, 270, 100, 70);
							panel.getValueIndic().setBounds(590, 290, 40, 25);
							panel.getValidateIndic().setBounds(670, 290, 40, 25);
							indicator = 1;
						}
						
						if (i == 2) {
							panel.getNewValueIndic().setBounds(500, 320, 100, 70);
							panel.getValueIndic().setBounds(590, 340, 40, 25);
							panel.getValidateIndic().setBounds(670, 340, 40, 25);
							indicator = 2;
						}
						
						if (i == 3) {
							panel.getNewValueIndic().setBounds(830, 220, 100, 70);
							panel.getValueIndic().setBounds(920, 240, 40, 25);
							panel.getValidateIndic().setBounds(1000, 240, 40, 25);
							indicator = 3;
						}
						
						if (i == 4) {
							panel.getNewValueIndic().setBounds(830, 270, 100, 70);
							panel.getValueIndic().setBounds(920, 290, 40, 25);
							panel.getValidateIndic().setBounds(1000, 290, 40, 25);
							indicator = 4;
						}
						
						if (i == 5) {
							panel.getNewValueIndic().setBounds(830, 320, 100, 70);
							panel.getValueIndic().setBounds(920, 340, 40, 25);
							panel.getValidateIndic().setBounds(1000, 340, 40, 25);
							indicator = 5;
						}
					}
				}
			}
			
			/** Sensor indicator update */
			if ((JButton)event.getSource() == panel.getValidateIndic()) {
				int value = Integer.valueOf(panel.getValueIndic().getText());
				if (indicator == 0) config.setCo2(value);
				if (indicator == 1) config.setCarbonMonoxide(value); 
				if (indicator == 2) config.setFinesParticules(value); 
				if (indicator == 3) config.setSulfurDioxide(value); 
				if (indicator == 4) config.setNitrogenDioxide(value); 
				if (indicator == 5) config.setOzone(value); 
				
				request.update(config, source, "deviceconfigair");
				panel.getWind().setContentPane(new AqsConfigPanel(panel.getWind(), device, ""));
			}
			
			/**array configuration */
			
			if ((JButton)event.getSource() == panel.getSelectIndic()) {
				String indicator = (String) panel.getIndicators().getSelectedItem();
				panel.getWind().setContentPane(new AqsConfigPanel(panel.getWind(), device, indicator));
			}
			
			/** Return button */
			if ((JButton)event.getSource() == panel.getBack()) { 
				panel.getWind().setVisible(false);
				AqsWindows map = new AqsWindows(panel.getWind().getCommunication());
			}

		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
