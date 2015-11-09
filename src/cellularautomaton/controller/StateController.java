package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.cells.CAStateCell;
import cellularautomaton.view.gui.basicview.components.CAJButton;
import cellularautomaton.view.gui.basicview.states.CAStateContainer;
import cellularautomaton.view.util.IOwnEnumeration;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vspadi on 08.11.15.
 */
public class StateController extends AbstractController<CAStateContainer, CellularAutomatonController> {

    StateController(CAStateContainer view, CellularAutomatonController cac) {
        super(view, cac);
        // specific case -> state cells
    }

    public void bindStates() {
        this.getView().removeAll();
        for(int i = 0; i < this.getModel().getNumberOfStates(); i++) {
            this.getView().addCell(this.getModel().getColor(i));
            this.getView().getCell(i).getStateButton().addActionListener(this);
            this.getView().getCell(i).getColorButton().addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {
            case SI_COLOR:
                CAStateCell cell = (CAStateCell)((CAJButton)e.getSource()).getParent();

                Color newColor = this.getParent().getView().getChangeCellColorWindow().showDialog(null, "Change Color", cell.getBackground());
                //cell.setBackground(newColor);
                this.getModel().changeColor(cell.getState(), newColor);
                break;
            default:
                System.out.println("StateAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());
        }
    }
}
