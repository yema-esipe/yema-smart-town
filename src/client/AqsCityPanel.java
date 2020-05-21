package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;

/**
 * AqsCityPanel is the panel that we have by clicking on 'Info Ville' on the main air quality sensor panel
 * @author elisa
 * Here we have some information about the AQS (air quality sensor) of the city 
 * Here we can add or delete sensor 
 * only one connection from the old panel is used for the communication with server
 */
@SuppressWarnings("serial")
public class AqsCityPanel extends JPanel {
	/**Common composant attribute */
	private Image image;
	private Font titleFont;
	private Font basiqueFont;
	private JButton back;
	private int nbSensor;
	private JPanel responsePanel;
	private String response;

	/** Add sensor configuration attribute */
	private JButton addSensor;
	private JButton addressOK;
	private JTextField addressValue;
	private boolean checkAddress;
	private JButton position;
	private JPanel addSensorPanel;

	/** delete sensor configuration attribute*/
	private JPanel deleteSensorPanel;
	private JButton deleteSensor;
	private JButton idOK;
	private JTextField idValue;

	/** Common */
	private CommunicationWithServer communication;
	private AqsWindows wind;
	private RequestsAqsClient request;
	private AqsCityListener action;

	public AqsCityPanel(AqsWindows wind, String response) {
		this.wind = wind;
		this.response = response;
		communication = wind.getCommunication();
		action = new AqsCityListener(this);
		
		request = new RequestsAqsClient(communication);
		titleFont = new Font("Bahnschrift", Font.BOLD, 40);
		basiqueFont = new Font("Bahnschrift", Font.BOLD, 22);

		/**
		 * panel construction
		 */
		this.createBackButton();
		this.createLeftView();
		this.createResponsePanel();

		this.createRightButton();
		this.createAddSensorPanel();
		this.createDeleteSensorPanel();

		/**
		 * Background configuration
		 */
		image=(new javax.swing.ImageIcon(getClass().getResource("/pictures/city-background.png"))).getImage();
		this.setLayout(null);

	}

	/**
	 * createLeftView recover all information we need for left view
	 * create the left panel
	 */
	public void createLeftView() {
		ArrayList<String> informationsDevice = request.getInformationDeviceAir();
		nbSensor = Integer.valueOf(informationsDevice.get(0));
		JLabel title = new JLabel("<html> Etat actuel de <br> YEMA-TOWN </html>"); title.setFont(titleFont); title.setForeground(Color.white);
		title.setBounds(100, 30, 400, 150); this.add(title);

		JLabel nbSensor = new JLabel("- Nombre de capteur(s) en ville : " + informationsDevice.get(0)); nbSensor.setFont(basiqueFont); nbSensor.setForeground(Color.white);
		nbSensor.setBounds(50, 180, 500, 50); this.add(nbSensor);

		JLabel nbActivedSensor = new JLabel("- Nombre de capteur(s) activé(s) en ville : " + informationsDevice.get(1)); nbActivedSensor.setFont(basiqueFont); nbActivedSensor.setForeground(Color.white);
		nbActivedSensor.setBounds(50, 220, 500, 50); this.add(nbActivedSensor);

		JLabel average = new JLabel("<html> - Indice de la qualité de l'air en ville : " + informationsDevice.get(2) + " / 10 <br> Pour RAPPEL : </html>"); average.setFont(basiqueFont); average.setForeground(Color.white);
		average.setBounds(50, 260, 500, 70); this.add(average);

		ImageIcon index = new ImageIcon(getClass().getResource("/pictures/indexImage.png"));
		JLabel imageIndex = new JLabel(index);imageIndex.setBounds(60, 340, 400, 180); this.add(imageIndex);

		JLabel information = new JLabel("<html> <font size=4>*L’indice de la qualité de l’air est calculé en fonction de la réglementation actuelle en France. A l’échelle de la ville, il s’agit de la moyenne des indices par capteur </font> </html>");
		information.setFont(basiqueFont); information.setForeground(Color.white); information.setBounds(50, 480, 385, 200); this.add(information);
	}
	
	/**
	 * createResponsePanel is not visible firstly but appear by clicking on different button in order to inform the user
	 */
	public void createResponsePanel() {
		responsePanel = new JPanel(); responsePanel.setLayout(new GridLayout(1,1)); responsePanel.setBackground(Color.WHITE);
		responsePanel.setBounds(700, 180, 170, 30); 
	}
	
	/**
	 * createRightButton correspond to add sensor button and delete sensor button
	 */
	public void createRightButton() {
		addSensor = new JButton("<html> <font size = 5> <center> Ajouter un capteur </center> </font> </html>"); addSensor.setBackground(Color.white); addSensor.setForeground(Color.RED); addSensor.setFont(basiqueFont);
		addSensor.setBounds(700, 220, 170, 60); this.add(addSensor); addSensor.addActionListener(action);

		deleteSensor = new JButton("<html> <font size = 5> <center> Supprimer un capteur </center> </font> </html>"); deleteSensor.setBackground(Color.white); deleteSensor.setForeground(Color.RED); deleteSensor.setFont(basiqueFont);
		deleteSensor.setBounds(900, 220, 170, 60); this.add(deleteSensor); deleteSensor.addActionListener(action);
	}

