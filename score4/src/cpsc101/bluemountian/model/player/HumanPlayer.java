package cpsc101.bluemountian.model.player;

import cpsc101.bluemountian.model.board.Board;
import cpsc101.bluemountian.model.board.Move;

import java.awt.*;

public class HumanPlayer extends Player implements CanPlay{

    private Move move;
    private Boolean hasMove=false;
    public HumanPlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public Move getMove() {
        hasMove=false;
        return move;
    }

    @Override
    public void resetMove() {
        hasMove=false;
    }

    public void setMove(Move move){
        this.move= move;
        hasMove=true;
    }

    public Boolean getHasMove(){
        return hasMove;
    }
}
