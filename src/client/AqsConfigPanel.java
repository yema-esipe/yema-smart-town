package client;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.Border;

import common.DeviceAir;
import common.DeviceConfigAir;
import common.Index;
import common.YamlFileReader;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;
/**
 * AqsConfigPanel is the panel that we have by clicking on 'Configurer' on the main air quality sensor panel, after to have click on a sensor
 * @author elisa
 * allow to configure the sensor : frequency and activate or disable sensor
 * allow to configure all the indicators taking account the regulation about them
 * only one connection from the old panel is used for the communication with server
 */
@SuppressWarnings("serial")
public class AqsConfigPanel extends JPanel {
	/** Common composant attributes*/
	private Image image;
	private Font titleFont;
	private Font basiqueFont;
	private Font littleFont;
	private Font buttonFont;
	private JButton back;

	/** Sensor configuration Panel attributes */
	//ON - OFF
	private JButton updateState;
	private JLabel responseState;
	private String serverResponse;
	private String change;
	//Frequency
	private JButton updateFrequency;
	private JLabel askFreqency;
	private JTextField newF;
	private JButton validateF;
	private JLabel responseFrequency;

	/**indicators configuration Panel attributes*/
	//Indicators values
	private JLabel newValueIndic;
	private JButton validateIndic;
	private JTextField valueIndic;
	private JLabel responseAlert;
	private ArrayList<JButton> indicButton;
	//index values
	private JButton selectIndic;
	private JComboBox indicators;

	/**Common attributes*/
	private DeviceAir device;
	private AqsConfigListener action;
	private CommunicationWithServer communication;
	private AqsWindows wind;
	private RequestsAqsClient request;
	private final String source;

	public AqsConfigPanel(AqsWindows wind, DeviceAir device, String serverResponse) throws IOException {
		this.wind = wind;
		this.device = device;
		this.serverResponse = serverResponse;
		communication = wind.getCommunication();
		action = new AqsConfigListener(this, device);
		request = new RequestsAqsClient(communication);
		source = "client";
		
		titleFont = new Font("Bahnschrift", Font.BOLD, 30);
		basiqueFont = new Font("Bahnschrift", Font.ITALIC, 24);
		littleFont = new Font("Bahnschrift", Font.ITALIC, 16);
		buttonFont = new Font("Bahnschrift", Font.CENTER_BASELINE, 12);

		/**
		 * Panel construction
		 */
		this.createTitlePanel();
		this.createSensorConfig();
		this.createIndexView();

		this.createIndicConfig();
		this.createReturnButton();
		this.createRulesPanel();
		
		/**
		 * Background configuration
		 */
		image=(new javax.swing.ImageIcon(getClass().getResource("/pictures/config-background.png"))).getImage();
		this.setLayout(null);
	}

	/**
	 * CreateTitlePanel allow to know what is the sensor that we configure
	 */
	public void createTitlePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.setBackground(Color.WHITE); 
		Border border = BorderFactory.createLineBorder(Color.BLACK, 4); panel.setBorder(border);

		JLabel label = new JLabel("<html> <font size=30> CAPTEUR N°" + device.getId() + "</font> </html>", JLabel.CENTER); label.setFont(titleFont); label.setForeground(Color.BLACK);
		panel.add(label); 
		panel.setBounds(50, 10, 350, 100);
		
