package cellularautomaton.view.gui.basicview.windows;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.FileHelper;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by vspadi on 07.12.15.
 */
public class CAJAutomatonClassChooser extends JFileChooser implements IOwnEnumeration {
    public enum FILTER {
        CLASS,
        SERIAL,
        XML
    }

    public CAJAutomatonClassChooser() {
        super(FileHelper.getInstance().getAutomataPath());

        // default filter
        FileNameExtensionFilter filter = new FileNameExtensionFilter(StringController.getInstance().get(StringEnumeration.W_AUTOMATON_FNEF_CLASS), "class");
        this.setFileFilter(filter);
    }

    public void setFilter(FILTER f) {
        FileNameExtensionFilter filter = null;
        switch(f) {
            case CLASS:
                filter = new FileNameExtensionFilter(StringController.getInstance().get(StringEnumeration.W_AUTOMATON_FNEF_CLASS), "class");
                break;
            case SERIAL:
                filter = new FileNameExtensionFilter(StringController.getInstance().get(StringEnumeration.W_AUTOMATON_FNEF_SER), "ser");
                break;
            case XML:
                filter = new FileNameExtensionFilter(StringController.getInstance().get(StringEnumeration.W_AUTOMATON_FNEF_XML), "xml");
                break;
        }
        if(filter != null) {
            this.setFileFilter(filter);
        }
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.W_AUTOMATON_CHOOSER;
    }
}
