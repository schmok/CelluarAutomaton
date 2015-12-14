package cellularautomaton.model.automata;

import cellularautomaton.annotation.Callable;
import cellularautomaton.model.Automaton;
import cellularautomaton.model.Cell;

/**
 * Created by Viktor Spadi on 18.10.2015.
 */
public class KruemelMonster extends Automaton {

    public KruemelMonster(int rows, int columns, boolean isTorus) {
        super(rows, columns, 10, false, isTorus);

    }
    protected Cell transform(Cell cell, Cell[] neighbors) {
        Cell newCell = new Cell(cell);
        // Count living neighbors
        for(Cell n : neighbors) {
            if(n != null) {
                if(n.getState() == cell.getState()+1) {
                    newCell.setState(n.getState());
                    break;
                } if(cell.getState() == getNumberOfStates() -1 && n.getState() == 0) {
                    newCell.setState(n.getState());
                    break;
                }
            }
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
