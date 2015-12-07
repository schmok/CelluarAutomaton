package cellularautomaton.app;

import cellularautomaton.controller.*;
import cellularautomaton.model.Automaton;
import cellularautomaton.model.CellularAutomaton;
import cellularautomaton.model.internalautomata.GameOfLifeAutomaton;
import cellularautomaton.view.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Viktor Spadi on 14.10.2015.
 *
 * Entry point
 *
 */
public class Main {

    public static int instanceCount = 0;

    public static void main(String[] args) {
        createAutomatonWindow(new GameOfLifeAutomaton(15, 15, true));
    }

    public static void createAutomatonWindow(Automaton automaton) {
        instanceCount++;
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AutomatonView automatonView = new AutomatonView();
                CellularAutomatonController automatonController = new CellularAutomatonController(automatonView);
                CellularAutomaton automatonApp = new CellularAutomaton(automatonController, automaton);
                automatonApp.start();
            }
        });
    }
}
