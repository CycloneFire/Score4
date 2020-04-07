package cpsc101.bluemountian.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Provides an interface to select whose turn it is.
 *
 * @author Sebastian
 */
public class TurnComponent extends JComponent {
    private JButton player1First;
    private JButton player2First;
    private JButton randomButton;
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 250;

    /**
     * Constructs a turn component for gien players
     * @param player1Name Name of first player
     * @param player2Name Name of second player
     */
    public TurnComponent(String player1Name, String player2Name) {
        this.setLayout(new BorderLayout(10,70));
        JLabel title = createTitle();
        JPanel options = createOptions(player1Name,player2Name);

        add(title, BorderLayout.NORTH);
        add(options, BorderLayout.CENTER);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private JPanel createOptions(String player1Name, String player2Name) {
        JPanel panel = new JPanel(new GridLayout(1,3,10,10));
        player1First = new JButton(player1Name + " goes first.");
        player2First = new JButton(player2Name + " goes first.");
        randomButton = new JButton("Random.");
        panel.add(player1First);
        panel.add(player2First);
        panel.add(randomButton);

        return panel;
    }

    private JLabel createTitle() {
        JLabel label = new JLabel("Who will go first?");
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 28));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        return label;
    }

    /**
     * Add listener to First player button
     * @param listener listener to add
     */
    public void addPlayer1FirstListener(ActionListener listener) {
        player1First.addActionListener(listener);
    }

    /**
     * Add listener to Second player button
     * @param listener listener to add
     */
    public void addPlayer2FirstListener(ActionListener listener) {
        player2First.addActionListener(listener);
    }

    /**
     * Add listener to Random player button
     * @param listener listener to add
     */
    public void addRandomButtonListener(ActionListener listener) {
        randomButton.addActionListener(listener);
    }
}

// Listeners