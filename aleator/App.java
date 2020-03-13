package aleator;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -7060012667115081197L;
    
    App() {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        JPanel output = new OutputPanel();
        JPanel input = new InputPanel();
        this.setSize(500,600);
        this.add(BorderLayout.NORTH, output);
        this.add(BorderLayout.SOUTH, input);
        
        this.setVisible(true);
    }
}
