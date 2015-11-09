package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.states.CAPopulationContainer;
import cellularautomaton.view.util.IOwnEnumeration;

import java.awt.event.ActionEvent;

/**
 * Created by vspadi on 08.11.15.
 */
public class PoppulationController extends AbstractController<CAPopulationContainer, CellularAutomatonController> {

    PoppulationController(CAPopulationContainer view, CellularAutomatonController cac) {
        super(view, cac);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {
            default:
                System.out.println("PoppulationAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());
        }
    }
}
