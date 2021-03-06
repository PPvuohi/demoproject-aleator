package aleator;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

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
        ImageIcon img = new ImageIcon("resources/ic_launcher.png");
        this.setIconImage(img.getImage());
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
            ResultContainer rc = this.parser.parse(input);
            float result = rc.result;
            if ((int)result == result) {    //don't display trailing zeroes 
                output = String.format("%d",(int)result);
            } else {
                output = String.format("%s",result);
            }
            String dice = rc.diceToString();
            if (dice.length() > 0) {
                output += " (rolled: " + dice + ")";
            }
            this.output.addLine(input + " → " + output);
        }
    }
}
