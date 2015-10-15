package cellularautomaton.model;

import cellularautomaton.controller.CellularAutomatonController;

import java.util.Observable;

/**
 * Created by Viktor Spadi on 14.10.2015.
 *
 * This is the main class of the cellular automaton
 */
public class CellularAutomaton extends Observable {
    // Attriubues //////////////////////////////////////////////////////////////////////////////////////////////////////

    private CellularAutomatonController automatonController;

    // Methods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     * Constructor
     * @param automatonController
     */
    public CellularAutomaton(CellularAutomatonController automatonController) {
        this.automatonController = automatonController;
        this.automatonController.bindModel(this);
    }

    /*
     * Method to start the Automaton
     */
    public void start() {
        this.automatonController.open();
    }

    public void terminate() {
        // ToDo later on "on close do you want to save?" logic
        System.exit(0);
    }
}
