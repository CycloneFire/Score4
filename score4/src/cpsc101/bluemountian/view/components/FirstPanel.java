package cpsc101.bluemountian.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FirstPanel extends JPanel {
    private JLabel title;
    private JButton about;
    private JButton singlePlayer;
    private JButton multiPlayer;
    private JButton botPlayer;
    private JButton instruction;
    private JButton console;
    private JButton quit;
    public FirstPanel(){
        this.setLayout(new BorderLayout());
    }

    public void addAboutListener(ActionListener listener){
        about.addActionListener(listener);
    }
    public void addSinglePlayerListener(ActionListener listener){
        singlePlayer.addActionListener(listener);
    }
    public void addMultiPlayerListener(ActionListener listener){
        multiPlayer.addActionListener(listener);
    }
    public void addBotPlayerListener(ActionListener listener){
        botPlayer.addActionListener(listener);
    }
    public void instructionListener(ActionListener listener){
        instruction.addActionListener(listener);
    }
    public void consoleListener(ActionListener listener){
        console.addActionListener(listener);
    }
    public void quitListener(ActionListener listener){
        quit.addActionListener(listener);
    }

}
