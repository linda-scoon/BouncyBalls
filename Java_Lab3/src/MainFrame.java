import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Linda Scoon
 *
 */
public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton start;
	private JButton pause;
	private JButton stop;
	private JButton exit;
	private BouncyBalls balls;

	/**
	 * @param title
	 */
	public MainFrame(String title) {
		super(title);

		balls = new BouncyBalls();

		createContolScreen();
		pack();
	}

	/**
	 * arranges the screen
	 */
	public void createContolScreen() {

		// create ball panel
		JPanel ballPanel = new JPanel();
		ballPanel.setBackground(Color.BLACK);
		balls.setPreferredSize(new Dimension(500, 400));// if this is not given a size it wont show on screen when added
														// // to panel
		ballPanel.add(balls);
		this.add(ballPanel, BorderLayout.CENTER);

		// create panels
		JPanel btnPanel = createBtnPanel();
		JPanel sliderPanel = createSliderPanel();

		// create control panel
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2, 1));
		controlPanel.add(btnPanel);
		controlPanel.add(sliderPanel);
		this.add(controlPanel, BorderLayout.SOUTH);

	}

	private JPanel createSliderPanel() {

		JPanel sliderPanel = new JPanel();

		JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 5, 2);// orientation, min value, max value, starting value
		slider.setMajorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {// slider panels use change listeners not actionlisteners

			@Override
			public void stateChanged(ChangeEvent e) {
				int speed = slider.getValue();
				balls.changeSpeed(speed);
			}

		});

		sliderPanel.add(slider);
		return sliderPanel;
	}

	private JPanel createBtnPanel() {
		// create buttons
		start = new JButton("START");
		pause = new JButton("PAUSE");
		stop = new JButton("STOP ");
		exit = new JButton(" EXIT ");

		// add action listeners
		start.addActionListener(this);
		pause.addActionListener(this);
		stop.addActionListener(this);
		exit.addActionListener(this);

		// add buttons to panel
		JPanel btnPanel = new JPanel();
		btnPanel.add(start);
		btnPanel.add(pause);
		btnPanel.add(stop);
		btnPanel.add(exit);

		return btnPanel;
	}

	/**
	 * overriding the implemented method
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			balls.addBall();
		}
		if (e.getSource() == pause) {
			balls.pause();
		}
		if (e.getSource() == stop) {
			balls.stop();
		}
		if (e.getSource() == exit) {
			System.exit(EXIT_ON_CLOSE);
		}
	}

}
