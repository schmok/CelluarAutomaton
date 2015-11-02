package cellularautomaton.gol;

/**
 * Created by Viktor Spadi on 18.10.2015.
 */
public class Cell {
    private int state;

    public Cell () {
        this(0);
    }

    public Cell(int state) {
        this.state = state;
    }

    public Cell(Cell cell) {
        this(cell.state);
    }

    public int getState() {
        return this.state;
    }

    void setState(int state) {
        this.state = state;
    }
}