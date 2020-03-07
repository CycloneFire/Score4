package cpsc101.bluemountian.model.player;

import cpsc101.bluemountian.model.board.Board;
import cpsc101.bluemountian.model.board.Move;

import java.awt.*;

public class HumanPlayer extends Player implements CanPlay{

    public HumanPlayer(String name, Color color) {
        super(name, color);
    }

    @Override
    public Move getMove(Board board) {
        return new Move(0,0);
    }
}
