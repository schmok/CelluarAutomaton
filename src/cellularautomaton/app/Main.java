package cellularautomaton.app;

import cellularautomaton.controller.*;
import cellularautomaton.model.Automaton;
import cellularautomaton.model.CellularAutomaton;
import cellularautomaton.model.automata.GameOfLifeAutomaton;
import cellularautomaton.view.*;

import java.util.HashMap;

/**
 * Created by Viktor Spadi on 14.10.2015.
 *
 * Entry point
 *
 */
public class Main {
    public static HashMap<CellularAutomatonController,Long> runnableHashMap = new HashMap<>();
    public static long id=0;
    public static int instanceCount = 0;

    public static void main(String[] args) {
        createAutomatonWindow(new GameOfLifeAutomaton(15, 15, true));
    }

    public static void createAutomatonWindow(Automaton automaton) {
        instanceCount++;
        AutomatonView automatonView = new AutomatonView();
        CellularAutomatonController automatonController = new CellularAutomatonController(automatonView);
        CellularAutomaton automatonApp = new CellularAutomaton(automatonController, automaton);
        Runnable runner = new Runnable() {
            public void run() {
                automatonApp.start();
            }
        };
        runnableHashMap.put(automatonController, id++);
        javax.swing.SwingUtilities.invokeLater(runner);
    }

    public static void closeWindow(CellularAutomatonController cac) {
        Long id = runnableHashMap.get(cac);
        if(id != null){
            instanceCount--;
            cac.getModel().setRunning(false);
            cac.getView().getFrame().dispose();
            runnableHashMap.remove(cac);
            if(instanceCount == 0) {
                System.exit(0);
            }
        }
    }
}
