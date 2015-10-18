package cellularautomaton.controller;

import cellularautomaton.model.CellularAutomaton;
import cellularautomaton.view.AbstractAutomatonView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Viktor Spadi on 14.10.2015.
 *
 *
 */
public class CellularAutomatonController implements ActionListener {
    // Attributes //////////////////////////////////////////////////////////////////////////////////////////////////////
    private CellularAutomaton cellularAutomaton;
    private AbstractAutomatonView automatonView;

    // Methods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    * Constructor
    *
    * @param viewController     the ViewController to use
    *
    */
    public CellularAutomatonController(AbstractAutomatonView automatonView) {
        this.automatonView = automatonView;

        bindEvents();
    }

    /*
     * Pass call to the view
     */
    public void open() {
        this.automatonView.open();
    }

    public void bindModel(CellularAutomaton cellularAutomaton) {
        this.cellularAutomaton = cellularAutomaton;
    }

    private void bindEvents() {
        // Terminate
        this.automatonView.bindTerminate(() -> {
            cellularAutomaton.terminate();
            return null;
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Some Action E");
    }
}
