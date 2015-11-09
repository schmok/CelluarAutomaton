package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.states.CAStateContainer;
import cellularautomaton.view.util.IOwnEnumeration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vspadi on 08.11.15.
 */
public class StateController extends AbstractController<CAStateContainer, CellularAutomatonController> {

    StateController(CAStateContainer view, CellularAutomatonController cac) {
        super(view, cac);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {
            default:
                System.out.println("StateAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());
        }
    }
}
