package cpsc101.bluemountian.view;

import cpsc101.bluemountian.view.components.FirstPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ComponentManager extends JFrame {
    private int currentState;
    private ArrayList<JComponent> components;

    public ComponentManager(){
        this.setPreferredSize(new Dimension(900, 600));
        this.setMinimumSize(new Dimension(900, 600));
        this.setTitle("Score 4");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // First add all state based components in arrayList in order.
        components.add(new FirstPanel());
        // Add first components.
        this.add(components.get(currentState));
    }

    @Override
    public Component getComponent(int n) {
        return components.get(n);
    }

    public int getCurrentState() {
        return currentState;
    }

    public void advanceState(){
        this.remove(components.get(currentState));
        currentState++;
        this.add(components.get(currentState));
    }
}
