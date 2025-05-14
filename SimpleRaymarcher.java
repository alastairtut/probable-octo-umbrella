import javax.swing.*;
import java.awt.*;

/**
 * A standalone simplified raymarcher application.
 * This version completely avoids using the SwingApplication
 * class to eliminate potential issues.
 */
public class SimpleRaymarcher {
    
    // Constants
    private static final int WIDTH = 640;
    private static final int HEIGHT = 640;
    private static final int FPS = 60;
    private static final String TITLE = "Simple Raymarcher";
    
    // UI Components
    private JFrame frame;
    private RenderPanel renderPanel;
    
    /**
     * Panel that handles the raymarching rendering
     */
    private static class RenderPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Get the panel dimensions
            int width = getWidth();
            int height = getHeight();
            
            // Fill with blue background
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, width, height);
            
            // Draw a yellow circle
            g.setColor(Color.YELLOW);
            g.fillOval(width/4, height/4, width/2, height/2);
            
            // Add a grid pattern
            g.setColor(Color.WHITE);
            for (int x = 0; x < width; x += 50) {
                g.drawLine(x, 0, x, height);
            }
            for (int y = 0; y < height; y += 50) {
                g.drawLine(0, y, width, y);
            }
            
            // Draw a border
            g.setColor(Color.GREEN);
            g.drawRect(0, 0, width-1, height-1);
            
            // Print size info occasionally
            if (Math.random() < 0.01) {
                System.out.println("Panel size: " + width + "x" + height);
            }
        }
    }
    
    /**
     * Create and run the application
     */
    public SimpleRaymarcher() {
        // Create and configure the frame
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        
        // Create and add the render panel
        renderPanel = new RenderPanel();
        renderPanel.setOpaque(true);
        renderPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.add(renderPanel, BorderLayout.CENTER);
        
        // Pack and display
        frame.pack();
        frame.setVisible(true);
        
        // Start the rendering loop
        startRenderLoop();
    }
    
    /**
     * Set up a timer to trigger repaints
     */
    private void startRenderLoop() {
        int delay = 1000 / FPS;
        Timer timer = new Timer(delay, e -> {
            if (renderPanel != null) {
                renderPanel.repaint();
            }
        });
        timer.start();
    }
    
    /**
     * Main entry point
     */
    public static void main(String[] args) {
        // Ensure we run on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new SimpleRaymarcher();
        });
    }
}