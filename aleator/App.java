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
    AleatorParser parser;
    
    App() {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        this.output = new OutputPanel(this);
        this.input = new InputPanel(this);
        this.parser = new AleatorParser();
        this.setSize(500,600);
        this.add(BorderLayout.NORTH, output);
        this.add(BorderLayout.SOUTH, input);
        
        this.setVisible(true);
    }

    protected void enterInput() {
        String output;
        String str = this.input.getInput();
        float result = this.parser.parse(str);
        if ((int)result == result) {    //don't display trailing zeroes 
            output = String.format("%d",(int)result);
        } else {
            output = String.format("%s",result);
        }
        this.output.addLine(output);
    }
}
