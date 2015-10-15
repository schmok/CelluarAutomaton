package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringEnumeration;

import javax.swing.*;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class CAJSubMenu extends JMenu {
    public CAJSubMenu(StringEnumeration text) {
        super(text.toString());
    }
}
