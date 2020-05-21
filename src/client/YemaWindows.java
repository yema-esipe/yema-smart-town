package client;


import java.io.IOException;
import javax.swing.JFrame;
/**
 * YemaWindows is the common windows for all use case
 * @author 
 *
 */

@SuppressWarnings("serial")
public class YemaWindows extends JFrame {
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 700;

	public YemaWindows() throws IOException {
		super();
		this.setSize(WIDTH, HEIGHT);

		this.setContentPane(new YemaPanel(this));


		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}

}
