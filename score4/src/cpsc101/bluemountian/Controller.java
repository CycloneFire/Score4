package cpsc101.bluemountian;

import cpsc101.bluemountian.model.board.Bead;
import cpsc101.bluemountian.model.board.Move;
import cpsc101.bluemountian.model.board.WatchedBoard;
import cpsc101.bluemountian.model.board.WinningCondition;
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
import java.util.ArrayList;


public class Controller {
    private WatchedBoard board;
    private ComponentManager frame;
    private GameComponent game;
    private WinningCondition win;
    private final Player[] player1 = new Player[1]; // Declaring as a final one element array to initialise it later.
    private final Player[] player2 = new Player[1];
    private int gameMode=-1;   // 0 is Human vs A.I, 1 is Human vs Human, 2 is AI vs AI.
    private boolean currentTurn = false; // False is player 1, true is player 2.
    private volatile ArrayList<Boolean> gameEnded = new ArrayList<>();
    // If won/lost/drawn. also used to stop threads when going back.
    private boolean mouseMoved = false; // Makes sure hover effect generates only if mouse wasn't moved during AI's move
    private int currentRun=0;




    public Controller(WatchedBoard board, ComponentManager frame) {
        this.board = board;
        this.frame = frame;
        addActions();
        currentRun++;
        gameEnded.add(false);
        this.board.addAction(()->{
            currentTurn=!currentTurn;
            game.getBoardComponent().repaint();
            // This creates a hover effect just after adding a bead, takes last move of alternating player and creates
            //a hover effect there with current player's colour.
            if(gameMode==0){
                if(!currentTurn && !mouseMoved)game.getBoardComponent().setHoverLocation(((CanPlay) player1[0])
                                .getMove().getX(),((CanPlay) player1[0]).getMove().getY(),board
                        .getPeg(((CanPlay)player1[0]).getMove()).getBeadCount(),player1[0].getColor());
            }
            else if(gameMode==1){
                if(!currentTurn)game.getBoardComponent().setHoverLocation(((CanPlay) player2[0]).getMove().getX(),
                        ((CanPlay) player2[0]).getMove().getY(),board.getPeg(((CanPlay)player2[0]).getMove())
                                .getBeadCount(),player1[0].getColor());
                else game.getBoardComponent().setHoverLocation(((CanPlay) player1[0]).getMove().getX(),
                        ((CanPlay) player1[0]).getMove().getY(),board.getPeg(((CanPlay)player1[0]).getMove())
                                .getBeadCount(),player2[0].getColor());
            }
        });
    }

