import javax.swing.*;
import java.awt.*;

/**
 * A simple standalone application that creates a blue panel
 * to verify basic rendering is working properly.
 */
public class TestRenderer {
    public static void main(String[] args) {
        // Make sure everything runs on the EDT
        SwingUtilities.invokeLater(() -> {
            // Create the frame
            JFrame frame = new JFrame("Test Renderer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(640, 640);
            
            // Create a simple panel with custom painting
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    
                    // Get current dimensions
                    int width = getWidth();
                    int height = getHeight();
                    
                    // Print size on first paint
                    System.out.println("Painting panel: " + width + "x" + height);
                    
                    // Fill with blue
                    g.setColor(Color.BLUE);
                    g.fillRect(0, 0, width, height);
                    
                    // Add yellow circle
                    g.setColor(Color.YELLOW);
                    g.fillOval(width/4, height/4, width/2, height/2);
                    
                    // Add white grid
                    g.setColor(Color.WHITE);
                    for (int x = 0; x < width; x += 50) {
                        g.drawLine(x, 0, x, height);
                    }
                    for (int y = 0; y < height; y += 50) {
                        g.drawLine(0, y, width, y);
                    }
                    
                    // Add green border
                    g.setColor(Color.GREEN);
                    g.drawRect(0, 0, width-1, height-1);
                }
            };
            
            // Ensure the panel is opaque
            panel.setOpaque(true);
            panel.setBackground(Color.RED);
            
            // Add panel to frame
            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            
            // Display the frame
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            // Force an immediate repaint
            panel.revalidate();
            panel.repaint();
        });
    }
}