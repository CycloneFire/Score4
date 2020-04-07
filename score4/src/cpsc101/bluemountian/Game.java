package cpsc101.bluemountian;

import cpsc101.bluemountian.model.board.WatchedBoard;
import cpsc101.bluemountian.view.ComponentManager;
import javax.swing.*;

/**
 * Provides a MVC model to be run on swing thread
 *
 * @author  Suyash
 * @version 1.0
 */
public class Game {
    private WatchedBoard board; // Model
    private ComponentManager frame; // View
    private Controller controller;  // Controller

    private void go(){
        WatchedBoard board = new WatchedBoard();    // Model
        ComponentManager frame = new ComponentManager();    // View
        Controller controller = new Controller(board,frame);    // Controller
        frame.setVisible(true);
    }

    /**
     * Runs the game
     * @param args Default arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());    // makes the UI look native
        } catch (Exception e) {
            e.printStackTrace();
        }
        Game game1 = new Game();    // Make a game
        javax.swing.SwingUtilities.invokeLater(game1::go);  // Run it in a swing thread
    }
}
