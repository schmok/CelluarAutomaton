package cellularautomaton.model.automata;

import cellularautomaton.annotation.Callable;
import cellularautomaton.model.Automaton;
import cellularautomaton.model.Cell;

import java.awt.*;

/**
 * Created by Viktor Spadi on 18.10.2015.
 */
public class BlurAutomaton extends Automaton {

    public BlurAutomaton(int rows, int columns, boolean isTorus) {
        super(rows, columns, 100, false, isTorus);
        mapColors();
    }
    protected Cell transform(Cell cell, Cell[] neighbors) {
        Cell newCell = new Cell(cell);
        // Count living neighbors
        int sum = 0;
        int iters = 0;
        for(Cell n : neighbors) {
            if(n != null) {
               sum += n.getState();
                iters++;
            }
        }
        sum /= iters;
        newCell.setState(sum);
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

    private void mapColors() {
        Color[]colors = new Color[getNumberOfStates()];
        for(int i = 0; i < getNumberOfStates(); i++) {
            int num = (int)(255.0/getNumberOfStates()*((float)getNumberOfStates()-(float)i));
            colors[i] = new Color(num, num, num);
        }
        setColors(colors);
    }
}
