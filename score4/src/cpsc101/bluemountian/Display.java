package cpsc101.bluemountian;

import javax.swing.*;
import java.awt.*;

public class Display {
    public static void main(String[] args) {
        JFrame frame = new GameFrame();

        frame.setPreferredSize(new Dimension(900, 600));
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setTitle("Connect 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
