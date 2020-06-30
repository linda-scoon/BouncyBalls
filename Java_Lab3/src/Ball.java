import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JComponent;

/**
 * defines a ball
 * 
 * @author Linda Scoon
 *
 */
public class Ball extends JComponent {

	private static final long serialVersionUID = 1L;
	private volatile int x;
	private volatile int y;
	private int DIAMETER = 50;
	private Color color;
	private int xVel;
	private int yVel;
	private int width;
	private int height;
	private int count = 0;
	private Random r;
	private int maxCoordinate;
	private int minCoordinate;

	public Ball() {
		r = new Random();
		maxCoordinate = 300;
		minCoordinate = DIAMETER;
		width = 460;
		height = 300;
		xVel = 20;
		yVel = 20;
		x = r.nextInt(maxCoordinate) + minCoordinate;
		y = r.nextInt(maxCoordinate) + minCoordinate;
		this.setColor();
	}

	/**
	 * ball movement instructions turns the ball around whenit reaches the edges of
	 * the board
	 */
	public synchronized void move() {

		// check boundaries
		if (x <= DIAMETER) {
			xVel *= -1;
		}
		if (x >= width - DIAMETER) {
			xVel *= -1;
		}
		if (y <= DIAMETER) {
			yVel *= -1;
		}
		if (y >= height + DIAMETER) {
			yVel *= -1;
		}

		// move
		x += xVel;
		y += yVel;

		// for debugging
//		System.out.println(x + "   " + y);
	}

	/**
	 * adds (value on slider) *10
	 * 
	 * @param speed
	 */
	public void changeSpeed(int speed) {
		xVel = speed * 10;
		yVel = speed * 10;

		// for debugging
		System.out.println(xVel + "   " + yVel);
	}

	/**
	 * generates a random color
	 */
	public void setColor() {
		float red = (float) Math.random();
		float green = (float) Math.random();
		float blue = (float) Math.random();

		this.color = new Color(red, green, blue);
	}

	/**
	 * 
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return color x
	 */
	@Override
	public int getX() {
		return x;
	}

	/**
	 * @return y
	 */
	@Override
	public int getY() {
		return y;
	}

	/**
	 * 
	 * @return xVel
	 */
	public int getxVel() {
		return xVel;
	}

	/**
	 * 
	 * @return yVel
	 */
	public int getyVel() {
		return yVel;
	}

	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	/**
	 * 
	 * @param yVel
	 */
	public void setyVel(int yVel) {
		this.yVel = yVel;
	}

	/**
	 * @return Rectangle surrounding the circle
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

	@Override
	public synchronized void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		super.paintComponent(g2);
		g2.setColor(color);
		g2.fillOval(x, y, DIAMETER, DIAMETER);

		// just for fun
//		count++;
//		String counter = count + "";
//		g2.setFont(new Font("Arial", Font.BOLD, 20));
//		g2.drawString(counter, x, y);
	}

}
