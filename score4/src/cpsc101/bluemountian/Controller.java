package cpsc101.bluemountian;

import cpsc101.bluemountian.model.board.Board;
import cpsc101.bluemountian.model.player.ArtificialPlayer;
import cpsc101.bluemountian.model.player.CanPlay;
import cpsc101.bluemountian.model.player.Player;
import cpsc101.bluemountian.view.ComponentManager;
import cpsc101.bluemountian.view.components.FirstPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {
    private Board board;
    private ComponentManager frame;

    public Controller(Board board, ComponentManager frame) {
        this.board = board;
        this.frame = frame;
        final Player[] player1 = new Player[1];
        final Player[] player2 = new Player[1];
        FirstPanel p = (FirstPanel) frame.getComponent(0);
        p.addSinglePlayerListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //E.g assignment of beads
                player2[0] = new ArtificialPlayer("AI", Color.CYAN);        //
                CanPlay move = (CanPlay) player2[0];        //
                board.addBead(move.getMove(),player2[0]);
            }
        });

    }
}
