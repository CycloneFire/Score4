package cpsc101.bluemountian.view.components;

import cpsc101.bluemountian.model.board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Provides a view containing board and sidebar
 *
 * @author Sebastian
 */
public class GameComponent extends JComponent {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    private BoardComponent boardComponent;
    private RightPanel rightPanel;

    /**
     * Constructs a board view and sidebar for given model and Players
     * @param model model to create view from
     */
    public GameComponent(Board model){
        setLayout(new BorderLayout());

        boardComponent = new BoardComponent(model);
         rightPanel = new RightPanel();

        add(boardComponent, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));

    }

    /**
     *
     * @return board view
     */
    public BoardComponent getBoardComponent() {
        return boardComponent;
    }

    /**
     *
     * @return Sidebar
     */
    public RightPanel getRightPanel() {return rightPanel;}

    /**
     * Add Mouse movement listener
     * @param listener listener to add
     */
    public void addBoardComponentMouseMotionListener(MouseMotionListener listener) {
        boardComponent.addMouseMotionListener(listener);
    }

    /**
     * Add mouse click listener
     * @param listener listener to add
     */
    public void addBoardComponentMouseListener(MouseListener listener) {
        boardComponent.addMouseListener(listener);
    }
}