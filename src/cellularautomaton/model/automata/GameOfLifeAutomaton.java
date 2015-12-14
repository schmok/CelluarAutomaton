package cellularautomaton.model.automata;

import cellularautomaton.annotation.Callable;
import cellularautomaton.model.Automaton;
import cellularautomaton.model.Cell;

/**
 * Created by Viktor Spadi on 18.10.2015.
 */
public class GameOfLifeAutomaton extends Automaton {

    public GameOfLifeAutomaton(int rows, int columns, boolean isTorus) {
        super(rows, columns, 2, true, isTorus);

    }
    protected Cell transform(Cell cell, Cell[] neighbors) {
        Cell newCell = new Cell(cell);
        int living = 0;
        // Count living neighbors
        for(Cell n : neighbors) {
            if(n != null && n.getState() == 1)
                living++;
        }

        // Set cell status
        switch(cell.getState()) {
            case 0:
                if(living == 3)
                    newCell.setState(1);
                break;
            case 1:
                if(living > 3)
                    newCell.setState(0);
                if(living < 2)
                    newCell.setState(0);
                break;
        }
        return newCell;
    }

    @Callable
    public void setGlider() {
        setState(1,2,1);
        setState(2,3,1);
        setState(3,1,1);
        setState(3,2,1);
        setState(3,3,1);
    }
}
