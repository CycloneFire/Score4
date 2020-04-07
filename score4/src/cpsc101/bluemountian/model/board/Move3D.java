package cpsc101.bluemountian.model.board;

/**
 * Provides a way to select a bead in the board.
 *
 * @author Suyash
 */
public class Move3D {
    private int x;
    private int y;
    private int z;

    /**
     * Constructs a 3D move based on passed indices
     * @param x x co-ordinate
     * @param y y co-ordinate
     * @param z z co-ordinate
     */
    public Move3D(int x, int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
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

    /**
     *
     * @return z co-ordinate
     */
    public int getZ() { return z; }
}
