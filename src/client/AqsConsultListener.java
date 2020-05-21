package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

/**
 * 
 * @author elisa
 * AqsConsultListener is in relation with AqsConsultPanel -> represents its ActionListener for the only one button of the panel
 */
public class AqsConsultListener implements ActionListener {
	private AqsConsultPanel panel;
	
	public AqsConsultListener(AqsConsultPanel panel) {
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent event) {
			
			/** allows to go back on the map city view*/
			if ((JButton)event.getSource() == panel.getBack()) { 
				try {
					panel.getWind().setVisible(false);
					AqsWindows map = new AqsWindows(panel.getWind().getCommunication());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 

			
	}

}
