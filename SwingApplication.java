import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Simplified version of SwingApplication that focuses on just
 * getting rendering to work correctly.
 */
public abstract class SwingApplication {

	private final JFrame frame;     // JFrame to add content to
	private Timer timer;            // Timer for updating the JFrame
	private final int fps;          // Frames per second
	private final int ms;           // Milliseconds per frame
	private boolean isRunning = false;

	public SwingApplication(int width, int height, int fps, String title) {
		// Create the frame first
		this.frame = new JFrame(title);
		this.frame.setSize(width, height);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		
		// Set up timing
		this.fps = fps;
		this.ms = 1000 / fps;
		
		// Register frame close handler
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stop();
			}
		});
	}

	/**
	 * Add a component to the frame
	 */
	public void addComponent(Component component) {
		this.frame.getContentPane().add(component);
	}

	/**
	 * Pack and center the frame
	 */
	public void packComponents() {
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
	}

	/**
	 * Make the frame visible and start the timer
	 */
	public void start() {
		if (isRunning) return;
		
		// Show the frame
		SwingUtilities.invokeLater(() -> {
			this.frame.setVisible(true);
			System.out.println("Frame made visible");
			
			// Create and start the timer
			this.isRunning = true;
			this.timer = new Timer(ms, e -> {
				// Call the run method
				run();
				// Force a repaint
				this.frame.repaint();
			});
			this.timer.start();
			System.out.println("Timer started at " + fps + " FPS");
		});
	}

	/**
	 * Stop the timer and dispose the frame
	 */
	private void stop() {
		if (!isRunning) return;
		
		isRunning = false;
		if (timer != null) {
			timer.stop();
		}
		frame.dispose();
		System.exit(0);
	}

	/**
	 * Abstract method to be implemented by subclasses
	 */
	public abstract void run();

	/**
	 * Get the JFrame
	 */
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Set frame visibility
	 */
	public void setVisible(boolean visible) {
		this.frame.setVisible(visible);
	}
}
