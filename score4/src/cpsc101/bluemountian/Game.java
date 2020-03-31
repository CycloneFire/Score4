package cpsc101.bluemountian;

import cpsc101.bluemountian.model.board.WatchedBoard;
import cpsc101.bluemountian.view.ComponentManager;
import javax.swing.*;

public class Game {
    private WatchedBoard board;
    private ComponentManager frame;
    private Controller controller;

    private void go(){
        WatchedBoard board = new WatchedBoard();
        ComponentManager frame = new ComponentManager();
        Controller controller = new Controller(board,frame);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Game game1 = new Game();
        javax.swing.SwingUtilities.invokeLater(game1::go);
    }
}
