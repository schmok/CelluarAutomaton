package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class CABasicAutomatonMenu extends JMenu implements Observer {
    private CAJMenuItem newItem;
    private CAJMenuItem loadItem;
    private CAJMenuItem editorItem;
    private CAJMenuItem quitItem;

    public CABasicAutomatonMenu(StringController stringController) {
        super(stringController.get(StringEnumeration.MB_AUTOMATON));
        add(this.newItem = new CAJMenuItem(StringEnumeration.MI_NEW));
        add(this.loadItem = new CAJMenuItem(StringEnumeration.MI_LOAD));
        addSeparator();
        add(this.editorItem = new CAJMenuItem(StringEnumeration.MI_EDITOR));
        add(this.quitItem = new CAJMenuItem(StringEnumeration.MI_QUIT));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