	/**
	 * createAddSensorPanel appear when user click on addSensor button
	 * it take the address and position of the new sensor and sends it to the server
	 * causes the creation of some response system and use responsePanel
	 */
	public void createAddSensorPanel() {
		/** Sensor position on map*/
		position = new JButton("CLIC"); position.setBackground(Color.white); position.setFont(basiqueFont); position.setBounds(900, 380, 80, 30);
		position.setMargin(new Insets(1,1,1,1)); this.add(position); position.addActionListener(action); position.setVisible(false);

		/** sensor address*/
		addressValue = new JTextField(); this.add(addressValue); addressValue.setBounds(870, 340, 130, 30); addressValue.setVisible(false);
		addressOK = new JButton("OK"); addressOK.setBackground(Color.white); addressOK.setFont(basiqueFont); addressOK.setBounds(1010, 340, 50, 30);	
		addressOK.setMargin(new Insets(1,1,1,1)); this.add(addressOK); addressOK.addActionListener(action); addressOK.setVisible(false);

		/** response */
		if ((response.equals("Adresse non saisie")) || (response.equals("Capteur ajouté !"))) {
			JLabel serverResponse = new JLabel("<html> <font size = 4 color = red>" + response + " </font> </html>"); 
			serverResponse.setFont(basiqueFont); serverResponse.setForeground(Color.WHITE);
			this.add(responsePanel); responsePanel.add(serverResponse);
		}		
		
		/** asking */
		addSensorPanel = new JPanel(); addSensorPanel.setBackground(Color.black);
		Border border = BorderFactory.createLineBorder(Color.WHITE, 4); addSensorPanel.setBorder(border);
		addSensorPanel.setLayout(new GridLayout(2, 1)); addSensorPanel.setBounds(704, 320, 366, 200); addSensorPanel.setVisible(false);
		this.add(addSensorPanel);

		JLabel captureInfo = new JLabel("<html> <font size = 5> Saisir l'adresse : <br> <br> Saisir la position : </font> </html>");
		captureInfo.setFont(basiqueFont); captureInfo.setForeground(Color.WHITE);
		addSensorPanel.add(captureInfo);

		JLabel infoSupp = new JLabel("<html> <font size = 5> Rendez-vous dans l’espace de configuration pour paramétrer votre capteur</font> </html>");
		infoSupp.setFont(basiqueFont); infoSupp.setForeground(Color.WHITE);
		addSensorPanel.add(infoSupp);

	}

	/**
	 * createDeleteSensorPanel appear when user click on deleteSensor button
	 * it take the id of the sensor to delete and sends it to the server
	 * causes the creation of some response system and use responsePanel
	 */
	public void createDeleteSensorPanel() {

		/** sensor id*/
		idValue = new JTextField(); this.add(idValue); idValue.setBounds(980, 340, 50, 30); idValue.setVisible(false);
		idOK = new JButton("OK"); idOK.setBackground(Color.white); idOK.setFont(basiqueFont); idOK.setBounds(980, 380, 50, 30);	
		idOK.setMargin(new Insets(1,1,1,1)); this.add(idOK); idOK.addActionListener(action); idOK.setVisible(false);

		/** system response */
		if ((response.equals("Capteur supprimé !")) || (response.equals("ERROR - ID non valide")) || (response.equals("ERROR - Capteur en marche"))) {
			JLabel serverResponse = new JLabel("<html> <font size = 4 color = red>" + response + " </font> </html>"); 
			serverResponse.setFont(basiqueFont); serverResponse.setForeground(Color.WHITE);
			this.add(responsePanel); responsePanel.add(serverResponse);
		}
		
		/** asking */
		deleteSensorPanel = new JPanel(); deleteSensorPanel.setBackground(Color.black);
		Border border = BorderFactory.createLineBorder(Color.WHITE, 4); deleteSensorPanel.setBorder(border);
		deleteSensorPanel.setLayout(new GridLayout(3, 1)); deleteSensorPanel.setBounds(704, 320, 366, 200); deleteSensorPanel.setVisible(false);
		this.add(deleteSensorPanel);

		JLabel captureInfoD = new JLabel("<html> <font size = 5> Num du capteur à supprimer : </font> </html>");
		captureInfoD.setFont(basiqueFont); captureInfoD.setForeground(Color.WHITE);
		deleteSensorPanel.add(captureInfoD);

		JLabel label = new JLabel(); deleteSensorPanel.add(label);

		JLabel infoSupp = new JLabel("<html> <font size = 5> La suppression d'un capteur nécessite sa désactivation préalable </font> </html>");
		infoSupp.setFont(basiqueFont); infoSupp.setForeground(Color.WHITE);
		deleteSensorPanel.add(infoSupp);
	}

	/**
	 * createBackButton allows to the user to go back on the city map
	 */
	public void createBackButton() {
		back = new JButton("Retour");
		back.setBounds(1000, 30, 150, 80);
		Font backFont = new Font("Bahnschrift", Font.BOLD, 28); back.setFont(backFont);
		back.setForeground(java.awt.Color.white);
		back.setBackground(Color.BLACK); 
		back.addActionListener(action);
		this.add(back);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage (image, 0, 0, 1200, 680, null); 
	}

	public AqsWindows getWind() {
		return wind;
	}

	public CommunicationWithServer getCommunication() {
		return communication;
	}

	public JButton getBack() {
		return back;
	}

	public JButton getAddSensor() {
		return addSensor;
	}

	public JButton getDeleteSensor() {
		return deleteSensor;
	}

	public JButton getAddressOK() {
		return addressOK;
	}

	public JTextField getAddressValue() {
		return addressValue;
	}

	public JPanel getAddSensorPanel() {
		return addSensorPanel;
	}

	public boolean isCheckAddress() {
		return checkAddress;
	}

	public void setIsCheckAddress(boolean check) {
		this.checkAddress = check;
	}

	public JButton getPosition() {
		return position;
	}

	public int getNbSensor() {
		return nbSensor;
	}

	public JButton getIdOK() {
		return idOK;
	}

	public JTextField getIdValue() {
		return idValue;
	}

	public JPanel getDeleteSensorPanel() {
		return deleteSensorPanel;
	}

}
