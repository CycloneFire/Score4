package cpsc101.bluemountian.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Provides a U.I to gather player information
 *
 * @author Sebastian
 */
public class PlayerInfoComponent extends JComponent {

        private JTextField playerNameField;
        private JLabel playerNameLabel;
        private JLabel playerColorLabel;
        private JButton back;
        private JButton next;
        private ColorOptions colorOptions;
        private static final int FRAME_WIDTH = 600;
        private static final int FRAME_HEIGHT = 250;
        private String titleStr;

    /**
     * Constructs a U.I for player information
     *
     * @param titleStr Title of the frame
     */
    public PlayerInfoComponent(String titleStr){
        this.titleStr = titleStr;
        this.setLayout(new BorderLayout());
        JLabel title = createTitle();
        JPanel playerInfo = createPlayerInfo();

        add(title, BorderLayout.NORTH);
        add(playerInfo, BorderLayout.CENTER);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        //Translate Listeners to Controller
        colorOptions.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                for(int i = 0; i < ColorOptions.getColors().size(); i++){
                    if(x > i*(colorOptions.getRectWidth()+10) && x < (i+1)*(colorOptions.getRectWidth()+10)) {
                        colorOptions.setSelected(i);
                    }
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                colorOptions.setHover(10);
            }
        });
        colorOptions.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                for(int i = 0; i < ColorOptions.getColors().size(); i++){
                    if(x > i*(colorOptions.getRectWidth()+10) && x < (i+1)*(colorOptions.getRectWidth()+10)) {
                        colorOptions.setHover(i);
                    }
                }
            }
        });
    }


    /**
     *
     * @return is player name not empty
     */
    public Boolean validateInput(){
        return !playerNameField.getText().isEmpty();
    }

    /**
     *
     * @return color selected from list
     */
    public Color getColor(){
        return colorOptions.getColor();
    }

    /**
     *
     * @return name of Player
     */
    public String getName(){
        return playerNameField.getText();
    }

    private JLabel createTitle() {
        JLabel label = new JLabel(titleStr);
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 36));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        return label;
    }

    private JPanel createPlayerInfo() {
        playerNameField = new JTextField();
        playerNameLabel = new JLabel("Name:");
        playerColorLabel = new JLabel("Color:");
        colorOptions = new ColorOptions();
        back = new JButton("Back");
        next = new JButton("Next");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10,10,0,10);
        c.weighty = 1.0;
        c.ipady = 20;
        c.ipadx = 20;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(playerNameLabel, c);
        c.gridy = 1;
        panel.add(playerColorLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(playerNameField, c);
        c.gridy = 1;
        c.ipady = 50;           // Changed this to match color swatch size
        panel.add(colorOptions, c);

        c.fill = GridBagConstraints.HORIZONTAL;

        c.ipady = 10;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0,10);
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(back, c);
        c.gridx = 1;
        panel.add(next, c);

        return panel;
    }

    /**
     * Add listener to Next button
     * @param listener listener to add
     */
    public void addNextListener(ActionListener listener){
        next.addActionListener(listener);
    }

    /**
     * Add listener to Back button
     * @param listener listener to add
     */
    public void addBackListener(ActionListener listener){
        back.addActionListener(listener);
    }

    /**
     * Add mouse motion listener
     * @param listener listener to add
     */
    public void addColorOptionsMouseListener(MouseListener listener){
        colorOptions.addMouseListener(listener);
    }

    /**
     * Add mouse click listener
     * @param listener listener to add
     */
    public void addColorOptionsMouseMotionListener(MouseMotionListener listener){
        colorOptions.addMouseMotionListener(listener);
    }


    }