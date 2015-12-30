package cellularautomaton.view.gui.basicview.windows;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.components.CAJButton;
import cellularautomaton.view.gui.basicview.components.CAJFrame;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vspadi on 14.12.15.
 */
public class CANewAutomatonWindow extends CAJFrame implements IOwnEnumeration {

    private JTextField nameField;
    private CAJButton submitButton;

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
        super(StringEnumeration.W_NEW_AUTOMATON_WINDOW);

        this.nameField = new JTextField();
        this.submitButton = new CAJButton(StringEnumeration.W_NEW_AUTOMATON);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(0,2));
        p1.add(nameField);
        p1.add(submitButton);
        this.add(p1);

        this.pack();
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.W_NEW_AUTOMATON_WINDOW;
    }

    public void reset() {
        this.nameField.setText("");
    }

}
