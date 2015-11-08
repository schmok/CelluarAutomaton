package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Viktor Spadi on 15.10.2015.
 */
public class CAHelpMenu extends JMenu implements Observer, IOwnEnumeration {
    private CAJMenuItem helpItem;
    private CAJMenuItem infoItem;

    public CAJMenuItem getInfoItem() {
        return infoItem;
    }

    public CAJMenuItem getHelpItem() {
        return helpItem;
    }

    public CAHelpMenu(StringController stringController) {
        super(stringController.get(StringEnumeration.MB_HELP));

        // Add components
        add(this.helpItem = new CAJMenuItem(StringEnumeration.MI_HELP));
        add(this.infoItem = new CAJMenuItem(StringEnumeration.MI_INFO));
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.MB_HELP;
    }
}
