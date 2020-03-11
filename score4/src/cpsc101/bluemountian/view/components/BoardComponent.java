package cpsc101.bluemountian.view.components;

import cpsc101.bluemountian.model.board.Board;

import javax.swing.*;
import java.awt.*;

public class BoardComponent extends JComponent {

    int hoverX = 10;
    int hoverY = 10;
    int hoverZ = 0;
    Color hoverColor = new Color(255, 255, 255, 128);
    Color p1Color;
    Color p2Color;
    Board model;


    public BoardComponent(Board model,Color p1Color,Color p2Color) {
        this.model=model;
        this.p1Color=p1Color;
        this.p2Color=p2Color;

    }



    public void setModel(Board model) {
        this.model = model;
    }

    public void setPlayerColors(Color c1,Color c2){
        p1Color=c1;
        p2Color=c2;
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        int x = getSize().width;
        int y = getSize().height;
        int size = y;
        int xgap = (x - y) / 2;
        int ygap = 0;
        if (y > x) {
            size = x;
            xgap = 0;
            ygap = (y - x) / 2;
        }

        //draw background
        g2.setColor(new Color(255, 242, 231));
        g2.fill(new Rectangle(xgap, ygap, size, size));
        //draw board
        //right side of the board
        int[] xCoordsSide = {xgap + size * 660 / 1000, xgap + size * 660 / 1000, xgap + size * 980 / 1000, xgap + size * 980 / 1000};
        int[] yCoordsSide = {ygap + size * 660 / 1000, ygap + size * 690 / 1000, ygap + size * 390 / 1000, ygap + size * 360 / 1000};
        int vertices = 4;
        g2.setColor(new Color(136, 0, 21));
        g2.fillPolygon(xCoordsSide, yCoordsSide, vertices);


        //Bottom side of the board
        int[] xCoordsBottom = {xgap + size * 660 / 1000, xgap + size * 660 / 1000, xgap + size * 20 / 1000, xgap + size * 20 / 1000};
        int[] yCoordsBottom = {ygap + size * 660 / 1000, ygap + size * 690 / 1000, ygap + size * 690 / 1000, ygap + size * 660 / 1000};
        g2.setColor(new Color(184, 121, 85));
        g2.fillPolygon(xCoordsBottom, yCoordsBottom, vertices);

        //Top of Board
        int[] xCoordsTop = {xgap + size * 660 / 1000, xgap + size * 20 / 1000, xgap + size * 350 / 1000, xgap + size * 980 / 1000};
        int[] yCoordsTop = {ygap + size * 660 / 1000, ygap + size * 660 / 1000, ygap + size * 360 / 1000, ygap + size * 360 / 1000};
        g2.setColor(new Color(242, 192, 123));
        g2.fillPolygon(xCoordsTop, yCoordsTop, vertices);

        //Pegs
        int pegXGap = size * 165 / 1000;
        int pegYGap = size * 75 / 1000;
        int pegHeight = size * 100 / 1000;
        int pegWidth = size * 10 / 1000;
        int baseHeight = size * 5 / 1000;
        int baseWidth = size * 20 / 1000;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g2.setColor(new Color(184, 121, 85));
                g2.fill(new Rectangle(xgap + size * 138 / 1000 + pegXGap * j + pegYGap * i, ygap + size * 525 / 1000 - pegYGap * i, pegWidth, pegHeight));
                //Pegs base
                g2.setColor(new Color(136, 0, 21));
                g2.fill(new Rectangle(xgap + size * 133 / 1000 + pegXGap * j + pegYGap * i, ygap + size * 620 / 1000 - pegYGap * i, baseWidth, baseHeight));
            }
        }

        //Hover
        int beadx = size * 40 / 1000;
        int beady = size * 20 / 1000;
        g2.setColor(hoverColor);
        g2.fill(new Rectangle(xgap + size * 123 / 1000 + pegXGap * hoverX + pegYGap * hoverY, ygap + size * 600 / 1000 - pegYGap * hoverY - hoverZ * (beady + size * 4 / 1000), beadx, beady));

        //Place beads
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (model.getPeg(i,j).getBead(k).isSet()) {
                        g2.setColor(model.getPeg(i,j).getBead(k).getColor());
                        g2.fillRect(xgap + size * 123 / 1000 + pegXGap * j + pegYGap * i, ygap + size * 600 / 1000 - pegYGap * i - k * (beady + size * 4 / 1000), beadx, beady);
                    }
                }
            }
        }
    }

    public void setHoverLocation(int i, int j, int k, Color hoverColor) {
        hoverX = j;
        hoverY = i;
        if(k<4) hoverZ = k; else hoverZ=3;
        this.hoverColor = new Color(hoverColor.getRed(), hoverColor.getGreen(), hoverColor.getBlue(), 128);
        repaint();
    }

}

