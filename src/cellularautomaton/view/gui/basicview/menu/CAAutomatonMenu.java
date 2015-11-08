package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.CellularAutomatonController;
import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.AutomatonView;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class CAAutomatonMenu extends JMenu implements Observer, IOwnEnumeration{
    private CAJMenuItem loadItem;
    private CAJMenuItem editorItem;
    private CAJMenuItem quitItem;

    private CAJMenuItem newItem;

    public CAJMenuItem getNewItem() {
        return newItem;
    }

    public CAJMenuItem getLoadItem() {
        return loadItem;
    }

    public CAJMenuItem getEditorItem() {
        return editorItem;
    }

    public CAJMenuItem getQuitItem() {
        return quitItem;
    }

    public CAAutomatonMenu(StringController stringController) {
        super(stringController.get(StringEnumeration.MB_AUTOMATON));

        // Add components
        add(this.newItem = new CAJMenuItem(StringEnumeration.MI_NEW));
        add(this.loadItem = new CAJMenuItem(StringEnumeration.MI_LOAD));
        addSeparator();
        add(this.editorItem = new CAJMenuItem(StringEnumeration.MI_EDITOR));
        add(this.quitItem = new CAJMenuItem(StringEnumeration.MI_QUIT));
        setMnemonic(stringController.getMnemonic(StringEnumeration.MB_AUTOMATON));
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.MB_AUTOMATON;
    }
}
