package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;

import javax.swing.*;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class CAJMenuItem extends JMenuItem {
    private StringController  stringController;

    public CAJMenuItem(StringEnumeration text) {
        super(text.toString());
        stringController = StringController.getInstance();
        setAccelerator(stringController.getAccelerator(text));
        setMnemonic(stringController.getMnemonic(text));
    }
}
