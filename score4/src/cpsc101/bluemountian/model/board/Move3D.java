package cpsc101.bluemountian.model.board;

public class Move3D {
    private int x;
    private int y;
    private int z;

    public Move3D(int x, int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() { return z; }
}
