package cpsc101.bluemountian.view.components;

import javax.swing.*;
import java.awt.*;

public class ColorInfo extends JComponent {
    private Color color=Color.BLACK;

    public void setColor(Color color) {
        this.color = color;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillRect(0,0,getWidth(),getHeight());
    }
}
