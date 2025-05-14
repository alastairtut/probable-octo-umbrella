import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * Displays and updates the logic for the raymarcher.
 */
public class RaymarcherPanel extends JPanel { 
	
	private final RaymarcherRunner raymarcherRunner; // reference to the parent app
	
	public RaymarcherPanel(RaymarcherRunner raymarcherRunner) {
		this.raymarcherRunner = raymarcherRunner;
		this.setPreferredSize(new Dimension(this.raymarcherRunner.getFrame().getHeight(), 
				this.raymarcherRunner.getFrame().getHeight())); 
	}
	
	// All drawing code goes here
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.fillRect(0, 0, this.getWidth(),this.getHeight());
	}
}
