package cpsc101.bluemountian.model.board;

import cpsc101.bluemountian.model.player.Player;

/**
 * The way to assign a value to bead is (Constructor):
 * Bead[] beads = new Bead[4]
 * beads[0] = new Bead(Player1)
 * OR ( Mutator )
 * Bead[] beads = new Bead[4]
 * beads[0] = new Bead()
 * beads[0].setBead(Player1)
 */

public class Bead extends Player {
    private Boolean set;    // Determines whether bead is set or not

    public Bead(){
        super();
        set=false;
    }

    public Bead(Player player){
     super(player.getName(),player.getColor());
     set=true;
    }

    public void setBead(Player player){
        super.setColor(player.getColor());
        super.setName(player.getName());
        set=true;
    }

    public Boolean isSet() {
        return set;
    }
}
