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
            case MI_LOAD:

            case MI_EDITOR:

            case MI_QUIT:

            case MI_NEW:

            case MI_CHANGE_SIZE:

            case MI_DELETE:

            case MI_CREATE:

            case MI_TORUS:
                this.getModel().setTorus(!this.getModel().isTorus());
                break;
            case MI_ZOOM_IN:

            case MI_ZOOM_OUT:

            case MI_SAVE_XML:

            case MI_SAVE_SERIAL:

            case MI_LOAD_XML:

            case MI_LOAD_SERIAL:

            case MI_PRINT:

            case MI_GIF:

            case MI_PNG:

            //
            default:
                System.out.println("MenuAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());
        }
    }
}
