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

        boardComponent = new BoardComponent(model,p1Col,p2Col);
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


//BoardComponent hover listener.

//        boardComponent.addMouseMotionListener(new MouseMotionListener() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent e) {
//
//                int x = boardComponent.getSize().width;
//                int y = boardComponent.getSize().height;
//                int size = y;
//                int xgap = (x - y) / 2;
//                int ygap = 0;
//
//                if (y > x) {
//                    size = x;
//                    xgap = 0;
//                    ygap = (y - x) / 2;
//                }
//                int pegXGap = size * 165 / 1000;
//                int pegYGap = size * 75 / 1000;
//                int pegHeight = size * 100 / 1000;
//                int pegWidth = size * 35 / 1000;
//                outerloop:
//                for (int i = 0; i < 4; i++) {
//                    for (int j = 0; j < 4; j++) {
//                        if (e.getX() > xgap + size * 103 / 1000 + pegXGap * j + pegYGap * i
//                                && e.getX() < xgap + size * 138 / 1000 + pegXGap * j + pegYGap * i + pegWidth
//                                && e.getY() > ygap + size * 525 / 1000 - pegYGap * i
//                                && e.getY() < ygap + size * 525 / 1000 - pegYGap * i + pegHeight) {
//                            boardComponent.setHoverLocation(i, j, thePegs[i][j].getMaxBead());
//                            break outerloop;
//                        } else {
//                            boardComponent.setHoverLocation(10, 10, 10);
//                        }
//                    }
//                }
//            }
//        });

//Board component click listener

//        boardComponent.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int x = boardComponent.getSize().width;
//                int y = boardComponent.getSize().height;
//                int size = y;
//                int xgap = (x - y) / 2;
//                int ygap = 0;
//
//                if (y > x) {
//                    size = x;
//                    xgap = 0;
//                    ygap = (y - x) / 2;
//                }
//                int pegXGap = size * 165 / 1000;
//                int pegYGap = size * 75 / 1000;
//                int pegHeight = size * 100 / 1000;
//                int pegWidth = size * 35 / 1000;
//                for (int i = 0; i < 4; i++) {
//                    for (int j = 0; j < 4; j++) {
//                        if (e.getX() > xgap + size * 103 / 1000 + pegXGap * j + pegYGap * i
//                                && e.getX() < xgap + size * 138 / 1000 + pegXGap * j + pegYGap * i + pegWidth
//                                && e.getY() > ygap + size * 525 / 1000 - pegYGap * i
//                                && e.getY() < ygap + size * 525 / 1000 - pegYGap * i + pegHeight) {
//                            thePegs[i][j].addBead(turnColor, boardComponent);
//                            switchColors();
//                            boardComponent.setTurnColor(turnColor);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//            }
//        });