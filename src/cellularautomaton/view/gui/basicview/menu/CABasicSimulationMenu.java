package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Viktor Spadi on 15.10.2015.
 */
public class CABasicSimulationMenu extends JMenu implements Observer {
    private CAJMenuItem stepItem;
    private CAJMenuItem startItem;
    private CAJMenuItem stopItem;

    public CABasicSimulationMenu(StringController stringController) {
        super(stringController.get(StringEnumeration.MB_SIMULATION));
        add(this.stepItem = new CAJMenuItem(StringEnumeration.MI_STEP));
        add(this.stepItem = new CAJMenuItem(StringEnumeration.MI_START));
        add(this.stepItem = new CAJMenuItem(StringEnumeration.MI_STOP));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
