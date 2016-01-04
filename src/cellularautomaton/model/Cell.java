package cellularautomaton.model;

import java.io.Serializable;

/**
 * Created by Viktor Spadi on 18.10.2015.
 */
public class Cell implements Serializable{
    private int state;

    public Cell () {
        this.state = 0;
    }

    public Cell(int state) {
        this.state = state;
    }

    public Cell(Cell cell) {
        this.state = cell.state;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }
}