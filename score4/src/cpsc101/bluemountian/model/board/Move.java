package cpsc101.bluemountian.model.board;

/**
 * Provides a way to select a peg in the board
 *
 * @author Suyash
 */
public class Move {
    private int x;
    private int y;

    /**
     * Construct a move based on passed indices
     * @param x x co-ordinate
     * @param y y co-ordinate
     */
    public Move(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return x co-ordinate
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return y co-ordinate
     */
    public int getY() {
        return y;
    }
}
