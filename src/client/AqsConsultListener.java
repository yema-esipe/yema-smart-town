package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

public class AqsConsultListener implements ActionListener {
	private AqsConsultPanel panel;
	
	public AqsConsultListener(AqsConsultPanel panel) {
		this.panel = panel;
	}
	
	public void actionPerformed(ActionEvent event) {
		
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
