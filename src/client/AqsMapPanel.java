package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import common.AppliSave;
import common.DeviceAir;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;
/**
 * AqsMapPanel is the main panel of the aqs (air quality sensor) use case
 * @author elisa
 * Here, there are all the sensor of the city, their state and address
 * User choose to configure, to consult sensors or see city information from this panel 
 * only one connection from the old panel is used for the communication with server
 */
@SuppressWarnings("serial")
public class AqsMapPanel extends JPanel {
	/**Common composant attributes*/
	private Image image;
	private ArrayList<JButton> sensors = new ArrayList<JButton>();
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	
	/**sensor panel attributes*/
	private JButton consult;
	private JButton config;
	private JButton city;
	private JButton back;
	private JPanel infoSensor;
	private int nbSensor = 0;
	private JLabel alertImage;
	
	/**Common attributes */
	private AqsWindows wind;
	private CommunicationWithServer communication;
	private RequestsAqsClient request;
	private AqsMapListener action;
	private final String source;

	public AqsMapPanel(AqsWindows wind) throws IOException {
		this.wind = wind;
		communication = wind.getCommunication();
		action = new AqsMapListener(this);
		request = new RequestsAqsClient(communication);
		source = "client";
				
		/**
		 * Background configuration
		 */
		image=(new javax.swing.ImageIcon(getClass().getResource("/pictures/mapsensor-background.png"))).getImage();
		this.setLayout(null);

		/**
		 * panel construction
		 */
		
		this.addSensorButton();
		this.createBackAndCityButton();
		this.createInformationPanel();
						
	}
	
	/** addSensorButton recover all sensors recorded in database and create little button for them*/
	public void addSensorButton() {
		ArrayList<String> info = request.getInformationDeviceAir();
		ArrayList<AppliSave> save = request.getAppliSave();
		for (int i = 0; i < Integer.valueOf(info.get(0)); i++) {
			JButton sensor = new JButton();
			DeviceAir device = (DeviceAir) request.selectOne(i + 1, source, "deviceair");
			if (device.isOnAlert())	sensor.setBackground(Color.RED);
			else if ((device.isActive()) && (!device.isOnAlert())) sensor.setBackground(Color.GREEN);
			else if (!device.isActive()) sensor.setBackground(Color.GRAY);
			
			sensor.addActionListener(action); 
			sensors.add(sensor); this.add(sensor);

			sensors.get(i).setBounds(save.get(i).getCoordX(), save.get(i).getCoordY(), 20, 20);
			nbSensor++;
		}
	}
		
	/**creationBackandCityButton create the two buttons for go back to the older panel or go to the city panel*/
	public void createBackAndCityButton() {
		back = new JButton("Retour");;
		back.setBounds(1000, 30, 150, 80);
		Font backFont = new Font("Bahnschrift", Font.BOLD, 28); back.setFont(backFont);
		back.setForeground(java.awt.Color.white);
		back.setBackground(Color.BLACK); back.addActionListener(action);

		this.add(back);
		
		city = new JButton("Ville Info");
		city.setBounds(70, 500, 190, 100);
		Font cityFont = new Font("Bahnschrift", Font.BOLD, 24); city.setFont(cityFont);
		city.setForeground(java.awt.Color.BLACK);
		city.setBackground(Color.WHITE); city.addActionListener(action);
		
		this.add(city);
		
	}
	
	/**createInformationPanel create the panel for sensor, recovers some of the sensor information*/
	public void createInformationPanel() {
		ImageIcon alert = new ImageIcon(getClass().getResource("/pictures/alert.jpg"));
		alertImage = new JLabel(alert); alertImage.setBounds(1010, 315, 130, 95); this.add(alertImage); alertImage.setVisible(false);
		
		infoSensor = new JPanel();

		infoSensor.setLayout(new GridLayout(7, 1));
		infoSensor.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);	infoSensor.setBorder(border);
		this.add(infoSensor); 
		
		for (int i = 0; i < 5; i++) {
			JLabel label = new JLabel("", JLabel.CENTER); label.setVisible(true);
			Font infoFont = new Font("Bahnschrift", Font.ITALIC, 16); label.setFont(infoFont);
			labels.add(label);
			infoSensor.add(label); 
		}
		
		consult = new JButton("Consulter");
		consult.setBackground(Color.GRAY);
		Font f3 = new Font("Bahnschrift", Font.BOLD, 20); consult.setFont(f3);
		infoSensor.add(consult);
		
		config = new JButton("Configurer");
		config.setBackground(Color.GRAY);
		Font f4 = new Font("Bahnschrift", Font.BOLD, 20); config.setFont(f4);
		infoSensor.add(config); 
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage (image, 0, 0, 1200, 680, null); 
	}


	public JButton getCity() {
		return city;
	}

	public JButton getBack() {
		return back;
	}

	public ArrayList<JButton> getSensors() {
		return sensors;
	}

	public JPanel getInfoSensor() {
		return infoSensor;
	}


	public JButton getConsult() {
		return consult;
	}


	public JButton getConfig() {
		return config;
	}


	public ArrayList<JLabel> getLabels() {
		return labels;
	}

	public AqsWindows getWind() {
		return wind;
	}

	public int getNbSensor() {
		return nbSensor;
	}

	public JLabel getAlertImage() {
		return alertImage;
	}
}
