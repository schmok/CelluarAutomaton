package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.menu.CAMenuBar;
import cellularautomaton.view.util.IOwnEnumeration;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

/**
 * Created by vspadi on 08.11.15.
 */
public class MenuController extends AbstractController<CAMenuBar> {

    MenuController(CAMenuBar view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {
            default:
                System.out.println("MenuAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());
        }
    }
}
