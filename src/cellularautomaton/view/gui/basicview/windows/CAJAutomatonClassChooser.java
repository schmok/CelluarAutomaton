package cellularautomaton.view.gui.basicview.windows;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Created by vspadi on 07.12.15.
 */
public class CAJAutomatonClassChooser extends JFileChooser implements IOwnEnumeration {

    public CAJAutomatonClassChooser() {
        //super(new File("./out/production/CelluarAutomaton/cellularautomaton/model/internalautomata"));
        //System.out.println(StringController.getInstance().getInternalAutomatonPath());
        super(StringController.getInstance().getInternalAutomatonPath());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(StringController.getInstance().get(StringEnumeration.W_AUTOMATON_FNEF), "class");

        this.setFileFilter(filter);
    }
    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.W_AUTOMATON_CHOOSER;
    }
}
