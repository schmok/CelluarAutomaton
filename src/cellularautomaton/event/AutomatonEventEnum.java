package cellularautomaton.event;

/**
 * Created by viktorspadi on 08.11.15.
 */
public enum AutomatonEventEnum {
    COLOR_CHANGED,
    CELL_CHANGED,
    TORUS_CHANGED,
    SIZE_CHANGED,
    CLOSE;

    AutomatonEventEnum() {
    }

    @Override
    public String toString() {
        return this.name();
    }
}
