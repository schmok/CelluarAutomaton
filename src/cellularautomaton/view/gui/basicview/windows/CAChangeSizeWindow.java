package cellularautomaton.view.gui.basicview.windows;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.components.CAJButton;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vspadi on 09.11.15.
 */
public class CAChangeSizeWindow extends JFrame implements IOwnEnumeration{
    private JTextField widthField;
    private JTextField heightField;
    private CAJButton submitButton;
    private JLabel widthLabel;
    private JLabel heightLabel;

    public JTextField getWidthField() {
        return widthField;
    }

    public JTextField getHeightField() {
        return heightField;
    }

    public CAJButton getSubmitButton() {
        return submitButton;
    }

    public CAChangeSizeWindow() {
        super(StringController.getInstance().get(StringEnumeration.W_CHANGE_SIZE_WINDOW));

        this.submitButton = new CAJButton(StringEnumeration.W_CHANGED_SIZE);
        this.widthField = new JTextField();
        this.heightField = new JTextField();
        this.widthLabel = new JLabel("Breite:");
        this.heightLabel = new JLabel("Höhe:");

        this.setLayout(new GridLayout(0,1));
        this.widthField.setPreferredSize(new Dimension(100, 25));
        this.heightField.setPreferredSize(new Dimension(100, 25));

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(0,2));
        p1.add(widthLabel);
        p1.add(widthField);
        this.add(p1);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(0,2));
        p2.add(heightLabel);
        p2.add(heightField);
        this.add(p2);

        this.submitButton.setPreferredSize(new Dimension(80, 25));

        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(0,2));

        p3.add(this.submitButton, 1,0);
        this.add(p3);

        this.pack();
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.W_CHANGE_SIZE_WINDOW;
    }

    public void reset() {
        this.heightField.setText("");
        this.widthField.setText("");
    }
}
