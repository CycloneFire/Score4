package cpsc101.bluemountian;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 100;

    private JButton clearBoardButton;
    private JButton printBoardButton;
    private JButton quitButton;
    private JCheckBox player1CheckBox;
    private JCheckBox player2CheckBox;
    private JPanel titlePanel;
    private JButton startEndGameButton;
    private JRadioButton blackRadioButton;
    private JRadioButton whiteRadioButton;
    private JButton goBeadButton;
    private JComboBox comboBoxColor;
    private JComboBox comboBoxRow;
    private JComboBox comboBoxColumn;
    private JComboBox comboBoxAddRemove;
    private JTextField commandTextField;
    private JButton goTextButton;
    private JButton goClassicModeButton;
    private JButton drawBoardButton;
    private JButton randomButton;
    private JLabel mainTitle;

    public GameFrame() {
        createRightPanel();

        clearBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Board cleared.");
            }
        });
        printBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Printing board...");
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Quitting...");
                System.exit(0);
            }
        });
        blackRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Black goes first.");
            }
        });
        whiteRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("White goes first.");
            }
        });
        ActionListener playerAI;
        player1CheckBox.addActionListener(playerAI = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player1CheckBox.isSelected() && player2CheckBox.isSelected()) {
                    System.out.println("Both players are now controlled by A.I");
                } else if (player1CheckBox.isSelected()) {
                    System.out.println("Playing 1 is now controlled by A.I");
                } else if (player2CheckBox.isSelected()) {
                    System.out.println("Playing 2 is now controlled by A.I");
                } else {
                    System.out.println("Neither Player is controlled by A.I");
                }
            }
        });
        player2CheckBox.addActionListener(playerAI);
        comboBoxAddRemove.addActionListener(e -> {
            if(comboBoxAddRemove.getSelectedItem() == "Remove") {
                comboBoxColor.setEnabled(false);
            } else {
                comboBoxColor.setEnabled(true);
            }
        });
        startEndGameButton.addActionListener(new ActionListener() {
            boolean clicked = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked = !clicked;
                if(!clicked){
                    startEndGameButton.setText("Start Game");
                } else {
                    startEndGameButton.setText("End Game");
                }
            }
        });
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createRightPanel() {
        JPanel topControlPanel = createTopControlPanel();
        JPanel bottomControlPanel = createBottomControlPanel();

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        rightPanel.add(topControlPanel, BorderLayout.NORTH);
        rightPanel.add(bottomControlPanel, BorderLayout.SOUTH);

        add(rightPanel,BorderLayout.EAST);
    }


    private JPanel createTopControlPanel() {
        JPanel playerGroupPanel = createCheckBoxes();
        JPanel firstPlayerPanel = createFirstPlayerRadios();

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2,1));
        controlPanel.add(playerGroupPanel);
        controlPanel.add(firstPlayerPanel);

        return controlPanel;
    }

    private JPanel createFirstPlayerRadios() {
        blackRadioButton = new JRadioButton("Black");

        whiteRadioButton = new JRadioButton("White");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(blackRadioButton);
        panel.add(whiteRadioButton);
        panel.setBorder(new TitledBorder(new EtchedBorder(), "First Player?"));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(blackRadioButton);
        buttonGroup.add(whiteRadioButton);

        return panel;
    }

    private JPanel createBottomControlPanel() {
        JPanel advancedSettings = createAdvancedSettings();
        startEndGameButton = new JButton("Start Game");

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        controlPanel.add(advancedSettings, c);
        c.ipady = 40;
        c.gridy = 1;
        controlPanel.add(startEndGameButton, c);

        return controlPanel;
    }

    private JPanel createCheckBoxes() {
        player1CheckBox = new JCheckBox("Player 1");

        player2CheckBox = new JCheckBox("Player 2");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(player1CheckBox);
        panel.add(player2CheckBox);
        panel.setBorder(new TitledBorder(new EtchedBorder(), "A.I?"));

        return panel;
    }

    private JPanel createAdvancedSettings() {
        clearBoardButton = new JButton("Clear Board");
        quitButton = new JButton("Close Game");
        printBoardButton = new JButton("Print Board");
        drawBoardButton = new JButton("Draw Board");
        goClassicModeButton = new JButton("Console Mode");
        randomButton = new JButton("???");
        goBeadButton = new JButton("Go!");
        comboBoxColor = new JComboBox();
            comboBoxColor.addItem("White");
            comboBoxColor.addItem("Black");
        comboBoxRow = new JComboBox();
            comboBoxRow.addItem("1");
            comboBoxRow.addItem("2");
            comboBoxRow.addItem("3");
            comboBoxRow.addItem("4");
        comboBoxColumn = new JComboBox();
            comboBoxColumn.addItem("A");
            comboBoxColumn.addItem("B");
            comboBoxColumn.addItem("C");
            comboBoxColumn.addItem("D");
        comboBoxAddRemove = new JComboBox();
            comboBoxAddRemove.addItem("Add");
            comboBoxAddRemove.addItem("Remove");


        commandTextField = new JTextField();
        goTextButton = new JButton("Go!");
        JPanel textPanel = new JPanel();
        textPanel.setBorder(new TitledBorder(BorderFactory.createEmptyBorder(), "Use Commands:"));
        textPanel.setLayout(new GridBagLayout());
        GridBagConstraints t = new GridBagConstraints();
        t.weightx = 5;
        t.gridx = 0;
        t.ipadx = 270;
        textPanel.add(commandTextField, t);
        t.gridx = 3;
        t.ipadx = 0;
        textPanel.add(goTextButton, t);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2,3,4,4));
        buttonsPanel.add(clearBoardButton);
        buttonsPanel.add(drawBoardButton);
        buttonsPanel.add(printBoardButton);
        buttonsPanel.add(randomButton);
        buttonsPanel.add(goClassicModeButton);
        buttonsPanel.add(quitButton);

        JPanel addBeadPanel = new JPanel();
        addBeadPanel.setBorder(new TitledBorder(BorderFactory.createEmptyBorder(), "Add/Remove Bead in Specific Location:"));
        addBeadPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.5;
        c.gridx = 0;
        addBeadPanel.add(comboBoxAddRemove, c);
        c.gridx = 1;
        addBeadPanel.add(comboBoxColumn, c);
        c.gridx = 2;
        addBeadPanel.add(comboBoxRow, c);
        c.gridx = 3;
        addBeadPanel.add(comboBoxColor, c);
        c.gridx = 4;
        addBeadPanel.add(goBeadButton, c);


        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Advanced Settings"));
        panel.setLayout(new GridLayout(3,0,5,2));
        panel.add(buttonsPanel);
        panel.add(addBeadPanel);
        panel.add(textPanel);


        return panel;
    }
}