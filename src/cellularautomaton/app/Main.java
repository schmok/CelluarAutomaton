package cellularautomaton.app;

import cellularautomaton.controller.*;
import cellularautomaton.model.CellularAutomaton;
import cellularautomaton.view.*;

import java.util.Locale;

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
        AutomatonView automatonView = new AutomatonView();

        // Init AutomatonController
        CellularAutomatonController automatonController = new CellularAutomatonController(automatonView);

        // Create Automaton
        CellularAutomaton automaton = new CellularAutomaton(automatonController);

        // Start it
        automaton.start();
        /*
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System: " +currentDir);
        */
        Locale loc = new Locale.Builder().setLanguage("de").setRegion("DE").build();
        
    }
}
