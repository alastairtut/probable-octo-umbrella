import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.BorderFactory;

/**
 * Displays and updates the logic for the raymarcher.
 */
public class RaymarcherPanel extends JPanel { 
	
	private final RaymarcherRunner raymarcherRunner; // reference to the parent app
	
	public RaymarcherPanel(RaymarcherRunner raymarcherRunner) {
		this.raymarcherRunner = raymarcherRunner;

		// Ensure we're visible and opaque
		this.setOpaque(true);
		this.setVisible(true);
		
		// Set a very explicit background color
		this.setBackground(Color.RED);
		
		// Add a border to help diagnose sizing issues
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		// Set preferred size
		this.setPreferredSize(new Dimension(600, 600));
		
		System.out.println("RaymarcherPanel constructor complete");
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// The next line needs to execute for proper rendering!
		super.paintComponent(g);
		
		// Force a full repaint immediately
		Graphics2D g2d = (Graphics2D) g.create();
		
		try {
			// Get the current size
			int width = getWidth();
			int height = getHeight();
			
			// Print debug info
			System.out.println("paintComponent executing with size: " + width + "x" + height);
			
			// Set rendering hints for better quality
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			// IMPORTANT: Fill explicit colors
			g2d.setColor(Color.BLUE);
			g2d.fillRect(0, 0, width, height);
			
			// Add a yellow circle
			g2d.setColor(Color.YELLOW);
			g2d.fillOval(width/4, height/4, width/2, height/2);
			
			// Print completion
			System.out.println("paintComponent completed");
		} finally {
			// Always dispose Graphics objects you've created
			g2d.dispose();
		}
	}
}
