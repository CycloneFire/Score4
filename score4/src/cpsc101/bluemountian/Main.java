package cpsc101.bluemountian;

import cpsc101.bluemountian.model.board.WatchedBoard;
import cpsc101.bluemountian.view.ComponentManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        WatchedBoard board = new WatchedBoard();
        ComponentManager frame = new ComponentManager();
        Controller controller = new Controller(board,frame);
        frame.setVisible(true);


        //board.addBead(new Move(3,1),new HumanPlayer("Suyash", Color.BLACK));

    }
}