		this.add(panel); 
	}
	
	/**
	 * createSensorConfig create the left panel, about the frequency and the state of the device
	 */
	public void createSensorConfig() {
		JPanel panelSensor = new JPanel();
		panelSensor.setLayout(new GridLayout(7, 1));
		panelSensor.setBackground(Color.BLACK); 
		Border border = BorderFactory.createRaisedBevelBorder(); panelSensor.setBorder(border);

		/** Title */
		JLabel title = new JLabel("<html> <center> Configuration du capteur* </center> </html>", JLabel.CENTER); title.setFont(titleFont); title.setForeground(Color.WHITE);
		panelSensor.add(title); 
		
		/** Sensor state */
		String s; String color;
		if (device.isActive()) {s = "ON"; color = "GREEN"; change = "Désactiver le capteur";}  else {s = "OFF"; color = "RED"; change = "Activer le capteur";}
		JLabel state = new JLabel("<html>Etat actuel : " + "<font color = " + color + "> " + s + " </font> </html>"); state.setFont(basiqueFont); state.setForeground(Color.WHITE);
		panelSensor.add(state);
		
		/** Sensor state button */
		updateState = new JButton("<html> <center>" + change + "</center> </html>");
		updateState.setBackground(Color.CYAN); updateState.setFont(basiqueFont);
		updateState.setBounds(230, 265, 150, 55); updateState.addActionListener(action);
		this.add(updateState);
		
		/** ResponseState */
		if ((serverResponse.equals("Le capteur a été désactivé")) || (serverResponse.equals("Le capteur a été activé"))) {responseState = new JLabel("<html> <font size=4> " + serverResponse + "</font> </html>"); responseState.setVisible(true);}
		else {responseState = new JLabel();}
		responseState.setFont(basiqueFont); responseState.setForeground(Color.white); 
		panelSensor.add(responseState); 
		
		/** frequency */
		JLabel frequency = new JLabel("<html>  Fréquence actuelle des <br> relevés : " + device.getFrequency() + "sec  </html>"); 
		frequency.setFont(basiqueFont); frequency.setForeground(Color.white);
		panelSensor.add(frequency);
		
		/** frequency button */
		updateFrequency = new JButton("<html> <center> Modifier</center> </html>");
		updateFrequency.setBackground(Color.CYAN); updateFrequency.setFont(basiqueFont);updateFrequency.setBounds(230, 410, 150, 30); 
		updateFrequency.addActionListener(action);
		this.add(updateFrequency);
		
		panelSensor.add(new JLabel(""));
		
		/** newFrequency */
		askFreqency = new JLabel("<html> <font size=5> Veuillez entrer une nouvelle fréquence comprise entre 1 et 10 : </font> </html>");
		askFreqency.setFont(basiqueFont); askFreqency.setForeground(Color.white); askFreqency.setVisible(false);
		panelSensor.add(askFreqency); 
		
		newF = new JTextField(); newF.setBounds(250, 480, 50, 20); newF.setVisible(false);
		this.add(newF); 

		/** newFrequency validate button*/
		validateF = new JButton("<html> <center>OK </center> </html>");
		validateF.setBackground(Color.CYAN); validateF.setFont(basiqueFont);
		validateF.setBounds(310, 530, 70, 30); validateF.setVisible(false);
		this.add(validateF);
		
		/** ResponseFrequency */
		if (serverResponse.equals("La modification a été validée")) {responseFrequency = new JLabel("<html> <font size = 4>" + serverResponse + "</font> </html>"); responseFrequency.setVisible(true);}
		else {responseFrequency = new JLabel();}
		responseFrequency.setFont(basiqueFont); responseFrequency.setForeground(Color.white);
		panelSensor.add(responseFrequency); 
		
		panelSensor.setBounds(50, 130, 352, 450);
		this.add(panelSensor); 
	}
	
	/**
	 * createIndicConfig allow to create the right panel for the indicators configuration
	 */
	public void createIndicConfig() {
		JPanel panelConfig = new JPanel();
		panelConfig.setLayout(new GridLayout(7, 1));
		panelConfig.setBackground(Color.BLACK); 
		Border border = BorderFactory.createRaisedBevelBorder(); panelConfig.setBorder(border);
		
		indicButton = new ArrayList<JButton>(); 
		DeviceConfigAir config = (DeviceConfigAir) request.selectOne(device.getId(), source, "deviceconfigair");
		
		/** Title */
		JLabel title = new JLabel("<html> <center> Configuration des seuils des indicateurs de pollution* </center> </html>", JLabel.CENTER); title.setFont(titleFont); title.setForeground(Color.WHITE);
		panelConfig.add(title);
		
		/** Alert Response */
		responseAlert = new JLabel("<html> <font color=red> Erreur - Capteur en marche </font> </html>"); responseAlert.setVisible(false);
		responseAlert.setBounds(475, 150, 200, 100); responseAlert.setFont(littleFont); this.add(responseAlert);
		
		/** Indicators config */ 
		JLabel co2 = new JLabel("Dioxyde de carbone : " + config.getCo2() + "ppm"); co2.setFont(littleFont); co2.setForeground(Color.white);
		co2.setBounds(475, 180, 300, 100); this.add(co2);
		JButton updateCo2 = new JButton("Modifier"); updateCo2.setBounds(725, 220, 70, 20); indicButton.add(updateCo2);
		
		JLabel carbonMonoxide = new JLabel("Monoxyde de carbone : " + config.getCarbonMonoxide() + "µg/m^3"); carbonMonoxide.setFont(littleFont); carbonMonoxide.setForeground(Color.white);
		carbonMonoxide.setBounds(475, 230, 300, 100); this.add(carbonMonoxide);
		JButton updateCarbonMonoxide = new JButton("Modifier"); updateCarbonMonoxide.setBounds(725, 270, 70, 20); indicButton.add(updateCarbonMonoxide);

		JLabel finesParticules = new JLabel("Particules Fines : " + config.getFinesParticules() + "µg/m^3"); finesParticules.setFont(littleFont); finesParticules.setForeground(Color.white);
		finesParticules.setBounds(475, 280, 300, 100); this.add(finesParticules);
		JButton updateFP = new JButton("Modifier"); updateFP.setBounds(725, 320, 70, 20); indicButton.add(updateFP);

		JLabel sulfurDioxide = new JLabel("Dioxyde de soufre : " + config.getSulfurDioxide() + "µg/m^3"); sulfurDioxide.setFont(littleFont); sulfurDioxide.setForeground(Color.white);
		sulfurDioxide.setBounds(805, 180, 300, 100); this.add(sulfurDioxide);
		JButton updateSD = new JButton("Modifier"); updateSD.setBounds(1035, 220, 70, 20); indicButton.add(updateSD);

		JLabel nitrogenDioxide = new JLabel("Dioxyde d'azote : " + config.getNitrogenDioxide() + "µg/m^3"); nitrogenDioxide.setFont(littleFont); nitrogenDioxide.setForeground(Color.white);
		nitrogenDioxide.setBounds(805, 230, 300, 100); this.add(nitrogenDioxide);
		JButton updateND = new JButton("Modifier"); updateND.setBounds(1035, 270, 70, 20); indicButton.add(updateND);

		JLabel ozone = new JLabel("Ozone : " + config.getOzone() + "µg/m^3"); ozone.setFont(littleFont); ozone.setForeground(Color.white);
		ozone.setBounds(805, 280, 300, 100); this.add(ozone);
		JButton updateO = new JButton("Modifier"); updateO.setBounds(1035, 320, 70, 20); indicButton.add(updateO);

		/** new value capture */ 		

		newValueIndic = new JLabel("<html> <font size=3> Nouveau seuil :  </font> </html>"); newValueIndic.setFont(basiqueFont); newValueIndic.setForeground(Color.white);
		this.add(newValueIndic);
		
		valueIndic = new JTextField(); this.add(valueIndic);
		
		validateIndic = new JButton("OK"); validateIndic.setFont(buttonFont); this.add(validateIndic); validateIndic.setBackground(Color.cyan);
		validateIndic.setMargin(new Insets(1,1,1,1)); validateIndic.addActionListener(action);
		
		for(int i = 0; i < indicButton.size(); i++) {
			this.add(indicButton.get(i));
			indicButton.get(i).setBackground(Color.cyan); indicButton.get(i).setFont(buttonFont); indicButton.get(i).setMargin(new Insets(1,1,1,1));
			indicButton.get(i).addActionListener(action);
		}
		
		panelConfig.setBounds(470, 130, 650, 450);
		this.add(panelConfig); 
	}

	/** createIndexView allow to create the array of air quality index*/
	public void createIndexView() {
		JLabel info = new JLabel("<html> <font color = red> REGLEMENTATION EN FRANCE DES INDICES DE QUALITE D'AIR </font> <br> Veuillez sélectionner un indicteur :");
		info.setBounds(475, 340, 640, 100); info.setFont(littleFont); info.setForeground(Color.white);this.add(info);
		
		Object[] elements = {"Dioxyde de carbone", "Monoxyde de carbone", "Particules Fines", "Dioxyde de soufre", "Dioxyde d'azote", "Ozone"};
		indicators = new JComboBox(elements);
		indicators.setBounds(750, 390, 150, 30); this.add(indicators);
		
		selectIndic = new JButton("OK"); selectIndic.setMargin(new Insets(1,1,1,1)); selectIndic.setFont(buttonFont); selectIndic.setBackground(Color.cyan);
		selectIndic.setBounds(950, 390, 50, 30); this.add(selectIndic); selectIndic.addActionListener(action);
		
		/** Array Configuration */
		String  arrayTitle[] = {"", " ", "  "};
		
		YamlFileReader yaml = new YamlFileReader();
		Index index = yaml.initIndex();
		HashMap<Integer, Float> indexValues = null;
		if ((serverResponse.equals("Dioxyde de carbone")) || (serverResponse.equals("Monoxyde de carbone")) || (serverResponse.equals("Particules Fines")) || (serverResponse.equals("Dioxyde de soufre")) || (serverResponse.equals("Dioxyde d'azote")) || (serverResponse.equals("Ozone"))) {
			
			if (serverResponse.equals("Dioxyde de carbone")) indexValues = index.getCo2();
			if (serverResponse.equals("Monoxyde de carbone")) indexValues = index.getCarbonMonoxide();			
			if (serverResponse.equals("Particules Fines")) indexValues = index.getFineParticules();			
			if (serverResponse.equals("Dioxyde de soufre")) indexValues = index.getSulfurDioxide();			
			if (serverResponse.equals("Dioxyde d'azote")) indexValues = index.getNitrogenDioxide();			
			if (serverResponse.equals("Ozone")) indexValues = index.getOzone();			

			Object[][] arrayIndex = {
					{"<html>Index de : <br>" + serverResponse, "Seuil minimal", "Seuil maximal"},
					{1, 0, indexValues.get(1)},
					{2, indexValues.get(1) + 1, indexValues.get(2)},
					{3, indexValues.get(2) + 1, indexValues.get(3)},
					{4, indexValues.get(3) + 1, indexValues.get(4)},
					{5, indexValues.get(4) + 1, indexValues.get(5)},
					{6, indexValues.get(5) + 1, indexValues.get(6)},
					{7, indexValues.get(6) + 1, indexValues.get(7)},
					{8, indexValues.get(7) + 1, indexValues.get(8)},
					{9, indexValues.get(8) + 1, indexValues.get(9)},
					{10, indexValues.get(9) + 1, indexValues.get(10)}

			};
			JTable array = new JTable(arrayIndex, arrayTitle);
				
			for(int i = 0; i < array.getColumnCount(); i++) {
				array.getColumn(array.getColumnName(i)).setCellRenderer(new JLabelRenderer());
			}
			
			for(int i = 0; i < array.getRowCount(); i++){
				array.setRowHeight(i, 30);
			}
			
			JPanel histo = new JPanel();
			histo.setLayout(new GridLayout(1, 1));
			histo.setForeground(Color.black);
			histo.setBounds(540, 440, 500, 130);
			histo.add(new JScrollPane(array));
			
			this.add(histo);
		}
		
	}
	
	/** createReturnButton allow to the user to go back in the map city panel*/
	public void createReturnButton() {
		back = new JButton("Retour");

		back.setBounds(1020, 15, 150, 80);
		Font returnFont = new Font("Bahnschrift", Font.BOLD, 28); back.setFont(returnFont);
		back.setForeground(java.awt.Color.white);
		back.setBackground(Color.black); back.addActionListener(action);

		this.add(back);
	}
	
	/** createRulesPanel is the creation of the last panel to inform the user about some configurations rules*/
	public void createRulesPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.setBackground(Color.WHITE); 

		JLabel label = new JLabel("<html> <font size = 3>*Toute configuration se réalise avec un capteur désactivé. <br> Un capteur ne peut être désactivé en état d'alerte.</font> </html>", JLabel.CENTER); label.setFont(titleFont); label.setForeground(Color.BLACK);
		panel.add(label); 
		panel.setBounds(800, 600, 320, 50);
		
		this.add(panel);
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage (image, 0, 0, 1205, 685, null); 
	}
	
	public JLabel getAskFreqency() {
		return askFreqency;
	}

	public JTextField getNewF() {
		return newF;
	}

	public JButton getValidateF() {
		return validateF;
	}

	public ArrayList<JButton> getIndicButton() {
		return indicButton;
	}
		
	public JLabel getNewValueIndic() {
		return newValueIndic;
	}

	public JTextField getValueIndic() {
		return valueIndic;
	}

	public JButton getValidateIndic() {
		return validateIndic;
	}

	public JButton getUpdateState() {
		return updateState;
	}

	public JButton getUpdateFrequency() {
		return updateFrequency;
	}
	
	public JLabel getResponseState() {
		return responseState;
	}
	
	public JLabel getResponseFrequency() {
		return responseFrequency;
	}
	
	public JComboBox getIndicators() {
		return indicators;
	}
	public JLabel getResponseAlert() {
		return responseAlert;
	}
	
	public String getChange() {
		return change;
	}
	
	public JButton getSelectIndic() {
		return selectIndic;
	}
	

	public JButton getBack() {
		return back;
	}

	public AqsWindows getWind() {
		return wind;
	}

	
	
}
