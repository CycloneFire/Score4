package cpsc101.bluemountian.model.player;

import cpsc101.bluemountian.model.board.Move;

/**
 * Provides a way to access any kind of player and get or reset their moves
 */
public interface CanPlay {
    Move getMove();
    void resetMove();
}
