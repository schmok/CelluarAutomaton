package cellularautomaton.model;

/**
 * Created by Viktor Spadi on 18.10.2015.
 */
public class Cell {
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

    void setState(int state) {
        this.state = state;
    }
}