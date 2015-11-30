package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class CAJMenuItem extends JMenuItem implements IOwnEnumeration {
    private StringController  stringController;
    private StringEnumeration type;

    public CAJMenuItem(StringEnumeration text) {
        super(StringController.getInstance().get(text));
        this.type = text;
        stringController = StringController.getInstance();
        setAccelerator(stringController.getAccelerator(text));
        setMnemonic(stringController.getMnemonic(text));
    }


    @Override
    public StringEnumeration getEnumeration() {
        return this.type;
    }
}
