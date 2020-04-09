package aleator;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    OutputPanel output;
    InputPanel input;
    AleatorParser parser;
    String prev_input = "";
    
    App() {
        this.setTitle("Aleator");
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
        String input = this.input.getInput();
        if (input.length() > 0) {
            prev_input = input;
        } else {
            input = prev_input;
        }
        if (input.length() > 0) {
            float result = this.parser.parse(input);
            if ((int)result == result) {    //don't display trailing zeroes 
                output = String.format("%d",(int)result);
            } else {
                output = String.format("%s",result);
            }
            this.output.addLine(input + " → " + output);
        }
    }
}
