package cpsc101.bluemountian.model.board;

import cpsc101.bluemountian.model.player.Player;

/**
 * Stores an array of 4 beads and gives methods to access and mutate them.
 */
public class Peg {
    private Bead[] beads = new Bead[4];
    private int beadCount;

    public Peg(){
        for(int i=0;i<4;i++)beads[i]=new Bead();  //Initialise empty Beads
        beadCount=0;
    }

    public void updatePeg(){}

    public Bead getBead(int i) {
        return beads[i];
    }

    public Bead[] getBeads() {
        return beads;
    }

    public Boolean addBead(Player player){
        beadCount++;
        if(beadCount==4)return false;
        beads[beadCount].setBead(player);
        updatePeg();
        return true;
    }
}
