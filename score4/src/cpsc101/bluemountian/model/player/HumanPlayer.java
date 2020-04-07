package cpsc101.bluemountian.model.player;

import cpsc101.bluemountian.model.board.Move;
import java.awt.*;

/**
 * Provides a Human player to game that gets it's moves from human interaction
 *
 * @author Suyash
 */
public class HumanPlayer extends Player implements CanPlay{

    private Move move;
    private Boolean hasMove=false;

    /**
     * Constructs a Human player with given name and color
     * @param name name of player
     * @param color color of player
     */
    public HumanPlayer(String name, Color color) {
        super(name, color);
    }

    /**
     *
     * @return the move set by the user
     */
    @Override
    public Move getMove() {
        hasMove=false;
        return move;
    }

    /**
     * Resets whatever move was set by the user
     */
    @Override
    public void resetMove() {
        hasMove=false;
    }

    /**
     * Sets the move selected by the user
     * @param move the peg user clicked on
     */
    public void setMove(Move move){
        this.move= move;
        hasMove=true;
    }

    /**
     *
     * @return is a move already set for this player
     */
    public Boolean getHasMove(){
        return hasMove;
    }
}
