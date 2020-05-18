package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextField;

import common.DeviceAir;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;

public class AqsCityListener implements ActionListener {
	private AqsCityPanel panel;
	private CommunicationWithServer communication;
	private RequestsAqsClient request;
	private final String source;

	private JButton addressOK;
	private JTextField addressValue;
	
	private JButton idOK;
	private JTextField idValue;

	public AqsCityListener(AqsCityPanel panel) {
		this.panel = panel;
		communication = panel.getCommunication();
		request = new RequestsAqsClient(communication);
		source = "client";
	}
	
	public void actionPerformed(ActionEvent event) {

		try {
			/** AddSensor Button */
			if ((JButton)event.getSource() == panel.getAddSensor()) {

				panel.getAddSensorPanel().setVisible(true);
				addressValue = panel.getAddressValue();	addressValue.setVisible(true);
				addressOK = panel.getAddressOK(); addressOK.setVisible(true);
				panel.getPosition().setVisible(true);
				
				panel.getDeleteSensorPanel().setVisible(false);
			}

			/** Sensor insertion */
			if ((JButton)event.getSource() == addressOK) {
				panel.setIsCheckAddress(true);
			}
			
			/** position button*/
			if ((JButton)event.getSource() == panel.getPosition()) {
				if (panel.isCheckAddress()) {
					DeviceAir device = new DeviceAir(); device.setId(panel.getNbSensor() + 1);
					device.setActive(false); device.setAddress(addressValue.getText()); device.setFrequency(10); 
					device.setOnAlert(false); device.setQuality(0);
					request.insert(device, source, "deviceair");
					panel.getWind().setVisible(false);
					AqsNewSensor windPostion = new AqsNewSensor(panel, panel.getNbSensor());
				} else {
					panel.getWind().setContentPane(new AqsCityPanel(panel.getWind(), "Adresse non saisie"));
				}
			}
			
			/** DeleteSensor Button*/
			if ((JButton)event.getSource() == panel.getDeleteSensor()) {
				panel.getDeleteSensorPanel().setVisible(true);
				idValue = panel.getIdValue(); idValue.setVisible(true);
				idOK = panel.getIdOK(); idOK.setVisible(true);
				
				panel.getAddSensorPanel().setVisible(false); panel.getPosition().setVisible(false);
				panel.getAddressOK().setVisible(false); panel.getAddressValue().setVisible(false);
			}
			
			if ((JButton)event.getSource() == idOK) {
				int id = Integer.valueOf(idValue.getText());
				if (id > panel.getNbSensor()) {
					panel.getWind().setContentPane(new AqsCityPanel(panel.getWind(), "ERROR - ID non valide"));
				} else {
					DeviceAir device = (DeviceAir) request.selectOne(id, source, "deviceair");
					if (device.isActive()) {
						panel.getWind().setContentPane(new AqsCityPanel(panel.getWind(), "ERROR - Capteur en marche"));
					} else {
						request.delete(device, source, "deviceair");
						panel.getWind().setContentPane(new AqsCityPanel(panel.getWind(), "Capteur supprimé !"));
					}
				}
			}

			/** Return button */
			if ((JButton)event.getSource() == panel.getBack()) { 
				panel.getWind().setVisible(false);
				AqsWindows map = new AqsWindows(panel.getWind().getCommunication());

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
