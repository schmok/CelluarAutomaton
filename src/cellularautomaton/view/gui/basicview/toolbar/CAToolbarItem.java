package cellularautomaton.view.gui.basicview.toolbar;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.FileHelper;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAToolbarItem extends JButton implements IOwnEnumeration {
    private StringEnumeration type;

    public CAToolbarItem(String icon, StringEnumeration text) {
        super();
        this.type = text;
        setIcon(FileHelper.getInstance().getIcon(icon));
    }

    @Override
    public StringEnumeration getEnumeration() {
        return type;
    }
}
