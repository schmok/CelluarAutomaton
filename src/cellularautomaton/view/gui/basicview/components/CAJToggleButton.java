package cellularautomaton.view.gui.basicview.components;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.FileHelper;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAJToggleButton extends JToggleButton implements IOwnEnumeration {
    private StringEnumeration type;

    public CAJToggleButton(String icon, StringEnumeration text) {
        super();
        this.type = text;
        if(icon != null)
            setIcon(FileHelper.getInstance().getIcon(icon));
        //setBorder(null);
    }

    @Override
    public StringEnumeration getEnumeration() {
        return type;
    }
}
