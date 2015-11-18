package cellularautomaton.view.gui.basicview.components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by viktorspadi on 17.11.15.
 */
public class CAJScrollPane extends JScrollPane {
    public CAJScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);
    }

    @Override
    public void setLocation(Point p) {
        super.setLocation(p);
    }
}
