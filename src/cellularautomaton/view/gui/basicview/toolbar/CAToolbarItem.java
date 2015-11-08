package cellularautomaton.view.gui.basicview.toolbar;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.FileHelper;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAToolbarItem extends JButton implements IOwnEnumeration {
    public CAToolbarItem(String icon) {
        super();
        setIcon(FileHelper.getInstance().getIcon(icon));
        setBorder(null);
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.CA_TOOLBARITEM;
    }
}
