package cpsc101.bluemountian.view;

import cpsc101.bluemountian.view.components.IntroComponent;
import cpsc101.bluemountian.view.components.PlayerInfoComponent;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Provides an easy way to advance/retreat current screen based on ScreenPlay (decided beforehand)
 *
 * @author Suyash
 */
public class ComponentManager extends JFrame {
    private int currentState=0;
    private ArrayList<JComponent> components = new ArrayList<JComponent>();

    /**
     * Constructs a component manager
     */
    public ComponentManager(){
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("score4.png")));
        setIconImage(icon.getImage());
        setMinimumSize(new Dimension(600,250));
        setTitle("Score 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // First add all state based components in arrayList in order.
        components.add(new IntroComponent());
        components.add(new PlayerInfoComponent("Player 1"));
        components.add(new PlayerInfoComponent("Player 2"));
        //components.add(new BoardComponent());

        // Add first component to frame in constructor.
        pack();
        setLocationRelativeTo(null);
        add(components.get(currentState));


    }

    /**
     * Re-initialises all screens and resets them, retreat to first screen
     */
    public void reInit(){
        remove(components.get(currentState));
        components.removeAll(components);
        components.add(new IntroComponent());
        components.add(new PlayerInfoComponent("Player 1"));
        components.add(new PlayerInfoComponent("Player 2"));
        currentState=0;
        add(components.get(currentState));
        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     *
     * @param n index of component
     * @return component at passed index
     */
    @Override
    public JComponent getComponent(int n) {
        return components.get(n);
    }

    /**
     * Adds passed component to screenplay list
     * @param component component to add
     */
    public void addComponent(JComponent component){components.add(component);}

    /**
     *
     * @return Number of current screen in screenplay
     */
    public int getCurrentState() {
        return currentState;
    }

    /**
     * Advance/retreat state by redrawing and restructuring the frame to given index in screenplay.
     * @param n index to advance/retreat by
     */
    public void advanceState(int n){
        //Removal and advance
        this.remove(components.get(currentState));
        currentState+=n;
        //Addition
        this.add(components.get(currentState));
        //Refreshing
        this.revalidate();  // Revalidate Layout structure
        this.repaint(); // Repaint Frame
        this.pack();    // Resize frame based on preferred sizes of individual component
        this.setLocationRelativeTo(null);   // Re-center frame

    }
}
