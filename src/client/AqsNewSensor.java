package client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.AppliSave;
import common_aqs_client.RequestsAqsClient;

/**
 * AqsNewSensor is the windows for the insertion of a new device, and more particularly when the user has to choose a position
 * @author elisa
 * extends JFrame because it is a new windows and implements MouseListener to have to position of the user click
 */

@SuppressWarnings("serial")
public class AqsNewSensor extends JFrame implements MouseListener{
	private AqsCityPanel panelCity;
	private int nbSensor;
	private final String source = "client";
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;

	public AqsNewSensor(AqsCityPanel panelCity, int nbSensor)  {
		super();
		this.panelCity = panelCity;
		this.nbSensor = nbSensor;
		this.setSize(WIDTH, HEIGHT);		
		JPanel panel = new JPanel();
		
		ImageIcon background = new ImageIcon(getClass().getResource("/pictures/newSensor-background.png"));
		JLabel label = new JLabel(background); label.setBounds(0, 0, 1200, 700);
		panel.add(label);
		panel.addMouseListener(this);
		this.setContentPane(panel);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}

	/**mouseClicked recovered the user click on the panel*/
	@Override
	public void mouseClicked(MouseEvent event) {
		
		AppliSave save = new AppliSave();
		save.setCoordX(event.getX());
		save.setCoordY(event.getY());
		save.setIdDeviceAir(nbSensor + 1);
		
		RequestsAqsClient request = new RequestsAqsClient(panelCity.getWind().getCommunication());
		request.insert(save, source, "applisave");
		
		this.setVisible(false);
		panelCity.getWind().setContentPane(new AqsCityPanel(panelCity.getWind(), "Capteur ajouté !"));
		panelCity.getWind().setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}