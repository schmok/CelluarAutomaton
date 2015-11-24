package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.toolbar.CAToolbar;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;

/**
 * Created by vspadi on 08.11.15.
 */
public class ToolbarController extends AbstractController<CAToolbar, CellularAutomatonController> {


    ToolbarController(CAToolbar view, CellularAutomatonController cac) {
        super(view, cac);
        view.getSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                getModel().setInterVal(((JSlider)e.getSource()).getValue());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {
            case MI_START:
                this.getModel().setIsRunning(true);
                break;
            case MI_STOP:
                this.getModel().setIsRunning(false);
                break;
            default:
                System.out.println("ToolbarAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());

        }
    }
}
