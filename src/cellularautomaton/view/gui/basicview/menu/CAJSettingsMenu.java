package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Viktor Spadi on 18.01.2016.
 */
public class CAJSettingsMenu extends JMenu implements Observer, IOwnEnumeration {
    private CAJMenuItem saveItem;
    private CAJMenuItem loadItem;
    private CAJMenuItem deleteItem;

    public CAJMenuItem getSaveItem(){ return saveItem; }
    public CAJMenuItem getLoadItem() { return loadItem; }
    public CAJMenuItem getDeleteItem() { return deleteItem; }

    public CAJSettingsMenu(StringController stringController) {
        super(stringController.get(StringEnumeration.MB_SETTINGS));

        add(this.saveItem = new CAJMenuItem(StringEnumeration.MI_SETTINGS_SAVE));
        add(this.loadItem = new CAJMenuItem(StringEnumeration.MI_SETTINGS_LOAD));
        add(this.deleteItem = new CAJMenuItem(StringEnumeration.MI_SETTINGS_DELETE));
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.MB_SETTINGS;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
