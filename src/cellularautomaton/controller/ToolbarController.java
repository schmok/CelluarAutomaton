package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.toolbar.CAToolbar;
import cellularautomaton.view.util.IOwnEnumeration;

import java.awt.event.ActionEvent;

/**
 * Created by vspadi on 08.11.15.
 */
public class ToolbarController extends AbstractController<CAToolbar, CellularAutomatonController> {


    ToolbarController(CAToolbar view, CellularAutomatonController cac) {
        super(view, cac);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {

            default:
                //System.out.println("ToolbarAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());
        }
    }
}
