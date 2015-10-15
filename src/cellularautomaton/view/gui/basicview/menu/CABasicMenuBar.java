package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringController;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class CABasicMenuBar extends JMenuBar implements Observer {
    CABasicAutomatonMenu automatonMenu;
    CABasicPopulationMenu populationMenu;
    CABasicSimulationMenu simulationMenu;
    CABasicHelpMenu helpMenu;

    public CABasicMenuBar(StringController stringController) {
        add(this.automatonMenu = new CABasicAutomatonMenu(stringController));
        add(this.populationMenu = new CABasicPopulationMenu(stringController));
        add(this.simulationMenu = new CABasicSimulationMenu(stringController));
        add(this.helpMenu = new CABasicHelpMenu(stringController));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
