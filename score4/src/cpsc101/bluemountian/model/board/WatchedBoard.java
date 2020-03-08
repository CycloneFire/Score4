package cpsc101.bluemountian.model.board;

import java.util.ArrayList;

public class WatchedBoard extends Board{
    private ArrayList<Runnable> actions= new ArrayList<Runnable>();

    public void addAction(Runnable r){
        actions.add(r);
    }

    @Override
    public void updateBoard() {
        for(Runnable r:actions)r.run();
    }
}
