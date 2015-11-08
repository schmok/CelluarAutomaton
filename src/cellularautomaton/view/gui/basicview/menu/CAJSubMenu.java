package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class CAJSubMenu extends JMenu implements IOwnEnumeration {
    private StringController stringController;
    private StringEnumeration type;

    public CAJSubMenu(StringEnumeration text) {
        super(text.toString());
        this.type = text;
        this.stringController = StringController.getInstance();
    }

    @Override
    public StringEnumeration getEnumeration() {
        return this.type;
    }
}
