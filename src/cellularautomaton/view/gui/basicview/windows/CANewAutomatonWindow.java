package cellularautomaton.view.gui.basicview.windows;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.components.CAJButton;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vspadi on 14.12.15.
 */
public class CANewAutomatonWindow extends JFrame implements IOwnEnumeration {

    private JTextField nameField;
    private CAJButton submitButton;
    private JFrame parentFrame;

    public JTextField getNameField() {
        return nameField;
    }

    public CAJButton getSubmitButton() {
        return submitButton;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public CANewAutomatonWindow() {
        super(StringController.getInstance().get(StringEnumeration.W_NEW_AUTOMATON_WINDOW));

        this.nameField = new JTextField();
        this.submitButton = new CAJButton(StringEnumeration.W_NEW_AUTOMATON);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(0,2));
        p1.add(nameField);
        p1.add(submitButton);
        this.add(p1);

        this.pack();
    }

    public void setParentFrame(JFrame component) {
        this.parentFrame = component;
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.W_NEW_AUTOMATON_WINDOW;
    }

    public void reset() {
        this.nameField.setText("");
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if(b) {
            Container parent = this.parentFrame;
            if(parent != null) {
                Point p = new Point(parent.getWidth() /2 + parent.getX()-this.getWidth() / 2,
                        parent.getHeight() / 2 + parent.getY()-this.getHeight() / 2);
                this.setLocation(p);
            } else {
                this.setLocation(100,100);
            }

        }
    }
}
