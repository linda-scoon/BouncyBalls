import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JComponent;

/**
 * Defines a particle
 * 
 * @author Lind Scoon
 *
 */
public class Particle extends JComponent {

	private static final long serialVersionUID = 1L;
	private int diameter;
	private int xVel;
	private int yVel;
	private int x;
	private int y;
	private Random r;
	private int width;
	private int height;

	/**
	 * @param x
	 * @param y
	 */
	public Particle(int x, int y) {
		r = new Random();
		width = 500;
		height = 350;
		this.x = x;
		this.y = y;
		diameter = 2;
		// generating random velocity
		xVel = r.nextInt(50) - 25;
		yVel = r.nextInt(50) - 25;
	}

	public void move() {

		// checking that velocity != 0
		if (xVel == 0 || yVel == 0) {
			xVel = 5;
			yVel = 5;
		}
		x += xVel;
		y += yVel;
	}

	/**
	 * checks if particle is off screen
	 * 
	 * @return boolean
	 */
	public boolean expire() {
		if (x <= 0 || x >= width || y <= 0 || y >= height) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Particle [diameter=" + diameter + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public synchronized void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		super.paintComponent(g2);
		g2.setColor(Color.YELLOW);
		g2.fillOval(x, y, diameter, diameter);

	}
}
