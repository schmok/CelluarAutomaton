package cellularautomaton.app;

import cellularautomaton.controller.*;
import cellularautomaton.controller.locale.StringController;
import cellularautomaton.model.CellularAutomaton;
import cellularautomaton.view.*;

/**
 * Created by Viktor Spadi on 14.10.2015.
 *
 * Entry point
 *
 */
public class Main {
    public static void main(String[] args) {
        // Start Automaton
        // Choose View
        BasicAutomatonView basicAutomatonView = new BasicAutomatonView(StringController.getInstance());

        // Init AutomatonController
        CellularAutomatonController automatonController = new CellularAutomatonController(basicAutomatonView);

        // Create Automaton
        CellularAutomaton automaton = new CellularAutomaton(automatonController);

        // Start it
        automaton.start();
    }
}
