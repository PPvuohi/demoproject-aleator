package aleator;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 0L;
    OutputPanel output;
    InputPanel input;
    
    App() {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        this.output = new OutputPanel(this);
        this.input = new InputPanel(this);
        this.setSize(500,600);
        this.add(BorderLayout.NORTH, output);
        this.add(BorderLayout.SOUTH, input);
        
        this.setVisible(true);
    }

    protected void enterInput() {
        //TODO actual functionality
        String str = this.input.getInput();
        this.output.addLine(str);
    }
}
