package cellularautomaton.view.gui.basicview.components;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;

import javax.swing.*;
import java.awt.*;

/**
 * Created by viktorspadi on 30.12.15.
 */
public class CAJFrame extends Frame {
    private JFrame parentFrame;

    public CAJFrame(StringEnumeration text) {
        super(StringController.getInstance().get(text));
    }

    public void setParentFrame(JFrame component) {
        this.parentFrame = component;
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if(b) {
            Container parent = this.parentFrame;
            if(parent != null) {
                Point p = new Point(parent.getWidth() /2 + parent.getX()-this.getWidth() / 2,
                        parent.getHeight() / 2 + parent.getY()-this.getHeight() / 2);
                this.setLocation(p);
            } else {
                this.setLocation(100,100);
            }
        }
    }
}
