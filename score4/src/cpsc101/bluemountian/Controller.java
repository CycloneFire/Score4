package cpsc101.bluemountian;

import cpsc101.bluemountian.model.board.Board;
import cpsc101.bluemountian.model.board.Move;
import cpsc101.bluemountian.model.board.WatchedBoard;
import cpsc101.bluemountian.model.player.ArtificialPlayer;
import cpsc101.bluemountian.model.player.CanPlay;
import cpsc101.bluemountian.model.player.HumanPlayer;
import cpsc101.bluemountian.model.player.Player;
import cpsc101.bluemountian.view.ComponentManager;
import cpsc101.bluemountian.view.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class Controller {
    private WatchedBoard board;
    private ComponentManager frame;
    final Player[] player1 = new Player[1]; // Declaring as a final one element array so as to initialise it later.
    final Player[] player2 = new Player[1];
    private int gameMode=-1;   // 0 is Human vs A.I, 1 is Human vs Human, 2 is AI vs AI.
    boolean currentTurn = false; // False is player 1, true is player 2.
    boolean gameEnded = false; // If won/lost/drawn.
    volatile boolean turnComplete = false; // tells if a move is set for player or not.
    GameComponent game;

    public Controller(WatchedBoard board, ComponentManager frame) {
        this.board = board;
        this.frame = frame;
        actionManagerFirst((IntroComponent) frame.getComponent(0));
        actionManagerSecond((PlayerInfoComponent)frame.getComponent(1));
        actionManagerSecond((PlayerInfoComponent)frame.getComponent(2));
        this.board.addAction(()->{
            currentTurn=!currentTurn;
            game.getBoardComponent().repaint();
        });
    }

    // Action Listeners for different screens ahead

    // Action Listeners for Intro Screen
    // Controls game mode.

    private void actionManagerFirst(IntroComponent component){
        component.addSinglePlayerListener(e ->{
            gameMode = 0;
            frame.advanceState(1);
            player2[0] = new ArtificialPlayer("AI", Color.RED);
        });

        component.addMultiPlayerListener(e->{
            gameMode = 1;
            frame.advanceState(1);
        });

        component.addBotPlayerListener(e->{
            gameMode = 2;
            Color randomColor1 = ColorOptions.getRandomColor();
            ColorOptions.removeColor(randomColor1);
            Color randomColor2 = ColorOptions.getRandomColor();
            ColorOptions.removeColor(randomColor2);
            player1[0] = new ArtificialPlayer("AI 1", randomColor1);
            player2[0] = new ArtificialPlayer("AI 2", randomColor2);
            addGameToFrame();
            frame.advanceState(3);
        });

        component.addInstructionListener(e->showError("This was created by Blue Mountain"));
        component.addQuitListener(e->System.exit(1));

    }

    // ActionListeners for PlayerInfo screen ( Both Player1 and Player 2 handled )
    // Handles going back forward and initialising players as either human or A.I

    private void actionManagerSecond(PlayerInfoComponent component){
        component.addBackListener(e->{
            if(gameMode==1)ColorOptions.resetColors();
            frame.advanceState(-1);
        });   //Go back 1 state
        component.addNextListener(e->{  // Get data from fields and set them based on game mode
            if(gameMode==0){
                if(component.validateInput()){
                    player1[0] = new HumanPlayer(component.getName(),component.getColor());
                    player2[0] = new ArtificialPlayer("A..I",ColorOptions.getRandomColor());
                    ColorOptions.removeColor(component.getColor());
                    addGameToFrame();
                    frame.advanceState(2);
            }else showError("Enter Player Name!");
            }else if(gameMode==1){
                if(component.validateInput()){
                    if(frame.getCurrentState()==1){
                        player1[0] = new HumanPlayer(component.getName(),component.getColor());
                        ColorOptions.removeColor(component.getColor());
                        frame.advanceState(1);
                    }else if(frame.getCurrentState()==2){
                        player2[0] = new HumanPlayer(component.getName(),component.getColor());
                        ColorOptions.removeColor(component.getColor());
                        addGameToFrame();
                        frame.advanceState(1);
                    }
                }else showError("Enter Player Name!");
            }
        });
    }


    private void actionManagerBoard(GameComponent component){

        component.addBoardComponentMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(gameMode==0 || gameMode==1){
                    int x = component.getBoardComponent().getSize().width;
                    int y = component.getBoardComponent().getSize().height;
                    int size = y;
                    int xgap = (x - y) / 2;
                    int ygap = 0;

                    if (y > x) {
                        size = x;
                        xgap = 0;
                        ygap = (y - x) / 2;
                    }
                    int pegXGap = size * 165 / 1000;
                    int pegYGap = size * 75 / 1000;
                    int pegHeight = size * 100 / 1000;
                    int pegWidth = size * 35 / 1000;
                    outerloop:
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (e.getX() > xgap + size * 103 / 1000 + pegXGap * j + pegYGap * i
                                    && e.getX() < xgap + size * 138 / 1000 + pegXGap * j + pegYGap * i + pegWidth
                                    && e.getY() > ygap + size * 525 / 1000 - pegYGap * i
                                    && e.getY() < ygap + size * 525 / 1000 - pegYGap * i + pegHeight) {
                                if(gameMode==0){
                                    if(!currentTurn){
                                        System.out.println("HELLLLLO");
                                        component.getBoardComponent().setHoverLocation(j,j, board.getPeg(i,j).getBeadCount()
                                                , player1[0].getColor());
                                    }
                                }
                                else if(gameMode == 1) {
                                    if(!currentTurn){
                                        component.getBoardComponent().setHoverLocation(i,j, board.getPeg(i,j).getBeadCount()
                                                , player1[0].getColor());
                                    } else {
                                        component.getBoardComponent().setHoverLocation(i,j, board.getPeg(i,j).getBeadCount()
                                                , player2[0].getColor());
                                    }

                                    break outerloop;
                                }

                            } else {
                                component.getBoardComponent().setHoverLocation(10, 10, 10,Color.BLACK);
                            }
                        }
                    }
                }

            }
        });


        component.addBoardComponentMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(gameMode==0 || gameMode==1){
                    int x = component.getBoardComponent().getSize().width;
                    int y = component.getBoardComponent().getSize().height;
                    int size = y;
                    int xgap = (x - y) / 2;
                    int ygap = 0;

                    if (y > x) {
                        size = x;
                        xgap = 0;
                        ygap = (y - x) / 2;
                    }
                    int pegXGap = size * 165 / 1000;
                    int pegYGap = size * 75 / 1000;
                    int pegHeight = size * 100 / 1000;
                    int pegWidth = size * 35 / 1000;
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (e.getX() > xgap + size * 103 / 1000 + pegXGap * j + pegYGap * i
                                    && e.getX() < xgap + size * 138 / 1000 + pegXGap * j + pegYGap * i + pegWidth
                                    && e.getY() > ygap + size * 525 / 1000 - pegYGap * i
                                    && e.getY() < ygap + size * 525 / 1000 - pegYGap * i + pegHeight) {
                                if(gameMode==0){
                                    if(!currentTurn)((HumanPlayer) player1[0]).setMove(new Move(i, j));
                                }else if(gameMode == 1){
                                    if(!currentTurn) {
                                        ((HumanPlayer) player1[0]).setMove(new Move(i, j));
                                    }else{
                                        ((HumanPlayer) player2[0]).setMove(new Move(i,j));
                                    }
                                }
                            }
                        }
                    }
                }

            }
        });
    }

    private void gameManager(){
        Thread T = new Thread(()->{
            while(!gameEnded) {
                if (gameMode == 0) {
                    while(!((HumanPlayer) player1[0]).getHasMove())Thread.onSpinWait();
                    board.addBead(((CanPlay) player1[0]).getMove(),player1[0]);
                    game.getBoardComponent().repaint();
                    try {
                        ((ArtificialPlayer) player2[0]).setMove(board);
                        Thread.sleep(500);  // Wait half a second before A.I Plays its move
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    board.addBead(((CanPlay) player2[0]).getMove(),player2[0]);
                    turnComplete=false;
                }else if (gameMode == 1) {
                    if(!currentTurn){
                        while (!((HumanPlayer) player1[0]).getHasMove())Thread.onSpinWait();
                        board.addBead(((CanPlay) player1[0]).getMove(),player1[0]);
                    }else{
                        while (!((HumanPlayer) player2[0]).getHasMove())Thread.onSpinWait();
                        board.addBead(((CanPlay) player2[0]).getMove(),player2[0]);
                    }
                }else if (gameMode == 2) {
                    try {
                        ((ArtificialPlayer) player1[0]).setMove(board);
                        Thread.sleep(500);  // Wait half a second before A.I Plays its move
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    board.addBead(((CanPlay) player1[0]).getMove(),player1[0]);
                    turnComplete=false;

                    try {
                        ((ArtificialPlayer) player2[0]).setMove(board);
                        Thread.sleep(500);  // Wait half a second before A.I Plays its move
                        turnComplete=true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    board.addBead(((CanPlay) player1[0]).getMove(),player1[0]);
                    turnComplete=false;
                }
            }
        });
        T.start();

    }

    private void addGameToFrame(){
        game = new GameComponent(board,player1[0].getColor(),player2[0].getColor());
        frame.addComponent(game);
        actionManagerBoard(game);
        gameManager();
    }

    private void showError(String errorMsg){
        JDialog dialog = new JDialog(frame, "Error");
        dialog.setLayout(new BorderLayout());

        JLabel label = new JLabel(errorMsg);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(200,50));

        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(e->dialog.setVisible(false));
        okBtn.setPreferredSize(new Dimension(60,35));

        dialog.add(label,BorderLayout.NORTH);
        dialog.add(okBtn,BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

}
