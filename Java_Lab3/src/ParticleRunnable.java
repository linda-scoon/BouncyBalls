/**
 * @author Linda Scoon
 *
 */
public class ParticleRunnable implements Runnable {

	private Particle particle;
	private BouncyBalls display;

	/**
	 * @param Particle    p
	 * @param BouncyBalls b
	 */
	public ParticleRunnable(Particle p, BouncyBalls b) {
		this.particle = p;
		this.display = b;
	}

	@Override
	public void run() {
		while (true) {
			particle.move();
			display.repaint();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
