package cellularautomaton.view.gui.basicview.windows;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.components.CAJButton;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vspadi on 09.11.15.
 */
public class CAChangeCellColorWindow extends JColorChooser implements IOwnEnumeration{

    public CAChangeCellColorWindow() {
        super();
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.W_CHANGE_SIZE_WINDOW;
    }

}
