package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Viktor Spadi on 15.10.2015.
 */
public class CASimulationMenu extends JMenu implements Observer, IOwnEnumeration {
    private CAJMenuItem stepItem;
    private CAJMenuItem startItem;
    private CAJMenuItem stopItem;

    public CAJMenuItem getStepItem() {
        return stepItem;
    }

    public CAJMenuItem getStartItem() {
        return startItem;
    }

    public CAJMenuItem getStopItem() {
        return stopItem;
    }

    public CASimulationMenu(StringController stringController) {
        super(stringController.get(StringEnumeration.MB_SIMULATION));

        // Add components
        add(this.stepItem = new CAJMenuItem(StringEnumeration.MI_STEP));
        add(this.stepItem = new CAJMenuItem(StringEnumeration.MI_START));
        add(this.stepItem = new CAJMenuItem(StringEnumeration.MI_STOP));
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.CA_SIMULATIONMENU;
    }
}
