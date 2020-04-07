package cpsc101.bluemountian.view.components;

import javax.swing.*;
import java.awt.*;

/**
 * Provides a component used to display colors of the player
 *
 * @author Sebastian
 */
public class ColorInfo extends JComponent {
    private Color color=Color.BLACK;

    /**
     * Construct this component from given color
     * @param color color of this component
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Paints a color swatch
     * @param g graphics object to paint on
     */
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillRect(0,0,getWidth(),getHeight());
    }
}
