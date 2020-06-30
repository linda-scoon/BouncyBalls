/**
 * 
 * @author Linda Scoon
 *
 */
public class BallRunnable implements Runnable {

	private Ball ball;
	private BouncyBalls display;
	private boolean running;

	/**
	 * 
	 * @param Ball        b
	 * @param BouncyBalls c
	 */
	public BallRunnable(Ball b, BouncyBalls c) {
		this.ball = b;
		this.display = c;
		this.running = true;
	}

	@Override
	public void run() {
		while (running) {
			if (!display.isRunning()) {
				running = false;
			}
			ball.move();
			display.repaint();// call the displays repaint, which will call individual balls repaint
			display.collide();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
