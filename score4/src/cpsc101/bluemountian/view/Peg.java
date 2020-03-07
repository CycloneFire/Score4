package cpsc101.bluemountian.view;

import cpsc101.bluemountian.view.components.BoardComponent;

import java.awt.*;

public class Peg {
    private int[] beads;
    private int pegXLocation;
    private int pegYLocation;

    public Peg(int i, int j) {
        beads = new int[]{0, 0, 0, 0};
        pegXLocation = i;
        pegYLocation = j;
    }

    public void addBead(Color turnColor, BoardComponent boardComponent) {
        int colorNumber;
        if (turnColor.equals(java.awt.Color.WHITE)) {
            colorNumber = 1;
        } else if (turnColor.equals(java.awt.Color.BLACK)) {
            colorNumber = 2;
        } else {
            colorNumber = 0;
        }

        for (int i = 0; i < 4; i++) {
            if (beads[i] == 0) {
                beads[i] = colorNumber;
                boardComponent.addBead(pegYLocation, pegXLocation, i, colorNumber);
                break;
            }
        }
    }

    public int getMaxBead() {
        int max = 0;
        for (int i = 0; i < 4; i++) {
            if (beads[i] == 0) {
                max = i;
                break;
            }
        }
        return max;
    }

    public int[] getBeads() {
        return beads;
    }

    public int getPegXLocation() {
        return pegXLocation;
    }

    public int getPegYLocation() {
        return pegYLocation;
    }

    //TO DO
    public void setBeads(int[] beads) {
        this.beads = beads;
    }
}
