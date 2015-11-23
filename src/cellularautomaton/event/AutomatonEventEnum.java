package cellularautomaton.event;

/**
 * Created by viktorspadi on 08.11.15.
 */
public enum AutomatonEventEnum {
    COLOR_CHANGED,
    CELL_CHANGED,
    TORUS_CHANGED,
    SIZE_CHANGED,
    NEW_AUTOMATON,
    SIMSTATE_CHANGED,
    INTERVAL_CHANGED,
    CLOSE;

    AutomatonEventEnum() {
    }

    @Override
    public String toString() {
        return this.name();
    }
}
