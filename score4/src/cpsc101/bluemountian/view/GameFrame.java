package cpsc101.bluemountian.view;

import cpsc101.bluemountian.model.board.Board;
import cpsc101.bluemountian.view.components.BoardComponent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/*
 *   Remove all printlns and put them into the corresponding methods in corresponding classes
 */
public class GameFrame extends JFrame {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 100;
    private Board board;


    private JButton clearBoardButton;
    private JButton printBoardButton;
    private JButton quitButton;
    private JCheckBox player1CheckBox;
    private JCheckBox player2CheckBox;
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
    private JButton goConsoleModeButton;
    private JButton drawBoardButton;
    private JButton randomButton;
    private JLabel mainTitle;
    private BoardComponent boardComponent;

    private Color turnColor = Color.white;

    public GameFrame(Board board) {
        this.board = board;
        Peg[][] thePegs = new Peg[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                thePegs[i][j] = new Peg(i, j);
            }
        }


        createBoardPanel();
        createRightPanel();


        clearBoardButton.addActionListener(e -> System.out.println("Board cleared."));
        printBoardButton.addActionListener(e -> System.out.println("Printing board..."));
        quitButton.addActionListener(e -> {
            System.out.println("Quitting...");
            System.exit(0);
        });
        //Missing method
        blackRadioButton.addActionListener(e -> System.out.println("Black goes first."));
        //Missing method
        whiteRadioButton.addActionListener(e -> System.out.println("White goes first."));
        ActionListener playerAI;
        player1CheckBox.addActionListener(playerAI = e -> {
            if (player1CheckBox.isSelected() && player2CheckBox.isSelected()) {
                //Missing method
                System.out.println("Both players are now controlled by A.I");
            } else if (player1CheckBox.isSelected()) {
                //Missing method
                System.out.println("Playing 1 is now controlled by A.I");
            } else if (player2CheckBox.isSelected()) {
                //Missing method
                System.out.println("Playing 2 is now controlled by A.I");
            } else {
                System.out.println("Neither Player is controlled by A.I");
            }
        });
        player2CheckBox.addActionListener(playerAI);
        comboBoxAddRemove.addActionListener(e -> {
            if (comboBoxAddRemove.getSelectedItem() == "Remove") {
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
                if (!clicked) {
                    startEndGameButton.setText("Start Game");
                    //Missing method
                    System.out.println("Game ended.");
                } else {
                    startEndGameButton.setText("End Game");
                    //Missing method
                    System.out.println("Game started.");
                }
            }
        });
        goBeadButton.addActionListener(e -> {
            if (comboBoxAddRemove.getSelectedItem().equals("Add")) {
//                    Missing method
//                    controller.addBead(comboBoxColumn,comboBoxRow, comboBoxColor);
                System.out.println("Adding a " + comboBoxColor.getSelectedItem().toString().toLowerCase() + " bead in location " + comboBoxColumn.getSelectedItem() + comboBoxRow.getSelectedItem() + "...");
            } else if (comboBoxAddRemove.getSelectedItem().equals("Remove")) {
//                    Missing method
//                    controller.removeRemove(comboBoxAddRemove,comboBoxColumn,comboBoxRow, comboBoxColor);
                System.out.println("Removing bead in location " + comboBoxColumn.getSelectedItem() + comboBoxRow.getSelectedItem() + "...");
            }
        });
//        Missing method. controller.command(commandTextField.getText())?
        goTextButton.addActionListener(e -> System.out.println("Doing command."));
        //Missing method. controller.goConsoleMode()?
        goConsoleModeButton.addActionListener(e -> System.out.println("Switching to console mode."));
        //Missing method.
        drawBoardButton.addActionListener(e -> System.out.println("Drawing board."));
        //???
        randomButton.addActionListener(e -> System.out.println("rAnDoMm"));
        boardComponent.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {

                int x = boardComponent.getSize().width;
                int y = boardComponent.getSize().height;
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
                            boardComponent.setHoverLocation(i, j, thePegs[i][j].getMaxBead());
                            break outerloop;
                        } else {
                            boardComponent.setHoverLocation(10, 10, 10);
                        }
                    }
                }
            }
        });
        boardComponent.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = boardComponent.getSize().width;
                int y = boardComponent.getSize().height;
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
                            thePegs[i][j].addBead(turnColor, boardComponent);
                            switchColors();
                            boardComponent.setTurnColor(turnColor);
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void switchColors() {
        if (turnColor.equals(Color.WHITE)) {
            turnColor = Color.black;
        } else {
            turnColor = Color.white;
        }
    }

    private void createBoardPanel() {
        boardComponent = new BoardComponent(board);
        add(boardComponent, BorderLayout.CENTER);
    }

    private void createRightPanel() {
        JPanel topControlPanel = createTopControlPanel();
        JPanel bottomControlPanel = createBottomControlPanel();

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        rightPanel.add(topControlPanel, BorderLayout.NORTH);
        rightPanel.add(bottomControlPanel, BorderLayout.SOUTH);

        add(rightPanel, BorderLayout.EAST);
    }


    private JPanel createTopControlPanel() {
        JPanel playerGroupPanel = createCheckBoxes();
        JPanel firstPlayerPanel = createFirstPlayerRadios();
        mainTitle = new JLabel("Score 4!");
        mainTitle.setHorizontalAlignment(JLabel.CENTER);
        mainTitle.setVerticalAlignment(JLabel.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 1));
        controlPanel.add(mainTitle);
        controlPanel.add(playerGroupPanel);
        controlPanel.add(firstPlayerPanel);

        return controlPanel;
    }

    private JPanel createFirstPlayerRadios() {
        blackRadioButton = new JRadioButton("Black");
        whiteRadioButton = new JRadioButton("White");

        whiteRadioButton.setSelected(true);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(whiteRadioButton);
        panel.add(blackRadioButton);
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
        c.ipady = 20;
        c.gridy = 1;
        controlPanel.add(startEndGameButton, c);

        return controlPanel;
    }

    private JPanel createCheckBoxes() {
        player1CheckBox = new JCheckBox("Player 1");

        player2CheckBox = new JCheckBox("Player 2");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
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
        goConsoleModeButton = new JButton("Console Mode");
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
        buttonsPanel.setLayout(new GridLayout(2, 3, 4, 4));
        buttonsPanel.add(clearBoardButton);
        buttonsPanel.add(drawBoardButton);
        buttonsPanel.add(printBoardButton);
        buttonsPanel.add(randomButton);
        buttonsPanel.add(goConsoleModeButton);
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
        panel.setLayout(new GridLayout(3, 0, 5, 2));
        panel.add(buttonsPanel);
        panel.add(addBeadPanel);
        panel.add(textPanel);


        return panel;
    }

    public BoardComponent getBoardComponent() {
        return boardComponent;
    }
}