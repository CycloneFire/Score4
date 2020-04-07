package cpsc101.bluemountian.model.board;

import cpsc101.bluemountian.model.player.Player;

/**
 * Provides a way to store beads to the game
 *
 * @author Suyash
 */
public class Peg {
    private Bead[] beads = new Bead[4];
    private int beadCount;

    /**
     * Construct a peg with no beads
     */
    public Peg(){
        for(int i=0;i<4;i++)beads[i]=new Bead();  // Initialise empty Beads
        beadCount=0;
    }

    /**
     *
     * @param i index of the bead
     * @return bead on passed index on this peg
     */
    public Bead getBead(int i) {
        return beads[i];
    }

    /**
     *
     * @return is bead count smaller than 4
     */
    public boolean canPlace(){
        return beadCount != 4;

    }

    /**
     *
     * @return Number of beads on this peg
     */
    public int getBeadCount() {
        return beadCount;
    }

    /**
     * Adds a bead on this peg
     * @param player Player for which to add bead
     * @return whether adding the bead was successful or not
     */
    public Boolean addBead(Player player){
        if(beadCount==4)return false;
        beads[beadCount].setBead(player);
        beadCount++;
        return true;
    }
}
