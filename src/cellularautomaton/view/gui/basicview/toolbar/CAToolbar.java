package cellularautomaton.view.gui.basicview.toolbar;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.components.CAJToggleButton;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Viktor Spadi on 17.10.2015.
 *
 * http://www.java2s.com/Code/Java/Swing-JFC/DeterminingWhenaFloatableJToolBarContainerChangesOrientation.htm
 */
public class CAToolbar extends JToolBar implements IOwnEnumeration {
    StringController stringController;
    private CAToolbarItem newButton;
    private CAToolbarItem openButton;
    private CAToolbarItem gridButton;
    private CAToolbarItem deleteButton;
    private CAToolbarItem randomButton;
    private CAJToggleButton torusButton;
    private CAToolbarItem printButton;
    private CAToolbarItem zoomInButton;
    private CAToolbarItem zoomOutButton;
    private CAToolbarItem stepButton;
    private CAToolbarItem simulateButton;
    private CAToolbarItem stopButton;
    private JSlider slider;

    public StringController getStringController() {
        return stringController;
    }

    public CAToolbarItem getNewButton() {
        return newButton;
    }

    public CAToolbarItem getOpenButton() {
        return openButton;
    }

    public CAToolbarItem getGridButton() {
        return gridButton;
    }

    public CAToolbarItem getDeleteButton() {
        return deleteButton;
    }

    public CAToolbarItem getRandomButton() {
        return randomButton;
    }

    public CAJToggleButton getTorusButton() {
        return torusButton;
    }

    public CAToolbarItem getPrintButton() {
        return printButton;
    }

    public CAToolbarItem getZoomInButton() {
        return zoomInButton;
    }

    public CAToolbarItem getZoomOutButton() {
        return zoomOutButton;
    }

    public CAToolbarItem getStepButton() {
        return stepButton;
    }

    public CAToolbarItem getSimulateButton() {
        return simulateButton;
    }

    public CAToolbarItem getStopButton() {
        return stopButton;
    }

    public JSlider getSlider() {
        return slider;
    }

    public CAToolbar(StringController stringController) {
        super(stringController.get(StringEnumeration.TB_NAME));

        // ToDo remove hardcoded icons
        add(this.newButton = new CAToolbarItem("New24.gif", StringEnumeration.MI_NEW));
        addSeparator(new Dimension(5,5));
        add(this.openButton = new CAToolbarItem("Open24.gif", StringEnumeration.MS_LOAD));
        addSeparator(new Dimension(15,15));
        add(this.gridButton = new CAToolbarItem("Size24.gif", StringEnumeration.MI_CHANGE_SIZE));
        addSeparator(new Dimension(5,5));
        add(this.deleteButton = new CAToolbarItem("Delete24.gif", StringEnumeration.MI_DELETE));
        addSeparator(new Dimension(5,5));
        add(this.randomButton = new CAToolbarItem("Random24.gif", StringEnumeration.MI_RANDOM));
        addSeparator(new Dimension(5,5));
        add(this.torusButton = new CAJToggleButton("Torus24.gif", StringEnumeration.MI_TORUS));
        addSeparator(new Dimension(5,5));
        add(this.printButton = new CAToolbarItem("Print24.gif", StringEnumeration.MI_PRINT));
        addSeparator(new Dimension(15,15));
        add(this.zoomInButton = new CAToolbarItem("ZoomIn24.gif", StringEnumeration.MI_ZOOM_IN));
        addSeparator(new Dimension(5,5));
        add(this.zoomOutButton = new CAToolbarItem("ZoomOut24.gif", StringEnumeration.MI_ZOOM_OUT));
        addSeparator(new Dimension(15,15));
        add(this.stepButton = new CAToolbarItem("StepForward24.gif", StringEnumeration.MI_STEP));
        addSeparator(new Dimension(10,10));
        add(this.simulateButton = new CAToolbarItem("Run24.gif", StringEnumeration.MI_START));
        addSeparator(new Dimension(5,5));
        add(this.stopButton = new CAToolbarItem("Stop24.gif", StringEnumeration.MI_STOP));
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

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.CA_TOOLBAR;
    }

    public void updateSimulationState(boolean running) {
        if(running) {
            this.stepButton.setEnabled(false);
            this.simulateButton.setEnabled(false);
            this.stopButton.setEnabled(true);
        } else {
            this.stepButton.setEnabled(true);
            this.simulateButton.setEnabled(true);
            this.stopButton.setEnabled(false);
        }
    }
}
