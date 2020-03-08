package cpsc101.bluemountian.view.components;

import cpsc101.bluemountian.model.board.Board;

import javax.swing.*;
import java.awt.*;

public class BoardComponent extends JComponent {

    int hoverX = 10;
    int hoverY = 10;
    int hoverZ = 0;
    Color turnColor = new Color(255, 255, 255, 128);
    Color p1Color;
    Color p2Color;
    int[][][] beads = new int[4][4][4];
    Board model;

    public BoardComponent(){
        setPreferredSize(new Dimension(400,400));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    beads[i][j][k] = 0;
                }
            }
        }
    }
    public BoardComponent(Board model,Color p1Color,Color p2Color) {
        this(); // Calls default constructor above to initialise beads
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
        //Missing color
        //Missing height
        int beadx = size * 40 / 1000;
        int beady = size * 20 / 1000;
        g2.setColor(turnColor);
        g2.fill(new Rectangle(xgap + size * 123 / 1000 + pegXGap * hoverX + pegYGap * hoverY, ygap + size * 600 / 1000 - pegYGap * hoverY - hoverZ * (beady + size * 4 / 1000), beadx, beady));

        //Place beads
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                for (int k = 0; k < 4; k++) {
                    if (beads[i][j][k] == 1) {
                        g2.setColor(p1Color);
                        g2.fill(new Rectangle(xgap + size * 123 / 1000 + pegXGap * i + pegYGap * j, ygap + size * 600 / 1000 - pegYGap * j - k * (beady + size * 4 / 1000), beadx, beady));
                    } else if (beads[i][j][k] == 2) {
                        g2.setColor(p2Color);
                        g2.fill(new Rectangle(xgap + size * 123 / 1000 + pegXGap * i + pegYGap * j, ygap + size * 600 / 1000 - pegYGap * j - k * (beady + size * 4 / 1000), beadx, beady));
                    }
                }
            }
        }
    }

//    public void addBead(int i, int j, int k, int colorNumber) {
//        beads[i][j][k] = colorNumber;
//        if (k < 3) {
//            setHoverLocation(j, i, k + 1);
//        }
//        repaint();
//    }

    public void setHoverLocation(int i, int j, int k) {
        hoverX = j;
        hoverY = i;
        hoverZ = k;
        repaint();
    }

    public void setTurnColor(Color turnColor) {
        this.turnColor = new Color(turnColor.getRed(), turnColor.getGreen(), turnColor.getBlue(), 128);
    }
}

