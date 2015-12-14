package cellularautomaton.view.gui.basicview.components;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;

/**
 * Created by vspadi on 09.11.15.
 */
public class CAJButton extends JButton implements IOwnEnumeration{
    private StringEnumeration type;

    public CAJButton(StringEnumeration text) {
        super();
        this.type = text;
        this.setText(StringController.getInstance().get(text));
    }

    @Override
    public StringEnumeration getEnumeration() {
        return type;
    }
}
