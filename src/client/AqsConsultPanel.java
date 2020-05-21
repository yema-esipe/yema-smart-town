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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import common.Alert;
import common.DataAirAVG;
import common.DeviceAir;
import common_aqs_client.CommunicationWithServer;
import common_aqs_client.RequestsAqsClient;

/**
 * AqsConsultPanel is the panel that we have by clicking on 'Consulter' on the main air quality sensor panel, after to have click on a sensor
 * @author elisa
 * Three situations :
 * 1) device disable -> no data visible, we have to active the sensor
 * 2) device just active -> no average was calculated yet, we have to wait the first one
 * 3) all data are visible : last average, indicators on alert (if the sensor is on alert) and the five olders averages
 */
@SuppressWarnings("serial")
public class AqsConsultPanel extends JPanel {
	private Image image;
	private DeviceAir device;
	private Font titleFont;
	private Font basiqueFont;
	private Font littleFont;
	private JButton back;

	private RequestsAqsClient request;
	private AqsWindows wind;
	private CommunicationWithServer communication;
	private AqsConsultListener action;
	private final String source;

	public AqsConsultPanel(AqsWindows wind, DeviceAir device) throws IOException {
		this.wind = wind;
		this.device = device;
		this.communication = wind.getCommunication();
		action = new AqsConsultListener(this);
		request = new RequestsAqsClient(communication);
		source = "client";

		titleFont = new Font("Bahnschrift", Font.LAYOUT_LEFT_TO_RIGHT, 20);
		basiqueFont = new Font("Bahnschrift", Font.ITALIC, 16);
		littleFont = new Font("Bahnschrift", Font.ITALIC, 14);


		/**
		 * Background configuration
		 */
		image=(new javax.swing.ImageIcon(getClass().getResource("/pictures/consult-background.png"))).getImage();
		this.setLayout(null);

		/**
		 * Panel construction
		 */
		if (device.isActive()) {

			this.createInformationPanel();
			this.createLittlePanel("<html> Dernière moyenne <br> relevée sur 1h </html>", 480, 40);
			this.createLittlePanel("<html> Derniers moyennes <br> calculées </html>", 330, 370);
			this.getAvgValues();
			this.arrayConfig();
		} else {
			this.createNoDataPanel();
		}
		this.createReturnButton();
	}

	/** createReturnButton allows to go back on the city view*/
	public void createReturnButton() {
		back = new JButton("Retour");

		back.setBounds(1020, 15, 150, 80);
		Font returnFont = new Font("Bahnschrift", Font.BOLD, 28); back.setFont(returnFont);
		back.setForeground(java.awt.Color.white);
		back.setBackground(Color.black); back.addActionListener(action);

		this.add(back);
	}

	/** createInformationPanel allows to create the left panel with all sensor informations */
	public void createInformationPanel() {
		/**	Panel creation */
		JPanel infoSensor = new JPanel();
		infoSensor.setLayout(new GridLayout(7, 1));
		infoSensor.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 4);	infoSensor.setBorder(border);
		infoSensor.setBounds(30, 30, 270, 340);


		/** Panel filling */
		JLabel title = new JLabel("Capteur n° " + device.getId(), JLabel.CENTER); title.setFont(titleFont);
		infoSensor.add(title);

		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		for (int i = 0; i < 4; i++) {
			JLabel label = new JLabel("");
			label.setFont(basiqueFont);
			labels.add(label);
			infoSensor.add(label); 
		}

		labels.get(0).setText("  Adresse : " + device.getAddress());
		labels.get(1).setText("<html> Etat actuel : <font color = green> ON </font> </html>");

		ArrayList<String> indic = new ArrayList<String>();
		if (device.isOnAlert()) {
			Alert alert = (Alert) request.selectOne(device.getId(), source, "alert");
			if (alert.isCo2()) indic.add("Dioxyde de carbone");
			if (alert.isCarbonMonoxide()) indic.add("Monoxyde de carbone");
			if (alert.isFinesParticules()) indic.add("Particules fines");
			if (alert.isSulfurDioxide()) indic.add("Dioxyde de soufre");
			if (alert.isNitrogenDioxide()) indic.add("Dioxyde d'azone");
			if (alert.isOzone()) indic.add("Ozone");


			labels.get(2).setText("  Indicateurs en alerte : ");
			labels.get(3).setText("<html> <center>" + indic.toString() + "</center> </html>");

			ImageIcon alertI = new ImageIcon(getClass().getResource("/pictures/alert2.jpg"));
			JLabel alertImage = new JLabel(alertI); alertImage.setBounds(240, 100, 105, 93); this.add(alertImage);
		}

		JLabel note = new JLabel("<html> <center> Indice de qualité d'air = " + device.getQuality() + "/10", JLabel.CENTER); note.setFont(titleFont); note.setForeground(Color.red);
		infoSensor.add(note);

