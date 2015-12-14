package cellularautomaton.view.gui.basicview.windows;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.FileHelper;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Created by vspadi on 07.12.15.
 */
public class CAJAutomatonClassChooser extends JFileChooser implements IOwnEnumeration {

    public CAJAutomatonClassChooser() {
        super(FileHelper.getInstance().getAutomataPath());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(StringController.getInstance().get(StringEnumeration.W_AUTOMATON_FNEF), "class");

        this.setFileFilter(filter);
    }
    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.W_AUTOMATON_CHOOSER;
    }
}
