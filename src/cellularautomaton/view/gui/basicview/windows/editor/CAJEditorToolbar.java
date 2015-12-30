package cellularautomaton.view.gui.basicview.windows.editor;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.toolbar.CAToolbarItem;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;

/**
 * Created by viktorspadi on 30.12.15.
 */
public class CAJEditorToolbar extends JToolBar implements IOwnEnumeration {
    private CAToolbarItem compileButton;

    public CAToolbarItem getCompileButton() { return compileButton; }

    public CAJEditorToolbar(){
        super();
        this.compileButton = new CAToolbarItem("Compile24.gif", StringEnumeration.MI_COMPILE);
        this.add(compileButton);
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.CA_EDITOR_TOOLBAR;
    }
}
