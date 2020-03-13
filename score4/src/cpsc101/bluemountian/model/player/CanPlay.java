package cpsc101.bluemountian.model.player;

import cpsc101.bluemountian.model.board.Board;
import cpsc101.bluemountian.model.board.Move;

public interface CanPlay {
    Move getMove();
    void resetMove();
}
