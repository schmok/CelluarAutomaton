package cellularautomaton.view.gui.basicview.toolbar;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Viktor Spadi on 17.10.2015.
 *
 * http://www.java2s.com/Code/Java/Swing-JFC/DeterminingWhenaFloatableJToolBarContainerChangesOrientation.htm
 */
public class CAToolbar extends JToolBar {
    StringController stringController;
    CAToolbarItem newButton;
    CAToolbarItem openButton;
    CAToolbarItem gridButton;
    CAToolbarItem deleteButton;
    CAToolbarItem randomButton;
    CAToolbarItem torusButton;
    CAToolbarItem printButton;
    CAToolbarItem zoomInButton;
    CAToolbarItem zoomOutButton;
    CAToolbarItem stepButton;
    CAToolbarItem simulateButton;
    CAToolbarItem stopButton;
    JSlider slider;

    public CAToolbar(StringController stringController) {
        super(stringController.get(StringEnumeration.TB_NAME));

        // ToDo remove hardcoded icons
        add(this.newButton = new CAToolbarItem("New24.gif"));
        addSeparator(new Dimension(5,5));
        add(this.newButton = new CAToolbarItem("Open24.gif"));
        addSeparator(new Dimension(15,15));
        add(this.newButton = new CAToolbarItem("Size24.gif"));
        addSeparator(new Dimension(5,5));
        add(this.newButton = new CAToolbarItem("Delete24.gif"));
        addSeparator(new Dimension(5,5));
        add(this.newButton = new CAToolbarItem("Random24.gif"));
        addSeparator(new Dimension(5,5));
        add(this.newButton = new CAToolbarItem("Torus24.gif"));
        addSeparator(new Dimension(5,5));
        add(this.newButton = new CAToolbarItem("Print24.gif"));
        addSeparator(new Dimension(15,15));
        add(this.newButton = new CAToolbarItem("ZoomIn24.gif"));
        addSeparator(new Dimension(5,5));
        add(this.newButton = new CAToolbarItem("ZoomOut24.gif"));
        addSeparator(new Dimension(15,15));
        add(this.newButton = new CAToolbarItem("StepForward24.gif"));
        addSeparator(new Dimension(10,10));
        add(this.newButton = new CAToolbarItem("Run24.gif"));
        addSeparator(new Dimension(5,5));
        add(this.newButton = new CAToolbarItem("Stop24.gif"));
        addSeparator(new Dimension(15,15));
        add(this.slider = new JSlider());
        this.slider.setPaintTicks(true);
        this.slider.setMajorTickSpacing(10);


        // ToDo put this logic into the controller
        this.addPropertyChangeListener(evt -> {
            String propName = evt.getPropertyName();
            if ("orientation".equals(propName)) {
                Integer newValue = (Integer) evt.getNewValue();
                if (newValue.intValue() == JToolBar.HORIZONTAL) {
                    this.slider.setOrientation(JSlider.HORIZONTAL);
                } else {
                    this.slider.setOrientation(JSlider.VERTICAL);
                }
            }
        });
        setBackground(Color.decode("0xFFD7B4"));
    }
}
