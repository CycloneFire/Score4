package cpsc101.bluemountian.view.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Provides a visual list of colors to chose from.
 *
 * @author Sebastian
 */
public class ColorOptions extends JComponent {
    private static ArrayList<Color> colors = new ArrayList<>();

    private int hover = 0;
    private int selected = 0;
    int rectWidth;

    /**
     * Constructs a visual list of pre-defined colors
     */
    public ColorOptions(){
        if(colors.size()<1)resetColors();
    }

    /**
     * Paints the list of colors
     * @param g graphics object to paint on
     */
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        rectWidth = (this.getWidth() - 10*colors.size())/(colors.size());

        for(int i = 0; i < colors.size(); i++) {
            g2.setColor(colors.get(i));
            g2.fillRect(rectWidth*i + 10*i,0,rectWidth,50); // Changed this to match color swatch size
        }

        g2.setStroke(new BasicStroke(5));

        g2.setColor(new Color(177, 238, 255, 255));
        g2.drawRect(rectWidth*hover + 10*hover, 2,rectWidth,48);// Changed this to match color swatch size


        g2.setColor(Color.CYAN);
        g2.drawRect(rectWidth*selected + 10*selected, 2,rectWidth,48);// same as above

    }

    /**
     * Draws a temporary boundary on swatch at passed index
     * @param place index to draw boundary for
     */
    public void setHover(int place) {
        hover = place;
        repaint();
    }

    /**
     * Draws a boundary on swatch at passed index
     * @param place index to draw boundary for
     */
    public void setSelected(int place) {
        selected = place;
        repaint();
    }

    /**
     *
     * @return selected color
     */
    public Color getColor(){
        return colors.get(selected);
    }

    /**
     *
     * @return Random color from available colors in the list
     */
    public static Color getRandomColor(){
        Random random = new Random();
        Color col = colors.get(random.nextInt(colors.size()));
        removeColor(col);
        return col;
    }

    /**
     *
     * @return list of all available colors
     */
    public static ArrayList<Color> getColors(){
        return colors;
    }

    /**
     * Remove passed color from the list
     * @param col color to remove
     */
    public static void removeColor(Color col){
        colors.remove(col);
    }

    /**
     * Resets all the color to this pre-defined list of colors
     */
    public static void resetColors(){
        colors.removeAll(colors);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.PINK);
        colors.add(new Color(0x9000C8));
        colors.add(Color.WHITE);
        colors.add(Color.BLACK);
        colors.add(Color.ORANGE);
    }

    /**
     *
     * @return width of one color swatch
     */
    public int getRectWidth() {
        return rectWidth;
    }

}
