package aleator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class InputPanel extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JTextField input_field  = new JTextField(" ",20);

    InputPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //input field
        JPanel thisline = new JPanel();
        thisline.add(input_field);
        JButton input_clear = new JButton("C");
        input_clear.addActionListener(this);
        input_clear.setActionCommand("CLEAR");
        thisline.add(input_clear);
        this.add(thisline);

        //die shortcuts
        JPanel panel_die_shortcuts = new JPanel();
        JButton btn_d4 = new JButton("d4");
        JButton btn_d6 = new JButton("d6");
        JButton btn_d8 = new JButton("d8");
        JButton btn_d10 = new JButton("d10");
        JButton btn_d12 = new JButton("d12");
        JButton btn_d20 = new JButton("d20");
        JButton btn_d100 = new JButton("d100");
        panel_die_shortcuts.add(btn_d4);
        panel_die_shortcuts.add(btn_d6);
        panel_die_shortcuts.add(btn_d8);
        panel_die_shortcuts.add(btn_d10);
        panel_die_shortcuts.add(btn_d12);
        panel_die_shortcuts.add(btn_d20);
        panel_die_shortcuts.add(btn_d100);
        this.add(panel_die_shortcuts);

        //keys

        JPanel panel_keys = new JPanel();

        JPanel panel_numpad = new JPanel();
        panel_numpad.setLayout(new GridBagLayout());
        GridBagConstraints numpad_c = new GridBagConstraints();

        ArrayList<JButton> numpad_keys = new ArrayList<JButton>();
        JButton btn = new JButton("0");
        numpad_keys.add(btn);
        btn.addActionListener(this);
        btn.setActionCommand(btn.getText());
    numpad_c.gridx = 0;
        numpad_c.gridy = 3;
        panel_numpad.add(numpad_keys.get(0), numpad_c);
        for (int i=0; i<=8; i++) {
            btn = new JButton(""+(i+1));
            btn.addActionListener(this);
            btn.setActionCommand(btn.getText());
            numpad_keys.add(btn);
            numpad_c.gridx = i%3;
            numpad_c.gridy = i/3;
            panel_numpad.add(btn, numpad_c);
        }
        JButton key_d = new JButton("d");
        key_d.addActionListener(this);
        key_d.setActionCommand("DIE");
        numpad_c.fill = GridBagConstraints.HORIZONTAL;
        numpad_c.gridwidth = 2;
        numpad_c.gridx = 1;
        numpad_c.gridy = 3;
        panel_numpad.add(key_d, numpad_c);
        panel_keys.add(panel_numpad);

        JPanel panel_operators = new JPanel();
        panel_operators.setLayout(new GridBagLayout());
        GridBagConstraints operators_c = new GridBagConstraints();
        JButton key_plus = new JButton("+");
        operators_c.gridx = 0;
        operators_c.gridy = 0;
        panel_operators.add(key_plus, operators_c);
        JButton key_minus = new JButton("-");
        operators_c.gridx = 1;
        operators_c.gridy = 0;
        panel_operators.add(key_minus, operators_c);
        JButton key_mult = new JButton("*");
        operators_c.gridx = 0;
        operators_c.gridy = 1;
        panel_operators.add(key_mult, operators_c);
        JButton key_div = new JButton("÷");
        operators_c.gridx = 1;
        operators_c.gridy = 1;
        panel_operators.add(key_div, operators_c);
        JButton key_paren_open = new JButton("(");
        operators_c.gridx = 0;
        operators_c.gridy = 2;
        panel_operators.add(key_paren_open, operators_c);
        JButton key_paren_close = new JButton(")");
        operators_c.gridx = 1;
        operators_c.gridy = 2;
        panel_operators.add(key_paren_close, operators_c);
        JButton key_backspace = new JButton("←");
        key_backspace.addActionListener(this);
        key_backspace.setActionCommand("BACKSPACE");
        operators_c.gridx = 0;
        operators_c.gridy = 3;
        operators_c.fill = GridBagConstraints.HORIZONTAL;
        operators_c.gridwidth  = 2;
        operators_c.gridheight = 2;
        panel_operators.add(key_backspace, operators_c);
        panel_keys.add(panel_operators);

        JButton key_enter = new JButton("enter");
        panel_keys.add(key_enter);

        this.add(panel_keys);

    }
    public void actionPerformed(final ActionEvent e) {
        String c = e.getActionCommand();
        String str = this.input_field.getText();
        System.out.println("Command entered: "+c); //test code, remove from final version
        switch(c) {
            case "BACKSPACE":
                this.input_field.setText(str.substring(0, str.length() - 1));
                break;
            case "CLEAR":
                this.input_field.setText("");
                break;
            case "DIE":
                if(!Character.isDigit(str.charAt(str.length()-1))) {
                    str += '1';
                }
                str += 'd';
                this.input_field.setText(str);
                break;
            default:
                this.input_field.setText(this.input_field.getText() + c);
        }
    }
}