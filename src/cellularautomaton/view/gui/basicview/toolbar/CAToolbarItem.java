package cellularautomaton.view.gui.basicview.toolbar;

import cellularautomaton.view.util.FileHelper;

import javax.swing.*;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAToolbarItem extends JButton {
    public CAToolbarItem(String icon) {
        super();
        setIcon(FileHelper.getInstance().getIcon(icon));
        setBorder(null);
    }
}
