package cpsc101.bluemountian.view.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ColorOptions extends JComponent {
    private static ArrayList<Color> colors = new ArrayList<>();

    private int hover = 0;
    private int selected = 0;
    private int alreadySelected = 10;
    int rectWidth;
    public ColorOptions(){
        if(colors.size()<1)resetColors();
    }
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

    public void setHover(int place) {
        hover = place;
        repaint();
    }

    public void setSelected(int place) {
        selected = place;
        repaint();
    }

    public Color getColor(){
        return colors.get(selected);
    }

    public static Color getRandomColor(){
        Random random = new Random();
        Color col = colors.get(random.nextInt(colors.size()));
        removeColor(col);
        return col;
    }

    public static ArrayList<Color> getColors(){
        return colors;
    }

    public static void removeColor(Color col){
        colors.remove(col);
    }

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

    public int getRectWidth() {
        return rectWidth;
    }

    public void setAlreadySelected(int place) {
        alreadySelected = place;
        repaint();
    }
}
