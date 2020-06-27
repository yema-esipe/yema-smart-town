package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class YemaPanel extends JPanel implements ActionListener {
	private Image image;
	private JButton tram;
	private JButton aqs;
	private JButton car;
	private JButton carbonPrint;
	private YemaWindows wind;

	public YemaPanel(YemaWindows wind) {
		this.wind = wind;
		/**
		 * Background configuration
		 */
		image=(new javax.swing.ImageIcon(getClass().getResource("/pictures/yema-background.png"))).getImage();
		this.setLayout(null);
		
		Font backFont = new Font("Bahnschrift", Font.BOLD, 20);
		/** button configuration */
		tram = new JButton("<html> <center> Réseau de tramway ");
		tram.setBackground(Color.black); tram.setForeground(Color.white); tram.setFont(backFont);
		tram.setBounds(60, 450, 250, 100); this.add(tram); tram.addActionListener(this);
		
		aqs = new JButton("<html> <center> Capteur de qualité d'air");
		aqs.setBackground(Color.black); aqs.setForeground(Color.white); aqs.setFont(backFont);
		aqs.setBounds(330, 450, 250, 100); this.add(aqs); aqs.addActionListener(this);
		
		car = new JButton("<html> <center> Bornes rétractables et capteurs de voitures");
		car.setBackground(Color.black); car.setForeground(Color.white); car.setFont(backFont);
		car.setBounds(600, 450, 250, 100); this.add(car); car.addActionListener(this);
		
		carbonPrint = new JButton("<html> <center>Empreinte Carbone");
		carbonPrint.setBackground(Color.black); carbonPrint.setForeground(Color.white); carbonPrint.setFont(backFont);
		carbonPrint.setBounds(870, 450, 250, 100); this.add(carbonPrint); carbonPrint.addActionListener(this);

	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage (image, 0, 0, 1185, 662, null); 
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == tram) {
			//aller sur la page tram
		}
		if (event.getSource() == aqs) {
			wind.setVisible(false);
			try {
				AqsComLaunch aqsstart = new AqsComLaunch();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		if (event.getSource() == car) {
			wind.setVisible(false);
		try {
			BollardlunchCom fenstart = new BollardlunchCom();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
			
		} 
		if (event.getSource() == carbonPrint) {
			wind.setVisible(false);
			FenCalculEC ecstart = new FenCalculEC();
			ecstart.setVisible(true);
		}
	}

}
