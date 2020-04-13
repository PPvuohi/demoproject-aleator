package aleator;

import javax.swing.*;
import java.awt.event.*;

public class OutputPanel extends JPanel implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 0L;

    JTextArea output_field = new JTextArea("", 10, 20);
    App root;

    OutputPanel(App root) {
        this.root = root;
        output_field.setEditable(false);
        output_field.setFont(output_field.getFont().deriveFont(24f));
        output_field.setLineWrap(true);
        output_field.setWrapStyleWord(true);
        this.add(output_field);

        JPanel output_nav = new JPanel();
        output_nav.setLayout(new BoxLayout(output_nav, BoxLayout.Y_AXIS));
        JButton btn_scrollup = new JButton("⇧");
        output_nav.add(btn_scrollup);
        JButton btn_scrolldown = new JButton("⇩");
        output_nav.add(btn_scrolldown);
        JButton btn_clear = new JButton("C");
        btn_clear.addActionListener(this);
        btn_clear.setActionCommand("CLEAR");
         output_nav.add(btn_clear);
        this.add(output_nav);
    }
    protected void addLine(String str) {
        String contents = this.output_field.getText();
        if (contents.length() > 0) {
            contents += System.lineSeparator();
        }
        contents += str;
        this.output_field.setText(contents);
    }

    public void actionPerformed(final ActionEvent e) {
        final String c = e.getActionCommand();
        switch(c) {
            case "CLEAR":
                this.output_field.setText("");
                break;
            default:
                break;
        }
    }
}