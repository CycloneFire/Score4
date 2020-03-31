package cpsc101.bluemountian.view.components;

import cpsc101.bluemountian.model.board.Board;
import cpsc101.bluemountian.view.components.BoardComponent;
import cpsc101.bluemountian.view.components.RightPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GameComponent extends JComponent {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    private BoardComponent boardComponent;
    private RightPanel rightPanel;

    public GameComponent(Board model, Color p1Col, Color p2Col){
        setLayout(new BorderLayout());

        boardComponent = new BoardComponent(model);
         rightPanel = new RightPanel();

        add(boardComponent, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));

    }

    public BoardComponent getBoardComponent() {
        return boardComponent;
    }

    public RightPanel getRightPanel() {return rightPanel;}

    public void addBoardComponentMouseMotionListener(MouseMotionListener listener) {
        boardComponent.addMouseMotionListener(listener);
    }

    public void addBoardComponentMouseListener(MouseListener listener) {
        boardComponent.addMouseListener(listener);
    }
}