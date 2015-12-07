package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;

/**
 * Created by vspadi on 07.12.15.
 */
public class CAJPopupMenu extends JPopupMenu implements IOwnEnumeration {

    public CAJPopupMenu() {

    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.PM_POPUP_MENU;
    }
}
