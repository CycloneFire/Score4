package cpsc101.bluemountian.model.player;

import cpsc101.bluemountian.model.board.Board;
import cpsc101.bluemountian.model.board.Move;

import java.awt.*;
import java.util.Random;

public class ArtificialPlayer extends Player implements CanPlay{
    private Move move;
    private Boolean hasMove = false;
    public ArtificialPlayer(String name, Color color) {
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

    public void setMove(Board board){
        hasMove=true;
        move = moveFinder();
    };

    private Move moveFinder(){
        Random random = new Random();
        return new Move(random.nextInt(4),random.nextInt(4));
    }
}
