import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author Linda Scoon
 *
 */
public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int width = 500;
		int height = 500;

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame frame = new MainFrame("Bouncing_Balls");
				frame.setSize(width, height);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});

	}

}
