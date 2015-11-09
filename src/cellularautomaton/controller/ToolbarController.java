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
            case MI_TORUS:
                this.getModel().setTorus(this.getView().getTorusButton().isSelected());
                break;
            case MI_CHANGE_SIZE:
                this.getView().getChangeSizeWindow().setVisible(true);
                this.getView().getChangeSizeWindow().toFront();
                break;
            case W_CHANGED_SIZE:
                try {
                    int width = Integer.parseInt(this.getView().getChangeSizeWindow().getWidthField().getText());
                    int height = Integer.parseInt(this.getView().getChangeSizeWindow().getHeightField().getText());
                    boolean valid = true;

                    if(width <= 0) {
                        this.getView().getChangeSizeWindow().getWidthField().setText("");
                        valid = false;
                    }
                    if(height <= 0) {
                        this.getView().getChangeSizeWindow().getHeightField().setText("");
                        valid = false;
                    }

                    if(valid) {
                        this.getModel().setSize(width, height);
                        this.getView().getChangeSizeWindow().setVisible(false);
                    }
                } catch(Exception ex) {
                    //ex.printStackTrace();
                }

                break;
            default:
                System.out.println("ToolbarAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());
        }
    }
}
