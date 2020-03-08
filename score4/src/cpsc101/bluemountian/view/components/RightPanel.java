package cpsc101.bluemountian.view.components;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel {
    private JButton mainMenuButton;
    private JButton quitButton;
    private JButton startNewGameButton;
    private JButton goConsoleModeButton;

    private JLabel player1Label;
    private JLabel player2Label;
    private String player1Name = "Placeholder 1";
    private String player2Name = "Placeholder 2";
    private int player1Score = 0;
    private int player2Score = 0;



    public RightPanel() {
        JPanel infoPanel = createInfoPanel();
        JPanel buttonsPanel = createButtonsPanel();

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200,300));
        setBorder(BorderFactory.createRaisedBevelBorder());
        add(infoPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
    }


    private JPanel createInfoPanel() {
        JPanel playerGroupPanel = createScorePanel();

        JLabel mainTitle = new JLabel("Score 4!");
        mainTitle.setHorizontalAlignment(JLabel.CENTER);
        mainTitle.setVerticalAlignment(JLabel.CENTER);

        mainTitle.setFont(new Font(mainTitle.getFont().getName(), Font.PLAIN, 36));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 1));
        controlPanel.add(mainTitle);
        controlPanel.add(playerGroupPanel);

        return controlPanel;
    }
    private JPanel createScorePanel() {
        player1Label = new JLabel("Placeholder");
        player2Label = new JLabel("Placeholder");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.ipady = 30;
        panel.add(player1Label, c);
        c.gridy = 1;
        panel.add(player2Label, c);
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Current scores:"));

        return panel;
    }

    private JPanel createButtonsPanel() {
        startNewGameButton = new JButton("Clear Board & Start New Game");
        quitButton = new JButton("Close Game");
        mainMenuButton = new JButton("Main Menu");
        goConsoleModeButton = new JButton("Console Mode");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 20;
        c.insets = new Insets(0, 10, 20, 10);
        c.gridy = 0;
        panel.add(goConsoleModeButton, c);
        c.gridy = 1;
        panel.add(mainMenuButton, c);
        c.gridy = 2;
        panel.add(quitButton, c);
        c.gridy = 3;
        panel.add(startNewGameButton, c);

        return panel;
    }


    public void setPlayer1Name(String name) {
        player1Name = name;
        player1Label.setText(player1Name + "...... " + player1Score);
    }
    public void setPlayer1Score(int score) {
        player1Score = score;
        player1Label.setText(player1Name + "...... " + player1Score);
    }
    public void setPlayer2Name(String name) {
        player2Name = name;
        player2Label.setText(player2Name + "...... " + player2Score);
    }
    public void setPlayer2Score(int score) {
        player2Score = score;
        player2Label.setText(player2Name + "...... " + player2Score);
    }

    public void addQuitButtonListener(ActionListener listener) {
        quitButton.addActionListener(listener);
    }
    public void addGoConsoleModeButtonListener(ActionListener listener) {
        goConsoleModeButton.addActionListener(listener);
    }
    public void addMainMenuButtonListener(ActionListener listener) {
        mainMenuButton.addActionListener(listener);
    }
    public void addStartNewGameButtonListener(ActionListener listener) {
        startNewGameButton.addActionListener(listener);
    }



}
