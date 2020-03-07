package cpsc101.bluemountian;

import cpsc101.bluemountian.model.board.Move;
import cpsc101.bluemountian.model.board.WatchedBoard;
import cpsc101.bluemountian.model.player.HumanPlayer;
import cpsc101.bluemountian.view.ComponentManager;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        WatchedBoard board = new WatchedBoard();
        ComponentManager frame = new ComponentManager();
        Controller controller = new Controller(board,frame);
        frame.setVisible(true);


        //board.addBead(new Move(3,1),new HumanPlayer("Suyash", Color.BLACK));

    }
}
