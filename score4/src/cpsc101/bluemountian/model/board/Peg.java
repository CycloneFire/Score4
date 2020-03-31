package cpsc101.bluemountian.model.board;

import cpsc101.bluemountian.model.player.Player;

/**
 * Stores an array of 4 beads and gives methods to access and mutate them.
 */
public class Peg {
    private Bead[] beads = new Bead[4];
    private int beadCount;

    public Peg(){
        for(int i=0;i<4;i++)beads[i]=new Bead();  // Initialise empty Beads
        beadCount=0;
    }

    public Bead getBead(int i) {
        return beads[i];
    }

    public boolean canPlace(){
        return beadCount != 4;

    }

    public int getBeadCount() {
        return beadCount;
    }

    public Boolean addBead(Player player){
        if(beadCount==4)return false;
        beads[beadCount].setBead(player);
        beadCount++;
        return true;
    }
}
