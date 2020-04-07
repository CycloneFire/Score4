package cpsc101.bluemountian.model.board;

import java.util.ArrayList;

/**
 * Provides a way to update unrelated structures of game when board is updated
 *
 * @author Suyash
 */
public class WatchedBoard extends Board{
    private ArrayList<Runnable> actions= new ArrayList<>();

    /**
     * Add the passed code to the queue to be run
     * @param r What to run
     */
    public void addAction(Runnable r){
        actions.add(r);
    }

    /**
     * Runs all the actions whenever board is updated
     */
    @Override
    public void updateBoard() {
        for(Runnable r:actions)r.run();
    }
}
