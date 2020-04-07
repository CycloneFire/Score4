package cpsc101.bluemountian.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Provides an interface for selecting game modes and other basic options
 *
 * @author Sebastian
 */
public class IntroComponent extends JComponent {
    private JButton singlePlayer;
    private JButton multiPlayer;
    private JButton botPlayer;
    private JButton about;
    private JButton instruction;
    private JButton console;
    private JButton quit;
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 250;

    /**
     * Constructs this component's UI
     */
    public IntroComponent(){
        setLayout(new BorderLayout());
        JLabel title = createTitle();
        JPanel buttons = createButtons();
        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
        console.setEnabled(false);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private JLabel createTitle() {
        JLabel label = new JLabel("Score 4!");
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 36));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        return label;
    }

    private JPanel createButtons() {
        instruction = new JButton("Instructions");
        console = new JButton("Console Mode");
        quit = new JButton("Quit");
        singlePlayer = new JButton("Single Player");
        multiPlayer = new JButton("Multi Player");
        botPlayer = new JButton("Bots Death Match");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10,10,0,10);
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.ipady = 50;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(singlePlayer, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(multiPlayer, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        panel.add(botPlayer, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.0;
        c.ipady = 10;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0,40);
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(instruction, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,50,0,0);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(console, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,0,0);
        c.gridx = 2;
        c.gridy = 1;
        panel.add(quit, c);

        return panel;
    }

    /**
     * Adds a listener for About button
     * @param listener listener to add
     */
    public void addAboutListener(ActionListener listener){
        about.addActionListener(listener);
    }

    /**
     * Adds a listener for Human vs A.I button
     * @param listener listener to add
     */
    public void addSinglePlayerListener(ActionListener listener){
        singlePlayer.addActionListener(listener);
    }

    /**
     * Adds a listener for Human vs Human button
     * @param listener listener to add
     */
    public void addMultiPlayerListener(ActionListener listener){
        multiPlayer.addActionListener(listener);
    }

    /**
     * Adds a listener for A.I vs A.I button
     * @param listener listener to add
     */
    public void addBotPlayerListener(ActionListener listener){
        botPlayer.addActionListener(listener);
    }

    /**
     * Adds a listener for Instructions button
     * @param listener listener to add
     */
    public void addInstructionListener(ActionListener listener){
        instruction.addActionListener(listener);
    }

    /**
     * Adds a listener for Console button
     * @param listener listener to add
     */
    public void addConsoleListener(ActionListener listener){
        console.addActionListener(listener);
    }

    /**
     * Adds a listener for Quit button
     * @param listener listener to add
     */
    public void addQuitListener(ActionListener listener){
        quit.addActionListener(listener);
    }

}
