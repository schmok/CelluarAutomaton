package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.menu.CAMenuBar;
import cellularautomaton.view.util.IOwnEnumeration;

import java.awt.event.ActionEvent;

/**
 * Created by vspadi on 08.11.15.
 */
public class MenuController extends AbstractController<CAMenuBar, CellularAutomatonController> {

    MenuController(CAMenuBar view, CellularAutomatonController cac) {
        super(view, cac);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {
            // Automatonmenu

            //
            default:
                System.out.println("MenuAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());
        }
    }
}
