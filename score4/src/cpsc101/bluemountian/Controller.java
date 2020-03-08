package cpsc101.bluemountian;

import cpsc101.bluemountian.model.board.Board;
import cpsc101.bluemountian.model.player.ArtificialPlayer;
import cpsc101.bluemountian.model.player.HumanPlayer;
import cpsc101.bluemountian.model.player.Player;
import cpsc101.bluemountian.view.ComponentManager;
import cpsc101.bluemountian.view.components.*;

import javax.swing.*;
import java.awt.*;


public class Controller {
    private Board board;
    private ComponentManager frame;
    final Player[] player1 = new Player[1]; // Declaring as a final one element array so as to initialise it later.
    final Player[] player2 = new Player[1];
    private int listenersAdded=0;
    private int gameMode=-1;   // 0 is Human vs A.I, 1 is Human vs Human, 2 is AI vs AI

    public Controller(Board board, ComponentManager frame) {
        this.board = board;
        this.frame = frame;
        actionManagerFirst((IntroComponent) frame.getComponent(frame.getCurrentState()));
        listenersAdded++;
    }

    // Action Listeners for different screens ahead

    private void actionManagerFirst(IntroComponent component){
        component.addSinglePlayerListener(e ->{
            gameMode = 0;
            frame.advanceState(1);
            if(listenersAdded<2){
                player2[0] = new ArtificialPlayer("AI", Color.RED);
                actionManagerSecond((PlayerInfoComponent) frame.getComponent(frame.getCurrentState()));
                listenersAdded++;
            }
        });

        component.addMultiPlayerListener(e->{
            gameMode = 1;
            frame.advanceState(1);
            if(listenersAdded<2){
                actionManagerSecond((PlayerInfoComponent) frame.getComponent(frame.getCurrentState()));
                listenersAdded++;
            }
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

//            CanPlay move = (CanPlay) player2[0];
//            board.addBead(move.getMove(),player2[0]);

    }

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
                    System.out.println("I tried to advance");
                    addGameToFrame();
                    frame.advanceState(2);
            }else showError("Enter Player Name!");
            }else if(gameMode==1){
                if(component.validateInput()){
                    if(frame.getCurrentState()==1){
                        player1[0] = new HumanPlayer(component.getName(),component.getColor());
                        ColorOptions.removeColor(component.getColor());
                        frame.advanceState(1);
                        if(listenersAdded<3){
                            actionManagerSecond((PlayerInfoComponent) frame.getComponent(frame.getCurrentState()));
                            listenersAdded++;
                        }
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

    private void addGameToFrame(){
        GameFrame game = new GameFrame(board,player1[0].getColor(),player2[0].getColor());
        frame.addComponent(game);
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
