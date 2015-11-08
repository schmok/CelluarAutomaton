package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.CellularAutomatonController;
import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class CAMenuBar extends JMenuBar implements Observer, IOwnEnumeration {
    private CAAutomatonMenu automatonMenu;
    private CAPopulationMenu populationMenu;
    private CASimulationMenu simulationMenu;
    private CAHelpMenu helpMenu;

    @Override
    public CAHelpMenu getHelpMenu() {
        return helpMenu;
    }

    public CASimulationMenu getSimulationMenu() {
        return simulationMenu;
    }

    public CAPopulationMenu getPopulationMenu() {
        return populationMenu;
    }

    public CAAutomatonMenu getAutomatonMenu() {
        return automatonMenu;
    }


    public CAMenuBar(StringController stringController) {

        // Add components
        add(this.automatonMenu = new CAAutomatonMenu(stringController));
        add(this.populationMenu = new CAPopulationMenu(stringController));
        add(this.simulationMenu = new CASimulationMenu(stringController));
        add(this.helpMenu = new CAHelpMenu(stringController));
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.CA_MENUBAR;
    }
}
