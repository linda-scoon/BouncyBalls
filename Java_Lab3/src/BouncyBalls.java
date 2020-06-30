import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JComponent;

/**
 * Stores and draws balls and particles. creates threads and manages multiple
 * instructions
 * 
 * @author LINDA SCOON
 *
 */
public class BouncyBalls extends JComponent {

	private static final long serialVersionUID = 1L;
	private CopyOnWriteArrayList<Ball> ballList;// CopyOnWriteArrayList is used to prevent concurrency issues
	private CopyOnWriteArrayList<Particle> particles;
	private boolean running;
	private int width;
	private int height;

	public BouncyBalls() {
		width = 500;
		height = 350;
		ballList = new CopyOnWriteArrayList<>();
		particles = new CopyOnWriteArrayList<>();
	}

	/**
	 * iterates through the array and creates a new thread for every ball in it
	 */
	public void start() {
		setRunning(true);

		for (Ball b : ballList) {
			ExecutorService executor = Executors.newCachedThreadPool();
			executor.submit(new BallRunnable(b, this));
		}
	}

	public void addBall() {
		ballList.add(new Ball());
		start();
	}

	/**
	 * checks for collisions by looping through the list for every element in the
	 * list. This is done usinng an inserted for loop. The only roblem is that the
	 * element checks itself whn it is iterating through the list. Therefore it has
	 * to be made not to do this by the adding this i != j to the condition Video
	 * tutorial that helped: https://youtu.be/GY-c2HO2liA
	 */
	public synchronized void collide() {
		for (int i = 0; i < ballList.size(); i++) {
			for (int j = 0; j < ballList.size(); j++) {
				if (i != j && ballList.get(i).getBounds().intersects(ballList.get(j).getBounds())) {

					// get coordinates at point of explosion. Calculating the average x and y
					// positions
					int x = (ballList.get(i).getX() + ballList.get(j).getX()) / 2;
					int y = (ballList.get(i).getY() + ballList.get(j).getY()) / 2;

					// explosion
					if (x >= 0 || x <= width || y >= 0 || y <= height) {
						explode(x, y);
					}

					// remove collided balls. Remove j first.
					ballList.remove(j);
					ballList.remove(i);
				}
			}
		}
	}

	/**
	 * Creates particles that simulate an explosion. Video tutorial that helped:
	 * https://www.youtube.com/watch?v=CKeyIbT3vXI&t=1110s
	 * 
	 * @param x
	 * @param y
	 */
	public synchronized void explode(int x, int y) {

		int numParticles = 50;

		// adding particles to array at position of collision
		for (int i = 0; i < numParticles; i++) {
			particles.add(new Particle(x, y));
		}

		for (Particle p : particles) {

			Thread t = new Thread(new ParticleRunnable(p, this));
			t.start();
		}
	}

	/**
	 * 
	 * @param speed
	 */
	public void changeSpeed(int speed) {
		for (Ball b : ballList) {
			b.changeSpeed(speed);
		}
	}

	/**
	 * Stops the balls for 5 seconds then clears the screen
	 */
	public void stop() {
		setRunning(false);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ballList.clear();
		particles.clear();
		repaint();
	}

	/**
	 * freezes the balls and unfreezes the balls
	 */
	public void pause() {
		if (!isRunning()) {
			start();
		} else if (isRunning()) {
			setRunning(false);
		}
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * Draws balls and particles
	 * 
	 * @param Graphics g
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : ballList) {
			b.paintComponent(g2);
		}

		for (Particle p : particles) {
			/*
			 * checking for out of bounds particles before drawing. After particles are no
			 * longer visible on screen they are removed
			 */
			if (p.expire()) {
				particles.remove(p);
			}

			if (p != null) {// check that array has element to avoid null pointer exception
				p.paintComponent(g2);
			}

			// for debugging
			System.out.println(particles);
		}
	}
}
