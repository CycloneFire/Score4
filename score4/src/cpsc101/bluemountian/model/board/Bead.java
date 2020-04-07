package cpsc101.bluemountian.model.board;

import cpsc101.bluemountian.model.player.Player;


/**
 * Provides a fundamental unit to the board for game
 *
 * @author Suyash
 */
public class Bead extends Player {
    private Boolean set;    // Determines whether bead is set or not

    /**
     * Constructs an empty bead
     */
    public Bead(){
        super();
        set=false;
    }

    /**
     * Constructs a bead from another player
     * @param player
     */
    public Bead(Player player){
     super(player.getName(),player.getColor());
     set=true;
    }

    /**
     * Set the bead's color and name using a player object
     * @param player Set this bead to belong to passed player
     */
    public void setBead(Player player){
        super.setColor(player.getColor());
        super.setName(player.getName());
        set=true;
    }

    /**
     * Set the bead's color and name using another bead
     * @param player Set this bead to belong to passed player
     */
    public void setBead(Bead player){
        super.setColor(player.getColor());
        super.setName(player.getName());
        set=true;
    }

    /**
     *  Checks if bead is set or not
     * @return is bead set
     */
    public Boolean isSet() {
        return set;
    }

    /**
     * Checks if one bead is equal to another
     * @param obj Object to compare to
     * @return is passed object equal to this object
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Bead){
            return ((Bead) obj).getColor() == super.getColor() && ((Player) obj).getName().equals(super.getName());
        }
        return false;
    }
}
