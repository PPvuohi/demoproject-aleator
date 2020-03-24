package aleator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class InputPanel extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 3L;

    JTextField input_field  = new JTextField("",20);
    App root;

    InputPanel(App root) {
        this.root = root;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //input field
        final JPanel thisline = new JPanel();
        thisline.add(input_field);
        final JButton input_clear = new JButton("C");
        input_clear.addActionListener(this);
        input_clear.setActionCommand("CLEAR");
        thisline.add(input_clear);
        this.add(thisline);

        // die shortcuts
        final JPanel panel_die_shortcuts = new JPanel();
        final JButton btn_d4 = new JButton("d4");
        final JButton btn_d6 = new JButton("d6");
        final JButton btn_d8 = new JButton("d8");
        final JButton btn_d10 = new JButton("d10");
        final JButton btn_d12 = new JButton("d12");
        final JButton btn_d20 = new JButton("d20");
        final JButton btn_d100 = new JButton("d100");
        panel_die_shortcuts.add(btn_d4);
        panel_die_shortcuts.add(btn_d6);
        panel_die_shortcuts.add(btn_d8);
        panel_die_shortcuts.add(btn_d10);
        panel_die_shortcuts.add(btn_d12);
        panel_die_shortcuts.add(btn_d20);
        panel_die_shortcuts.add(btn_d100);
        for (final Component c : panel_die_shortcuts.getComponents()) {
            if (c instanceof JButton) {
                ((JButton) c).addActionListener(this);
                ((JButton) c).setActionCommand(((JButton) c).getText());
            }
        }
        this.add(panel_die_shortcuts);

        // keys

        final JPanel panel_keys = new JPanel();

        final JPanel panel_numpad = new JPanel();
        panel_numpad.setLayout(new GridBagLayout());
        final GridBagConstraints numpad_c = new GridBagConstraints();

        final ArrayList<JButton> numpad_keys = new ArrayList<JButton>(); // storing these in case we need an easy way to
                                                                         // refer to them later
        JButton btn = new JButton("0"); // 0 added separately so it goes where we want it
        numpad_keys.add(btn);
        numpad_c.gridx = 0;
        numpad_c.gridy = 3;
        panel_numpad.add(numpad_keys.get(0), numpad_c);
        // number keys 1 to 9
        for (int i = 0; i <= 8; i++) {
            btn = new JButton("" + (i + 1));
            numpad_keys.add(btn);
            numpad_c.gridx = i % 3;
            numpad_c.gridy = i / 3;
            panel_numpad.add(btn, numpad_c);
        }
        final JButton key_d = new JButton("d"); // die key
        numpad_c.fill = GridBagConstraints.HORIZONTAL;
        numpad_c.gridwidth = 2;
        numpad_c.gridx = 1;
        numpad_c.gridy = 3;
        panel_numpad.add(key_d, numpad_c);
        for (final Component c : panel_numpad.getComponents()) {
            if (c instanceof JButton) {
                ((JButton) c).addActionListener(this);
                ((JButton) c).setActionCommand(((JButton) c).getText());
            }
        }
        panel_keys.add(panel_numpad);

        final JPanel panel_operators = new JPanel();
        panel_operators.setLayout(new GridBagLayout());
        final GridBagConstraints operators_c = new GridBagConstraints();
        final JButton key_plus = new JButton("+");
        operators_c.gridx = 0;
        operators_c.gridy = 0;
        panel_operators.add(key_plus, operators_c);
        final JButton key_minus = new JButton("-");
        operators_c.gridx = 1;
        operators_c.gridy = 0;
        panel_operators.add(key_minus, operators_c);
        final JButton key_mult = new JButton("*");
        operators_c.gridx = 0;
        operators_c.gridy = 1;
        panel_operators.add(key_mult, operators_c);
        final JButton key_div = new JButton("÷");
        operators_c.gridx = 1;
        operators_c.gridy = 1;
        panel_operators.add(key_div, operators_c);
        final JButton key_paren_open = new JButton("(");
        operators_c.gridx = 0;
        operators_c.gridy = 2;
        panel_operators.add(key_paren_open, operators_c);
        final JButton key_paren_close = new JButton(")");
        operators_c.gridx = 1;
        operators_c.gridy = 2;
        panel_operators.add(key_paren_close, operators_c);
        final JButton key_backspace = new JButton("←");
        operators_c.gridx = 0;
        operators_c.gridy = 3;
        operators_c.fill = GridBagConstraints.HORIZONTAL;
        operators_c.gridwidth = 2;
        operators_c.gridheight = 2;
        panel_operators.add(key_backspace, operators_c);
        for (final Component c : panel_operators.getComponents()) {
            if (c instanceof JButton) {
                ((JButton) c).addActionListener(this);
                ((JButton) c).setActionCommand(((JButton) c).getText());
            }
        }
        panel_keys.add(panel_operators);

        final JButton key_enter = new JButton("enter");
        key_enter.addActionListener(this);
        key_enter.setActionCommand("ENTER");
        panel_keys.add(key_enter);

        this.add(panel_keys);

    }

    private boolean prev_is_digit() {
        final String str = this.input_field.getText();
        if (str.length() < 1) {
            return false;
        }
        return Character.isDigit(str.charAt(str.length() - 1));
    }

    protected String getInput() {
        return this.input_field.getText();
    }

    public void actionPerformed(final ActionEvent e) {
        // FIXME: prevent chained die entries e.g. "1d1d"
        final String c = e.getActionCommand();
        String str = this.input_field.getText();
        System.out.println("Command entered: "+c); //test code, remove from final version
        if (c == "ENTER") {
            this.root.enterInput();
            this.input_field.setText("");
        } else if (c.length() > 1) {     //die quickbuttons
            if (c.charAt(0) == 'd') {
                if (!this.prev_is_digit()) {
                    str += '1';
                }
                str += c;
                this.input_field.setText(str);
            }
        } else {
            switch(c) {
                case "←":
                    if (str.length() > 0) {
                        this.input_field.setText(str.substring(0, str.length() - 1));
                    }
                    break;
                case "CLEAR":
                    this.input_field.setText("");
                    break;
                case "d":
                    if (!this.prev_is_digit()) {
                        str += '1';
                    }
                    str += 'd';
                    this.input_field.setText(str);
                    break;
                default:
                    this.input_field.setText(str + c);
            }
        }
    }
}