    private void addActions(){
        actionManagerFirst((IntroComponent) frame.getComponent(0));
        actionManagerSecond((PlayerInfoComponent)frame.getComponent(1));
        actionManagerSecond((PlayerInfoComponent)frame.getComponent(2));
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

        component.addInstructionListener(e->showDialog("Instructions",
                "This was created by Blue Mountain",()->{}));
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
                    ColorOptions.removeColor(player1[0].getColor());
                    player2[0] = new ArtificialPlayer("A.I",ColorOptions.getRandomColor());
                    ColorOptions.removeColor(player2[0].getColor());
                    addGameToFrame();
                    frame.advanceState(2);
            }else showDialog("Error","Enter Player Name!",()->{});
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
                }else showDialog("Error","Enter Player Name!",()->{});
            }
        });
    }

    // Controls Mouse hover and mouse click on game board.

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
                                    && e.getY() < ygap + size * 525 / 1000 - pegYGap * i + pegHeight &&
                                        !gameEnded.get(currentRun)) {
                                if(gameMode==0){
                                    mouseMoved=true;
                                    if(!currentTurn){
                                        component.getBoardComponent().setHoverLocation(i,j,
                                                board.getPeg(i,j).getBeadCount(), player1[0].getColor());
                                    }
                                }
                                else if(gameMode == 1) {
                                    if(!currentTurn){
                                        component.getBoardComponent().setHoverLocation(i,j,
                                                board.getPeg(i,j).getBeadCount(), player1[0].getColor());
                                    } else {
                                        component.getBoardComponent().setHoverLocation(i,j,
                                                board.getPeg(i,j).getBeadCount(), player2[0].getColor());
                                    }


                                }
                                break outerloop;
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
                                    mouseMoved=false;
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

    // Controls actions for sidebar on game screen

    private void actionManagerRight(RightPanel component){
        component.setPlayer1Name(player1[0].getName());
        component.setPlayer2Name(player2[0].getName());

        component.addMainMenuButtonListener(e->
            show2ButtonDialog("Main Menu?","Do you want to go back to main menu?","Menu",()->{
                gameMode=-1;
                resetBoard();
                ColorOptions.resetColors();
                frame.reInit();
                addActions();       // Add action listeners again.
            })
            );
        component.addQuitButtonListener(e->
            show2ButtonDialog("Exit game?","Do you want to exit?","Exit",
                    ()->System.exit(1))
        );
        component.addStartNewGameButtonListener(e->
            show2ButtonDialog("Restart game?","Do you want to reset board?","Clear",
                    ()->{
                        resetBoard();
                        game.getBoardComponent().repaint();
                        gameManager();
                    })
        );

    }

    private void resetBoard(){
        currentTurn=false;
        ((CanPlay) player1[0]).resetMove();
        ((CanPlay) player2[0]).resetMove();
        gameEnded.set(currentRun,true);
        board.reInit();
        win = new WinningCondition(board,player1[0].getColor());
        currentRun++;
    }

    private void gameManager(){
        int threadNum = currentRun;
        gameEnded.add(false);
        Thread T = new Thread(()->{
            System.out.println("I m invincible : "+threadNum);
            while(!gameEnded.get(threadNum)) {
                Bead winFind =  win.winCheck();
                if(winFind.isSet()){
                    gameEnded.set(threadNum,true);
                    if (winFind.getColor() == player1[0].getColor())game.getRightPanel().incrementPlayer1Score();
                    else if(winFind.getColor() == player2[0].getColor())game.getRightPanel().incrementPlayer2Score();
                    showDialog("Game Complete",winFind.getName()+" won the game.",()->
                        show2ButtonDialog("Restart game?","Do you want to play again?","Yes",
                                ()->{
                                    resetBoard();
                                    game.getBoardComponent().repaint();
                                    gameManager();
                                })
                    );
                    break;
                }


                if (gameMode == 0) {
                    if(!currentTurn){
                        while(!((HumanPlayer) player1[0]).getHasMove())Thread.onSpinWait();
                        board.addBead(((CanPlay) player1[0]).getMove(),player1[0]);
                    }else{
                        try {
                            ((ArtificialPlayer) player2[0]).setMove(board);
                            Thread.sleep(500);  // Wait half a second before A.I Plays its move
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        board.addBead(((CanPlay) player2[0]).getMove(),player2[0]);
                    }
                }else if (gameMode == 1) {
                    if(!currentTurn){
                        while (!((HumanPlayer) player1[0]).getHasMove())Thread.onSpinWait();
                        if(!gameEnded.get(threadNum)){  // Thread could 've been closed while waiting.
                            board.addBead(((CanPlay) player1[0]).getMove(),player1[0]);
                        }
                    }else{
                        while (!((HumanPlayer) player2[0]).getHasMove())Thread.onSpinWait();
                        board.addBead(((CanPlay) player2[0]).getMove(),player2[0]);
                    }
                }else if (gameMode == 2) {
                    if(!currentTurn){
                        try {
                            ((ArtificialPlayer) player1[0]).setMove(board);
                            Thread.sleep(500);  // Wait half a second before A.I Plays its move
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        board.addBead(((CanPlay) player1[0]).getMove(),player1[0]);
                    }else{
                        try {
                            ((ArtificialPlayer) player2[0]).setMove(board);
                            Thread.sleep(500);  // Wait half a second before A.I Plays its move
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        board.addBead(((CanPlay) player2[0]).getMove(),player2[0]);
                    }
                }
            }
        });
        T.start();

    }

    private void addGameToFrame(){
        game = new GameComponent(board,player1[0].getColor(),player2[0].getColor());
        win = new WinningCondition(board,player1[0].getColor());
        frame.addComponent(game);
        actionManagerBoard(game);
        actionManagerRight(game.getRightPanel());
        gameManager();
    }

    private void showDialog(String title,String errorMsg,Runnable runner){
        JDialog dialog = new JDialog(frame, title);
        dialog.setLayout(new BorderLayout());

        JLabel label = new JLabel(errorMsg);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(200,50));

        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(e->{
            dialog.setVisible(false);
            runner.run();
        });
        okBtn.setPreferredSize(new Dimension(60,35));

        dialog.add(label,BorderLayout.NORTH);
        dialog.add(okBtn,BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private void show2ButtonDialog(String title, String dialogLabel,String okBtnLabel, Runnable run){
        JDialog dialog = new JDialog(frame,title);
        dialog.setLayout(new BorderLayout());

        JLabel label = new JLabel(dialogLabel);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(200,50));

        JButton okBtn = new JButton(okBtnLabel);
        okBtn.addActionListener(e->{
            run.run();
            dialog.setVisible(false);
        });
        okBtn.setPreferredSize(new Dimension(90,35));
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e->dialog.setVisible(false));
        cancelBtn.setPreferredSize(new Dimension(90,35));
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);

        dialog.add(label,BorderLayout.NORTH);
        dialog.add(btnPanel,BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

}
