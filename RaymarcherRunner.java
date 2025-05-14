import java.awt.BorderLayout;
import javax.swing.SwingUtilities;

/**
 * RaymarcherRunner is the driver class where the JPanel
 * is initialized and instantiated.
 * 
 * This class will be run to begin the program
 */
public class RaymarcherRunner extends SwingApplication {
	
	private static final int WIDTH		= 640;			// Width of the JFrame.
	private static final int HEIGHT		= 640;			// Height of the JFrame.
	private static final int TARGET_FPS = 60;			// The frames-per-second for the app.
	private static final String TITLE	= "Raymarcher";	// Title of the JFrame.
	private RaymarcherPanel	raymarcherPanel;	// The raymarcher panel.
	
	public RaymarcherRunner(int width, int height, int fps, String title) {
		super(width, height, fps, title);
		
		// Ensure frame is created before continuing
		SwingUtilities.invokeLater(() -> {
		    // Explicitly set the layout manager
		    getFrame().getContentPane().setLayout(new BorderLayout());
		    
		    // Create the panel AFTER the frame is fully initialized
		    this.raymarcherPanel = new RaymarcherPanel(this);
		    
		    // Add the panel to the center of the layout
		    getFrame().getContentPane().add(raymarcherPanel, BorderLayout.CENTER);
		    
		    // Pack to ensure proper sizing
		    packComponents();
		    
		    // Print debug info
		    System.out.println("Frame size after packing: " + 
		        getFrame().getWidth() + "x" + getFrame().getHeight());
		    System.out.println("Content pane size: " + 
		        getFrame().getContentPane().getWidth() + "x" + 
		        getFrame().getContentPane().getHeight());
		    
		    // Force validation and repaint
		    getFrame().validate();
		    getFrame().repaint();
		});
	}
	
	@Override
	public void run() {
		// This gets called by the timer at the specified FPS
		if (raymarcherPanel != null) {
			raymarcherPanel.repaint();
		}
	}
	
	public static void main(String[] args) {
		// Ensure we're on the EDT for frame creation
		SwingUtilities.invokeLater(() -> {
			RaymarcherRunner runner = new RaymarcherRunner(WIDTH, HEIGHT, TARGET_FPS, TITLE);
		});
	}
}