		this.add(infoSensor); 
	}

	/** createNoDataPanel is appear if the sensor is disable */
	public void createNoDataPanel() {
		JPanel panel = new JPanel(); panel.setLayout(new GridLayout());
		panel.setBackground(Color.white); panel.setBounds(140, 200, 900, 200);	this.add(panel);
		JLabel label = new JLabel("<html> <center> Ce capteur est désactivé. <br> Pour l'activer, rendez-vous dans l'espace 'Configurer' du capteur.<br> Merci.</center><</html> ", JLabel.CENTER);
		panel.add(label); label.setFont(titleFont);
	}

	/** createLittlePanel create the two little panel to present the values*/
	public void createLittlePanel(String message, int locationH, int locationW) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.setBackground(Color.black); 
		Border border = BorderFactory.createRaisedBevelBorder(); panel.setBorder(border);

		JLabel label = new JLabel(message, JLabel.CENTER); label.setFont(basiqueFont); label.setForeground(Color.white);
		panel.add(label); 
		panel.setBounds(locationH, locationW, 200, 70);

		this.add(panel); 
	}

	/**getAvgValues build panels for each indicator and display their own average, if they are on alert they become red, otherwise they are green*/
	public void getAvgValues() {
		ArrayList<DataAirAVG> dataList = request.getDataAVG(device.getId());
				
		if (dataList.size() > 0) {
			DataAirAVG dataAVG = dataList.get(0);

			/** AVG data filling panels*/
			JPanel panel1 = new JPanel(); panel1.setBackground(Color.white); panel1.setBounds(500, 120, 120, 80);
			JLabel co2 = new JLabel("<html> <center> Dioxyde de <br> carbone <br> <br>" + dataAVG.getCo2() + " ppm</html>"); co2.setFont(littleFont);
			panel1.add(co2); this.add(panel1);

			JPanel panel2 = new JPanel(); panel2.setBackground(Color.white); panel2.setBounds(650, 120, 120, 80);
			JLabel carbonMonoxide = new JLabel("<html><center>Monoxyde de <br> carbone<br> <br>" + dataAVG.getCarbonMonoxide() + " µg/m^3</html>"); carbonMonoxide.setFont(littleFont);
			panel2.add(carbonMonoxide); this.add(panel2);

			JPanel panel3 = new JPanel(); panel3.setBackground(Color.white); panel3.setBounds(800, 120, 120, 80);
			JLabel finesParticules = new JLabel("<html><center>Particules fines<br> <br> <br>" + dataAVG.getFinesParticules() + " µg/m^3</html>"); finesParticules.setFont(littleFont);
			panel3.add(finesParticules); this.add(panel3);

			JPanel panel4 = new JPanel(); panel4.setBackground(Color.white); panel4.setBounds(500, 230, 120, 80);
			JLabel sulfurDioxide = new JLabel("<html><Center>Dioxyde de soufre<br> <br> <br>" + dataAVG.getSulfurDioxide() + " µg/m^3</html>"); sulfurDioxide.setFont(littleFont);
			panel4.add(sulfurDioxide); this.add(panel4);

			JPanel panel5 = new JPanel(); panel5.setBackground(Color.white); panel5.setBounds(650, 230, 120, 80);
			JLabel nitrogenDioxide = new JLabel("<html><center>Dioxyde <br>d'azote<br> <br>" + dataAVG.getNitrogenDioxide() + " µg/m^3</html>"); nitrogenDioxide.setFont(littleFont);
			panel5.add(nitrogenDioxide); this.add(panel5);

			JPanel panel6 = new JPanel(); panel6.setBackground(Color.white); panel6.setBounds(800, 230, 120, 80);
			JLabel ozone = new JLabel("<html> <center> Ozone <br><br> <br>" + dataAVG.getOzone() + " µg/m^3</html>"); ozone.setFont(littleFont);
			panel6.add(ozone); this.add(panel6);

			/** check Alert */
			if (device.isOnAlert()) {
				Alert alert = (Alert) request.selectOne(device.getId(), source, "alert");
				if (alert.isCo2()) co2.setForeground(Color.red); else co2.setForeground(Color.green);
				if (alert.isCarbonMonoxide()) carbonMonoxide.setForeground(Color.red); else carbonMonoxide.setForeground(Color.green);
				if (alert.isFinesParticules()) finesParticules.setForeground(Color.red); else finesParticules.setForeground(Color.green);
				if (alert.isSulfurDioxide()) sulfurDioxide.setForeground(Color.red); else sulfurDioxide.setForeground(Color.green);
				if (alert.isNitrogenDioxide()) nitrogenDioxide.setForeground(Color.red); else nitrogenDioxide.setForeground(Color.green);
				if (alert.isOzone()) ozone.setForeground(Color.red); else ozone.setForeground(Color.green);
			} else {
				co2.setForeground(Color.green);
				carbonMonoxide.setForeground(Color.green);
				finesParticules.setForeground(Color.green);
				sulfurDioxide.setForeground(Color.green);
				nitrogenDioxide.setForeground(Color.green);
				ozone.setForeground(Color.green);
			}
		} else {
			JPanel wait = new JPanel(); wait.setBackground(Color.white); wait.setBounds(360, 150, 500, 90);
			JLabel waiting = new JLabel("<html> <center> Veuillez patientez, la première moyenne de ce capteur <br> n'a pas encore pu être calculée </html>"); waiting.setFont(titleFont);
			wait.add(waiting); this.add(wait);
		}
	}

	/**arrayConfig concern the display of the five last average which were calculated */
	public void arrayConfig() {
		ArrayList<DataAirAVG> dataList = request.getDataAVG(device.getId());
		String  arrayTitle[] = {"", " ", "  ", "   ", "    ", "     ", "      "};
		JTable array;
		if (dataList.size() < 5) {
			Object[][] data = {
					{"<html> <b> Date </b> </html>", "<html> <center> <b>Dioxyde de carbone<br>en ppm </b> </center> </html>", "<html><center><b>Monoxyde de carbone en µg/m^3</b></center></html>", "<html> <center> <b>Particules Fines<br>en µg/m^3 </b> </center> </html>", "<html> <center> <b>Dioxyde de soufre<br>en µg/m^3 </b> </center> </html>", "<html> <center> <b>Dioxyde d'azote<br>en µg/m^3 </b> </center> </html>", "<html><b>Ozone en µg/m^3 </b> </html>"}
			};
			array = new JTable(data, arrayTitle); 
		} else {
			Object[][] data = {
					{"<html> <b> Date </b> </html>", "<html> <center> <b>Dioxyde de carbone<br>en ppm </b> </center> </html>", "<html><center><b>Monoxyde de carbone en µg/m^3</b></center></html>", "<html> <center> <b>Particules Fines<br>en µg/m^3 </b> </center> </html>", "<html> <center> <b>Dioxyde de soufre<br>en µg/m^3 </b> </center> </html>", "<html> <center> <b>Dioxyde d'azote<br>en µg/m^3 </b> </center> </html>", "<html><b>Ozone en µg/m^3 </b> </html>"},
					{dataList.get(0).getDate(), dataList.get(0).getCo2(), dataList.get(0).getCarbonMonoxide(), dataList.get(0).getFinesParticules(), dataList.get(0).getSulfurDioxide(), dataList.get(0).getNitrogenDioxide(), dataList.get(0).getOzone()},
					{dataList.get(1).getDate(), dataList.get(1).getCo2(), dataList.get(1).getCarbonMonoxide(), dataList.get(1).getFinesParticules(), dataList.get(1).getSulfurDioxide(), dataList.get(1).getNitrogenDioxide(),  dataList.get(1).getOzone()},
					{dataList.get(2).getDate(), dataList.get(2).getCo2(), dataList.get(2).getCarbonMonoxide(), dataList.get(2).getFinesParticules(), dataList.get(2).getSulfurDioxide(), dataList.get(2).getNitrogenDioxide(),  dataList.get(2).getOzone()},
					{dataList.get(3).getDate(), dataList.get(3).getCo2(), dataList.get(3).getCarbonMonoxide(), dataList.get(3).getFinesParticules(), dataList.get(3).getSulfurDioxide(), dataList.get(3).getNitrogenDioxide(),  dataList.get(3).getOzone()},
					{dataList.get(4).getDate(), dataList.get(4).getCo2(), dataList.get(4).getCarbonMonoxide(), dataList.get(4).getFinesParticules(), dataList.get(4).getSulfurDioxide(), dataList.get(4).getNitrogenDioxide(),  dataList.get(4).getOzone()}
			};
			array = new JTable(data, arrayTitle); 
		}

		for(int i = 0; i < array.getColumnCount(); i++) {
			array.getColumn(array.getColumnName(i)).setCellRenderer(new JLabelRenderer());
		}

		JPanel histo = new JPanel();
		histo.setLayout(new GridLayout(1, 1));
		histo.setForeground(Color.black);
		histo.setBounds(150, 430, 900, 200);
		histo.add(new JScrollPane(array));

		for(int i = 0; i < array.getRowCount(); i++){
			array.setRowHeight(i, 30);
		}

		this.add(histo);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage (image, 0, 0, 1220, 690, null); 
	}

	public AqsWindows getWind() {
		return wind;
	}

	public JButton getBack() {
		return back;
	}
}
