package cpsc101.bluemountian.view;

import cpsc101.bluemountian.view.components.BoardComponent;
import cpsc101.bluemountian.view.components.IntroComponent;
import cpsc101.bluemountian.view.components.PlayerInfoComponent;
import cpsc101.bluemountian.view.components.TurnComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ComponentManager extends JFrame {
    private int currentState=0;
    private ArrayList<JComponent> components = new ArrayList<JComponent>();

    public ComponentManager(){
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

    @Override
    public JComponent getComponent(int n) {
        return components.get(n);
    }

    public void addComponent(JComponent component){components.add(component);}

    public int getCurrentState() {
        return currentState;
    }

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
