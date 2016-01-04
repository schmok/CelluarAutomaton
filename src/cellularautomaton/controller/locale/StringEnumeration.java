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
    WINDOW_TITLE("WINDOW_TITLE"),
    MB_AUTOMATON("MB_AUTOMATON"),
    MI_NEW("MI_NEW"),
    MI_LOAD("MI_LOAD"),
    MI_EDITOR("MI_EDITOR"),
    MI_QUIT("MI_QUIT"),
    MB_POPULATION("MB_POPULATION"),
    MI_CHANGE_SIZE("MI_CHANGE_SIZE"),
    MI_DELETE("MI_DELETE"),
    MI_CREATE("MI_CREATE"),
    MI_TORUS("MI_TORUS"),
    MI_ZOOM_IN("MI_ZOOM_IN"),
    MI_ZOOM_OUT("MI_ZOOM_OUT"),
    MS_SAVE("MS_SAVE"),
    MI_SAVE_XML("MI_SAVE_XML"),
    MI_SAVE_SERIAL("MI_SAVE_SERIAL"),
    MS_LOAD("MS_LOAD"),
    MI_LOAD_XML("MI_LOAD_XML"),
    MI_LOAD_SERIAL("MI_LOAD_SERIAL"),
    MI_PRINT("MI_PRINT"),
    MS_SAVE_AS_IMG("MS_SAVE_AS_IMG"),
    MI_GIF("MI_GIF"),
    MI_PNG("MI_PNG"),
    MB_SIMULATION("MB_SIMULATION"),
    MI_STEP("MI_STEP"),
    MI_START("MI_START"),
    MI_STOP("MI_STOP"),
    MB_HELP("MB_HELP"),
    MI_HELP("MI_HELP"),
    MI_INFO("MI_INFO"),
    MI_RANDOM("MI_RANDOM"),
    // Toolbar
    TB_NAME("TB_NAME"),
    MI_COMPILE("MI_COMPILE"),

    // J Objects
    CA_CELL(""),
    CA_FOOTER(""),
    CA_MENUBAR(""),
    CA_POPULATIONMENU(""),
    CA_SIMULATIONMENU(""),
    CA_POPULATIONCONTAINER(""),
    CA_STATECONTAINER(""),
    CA_TOOLBAR(""),
    CA_TOOLBARITEM(""),
    CA_MAINWINDOW(""),
    CA_EDITOR_TOOLBAR(""),


    // Windows
    W_CHANGE_SIZE_WINDOW("W_CHANGE_SIZE_WINDOW"),
    W_CHANGED_SIZE("W_CHANGED_SIZE"),
    W_AUTOMATON_CHOOSER("W_AUTOMATON_CHOOSER"),
    W_AUTOMATON_FNEF_CLASS("W_AUTOMATON_FNEF_CLASS"),
    W_AUTOMATON_FNEF_SER("W_AUTOMATON_FNEF_SER"),
    W_AUTOMATON_FNEF_XML("W_AUTOMATON_FNEF_XML"),
    W_NEW_AUTOMATON_WINDOW("W_NEW_AUTOMATON"),
    W_NEW_AUTOMATON("W_NEW_AUTOMATON_WINDOW"),
    W_AUTOMATON_EDITOR("W_AUTOMATON_EDITOR"),
    // States ect.
    SI_STATE(""),
    SI_COLOR(""),

    // Popups
    PM_POPUP_MENU("")
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
