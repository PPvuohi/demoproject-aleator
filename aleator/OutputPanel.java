package aleator;

import javax.swing.*;

public class OutputPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 0L;

    JTextArea output_field = new JTextArea("", 20, 20);
    App root;

    OutputPanel(App root) {
        this.root = root;
        output_field.setEditable(false);
        this.add(output_field);

        JPanel output_nav = new JPanel();
        output_nav.setLayout(new BoxLayout(output_nav, BoxLayout.Y_AXIS));
        JButton btn_scrollup = new JButton("⇧");
        output_nav.add(btn_scrollup);
        JButton btn_scrolldown = new JButton("⇩");
        output_nav.add(btn_scrolldown);
        JButton btn_clear = new JButton("C");
        output_nav.add(btn_clear);
        this.add(output_nav);
    }
    protected void addLine(String str) {
        String current = this.output_field.getText();
        this.output_field.setText(current + System.lineSeparator() + str);
    }
}