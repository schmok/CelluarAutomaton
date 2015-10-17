package cellularautomaton.controller.locale;

/**
 * Created by Viktor Spadi on 14.10.2015.
 *
 * contains all used strings in this project
 *
 * http://stackoverflow.com/questions/3978654/best-way-to-create-enum-of-strings
 */
public enum StringEnumeration {
    // Menuitems
    WINDOW_TITLE("CAS"),
    MB_AUTOMATON("Automaton"),
    MI_NEW("Neu..."),
    MI_LOAD("Laden..."),
    MI_EDITOR("Editor"),
    MI_QUIT("Beenden"),
    MB_POPULATION("Population"),
    MI_CHANGE_SIZE("Gr\u00f6\u00dfe \u00e4ndern..."),
    MI_DELETE("L\u00f6schen"),
    MI_CREATE("Erzeugen"),
    MI_TORUS("Torus"),
    MI_ZOOM_IN("Vergr\u00f6\u00dfern"),
    MI_ZOOM_OUT("Verkleinern"),
    MS_SAVE("Speichern"),
    MS_LOAD("Laden"),
    MI_XML("XML"),
    MI_SERIAL("Serialisiert"),
    MI_PRINT("Drucken..."),
    MS_SAVE_AS_IMG("Als Bild Speichern"),
    MI_GIF("GIF"),
    MI_PNG("PNG"),
    MB_SIMULATION("Simulation"),
    MI_STEP("Schritt"),
    MI_START("Start"),
    MI_STOP("Stop"),
    MB_HELP("Hilfe"),
    MI_HELP("Hilfe"),
    MI_INFO("\u00dcber CA Simulator"),
    // Toolbar
    TB_NAME("Toolbar")
    ;

    private final String text;

    // Methods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param text
     */
    StringEnumeration(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
