package cellularautomaton.view.gui.basicview.cells;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.components.CAJButton;
import cellularautomaton.view.gui.basicview.components.CAJToggleButton;
import cellularautomaton.view.util.ColorGenerator;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAStateCell extends JPanel implements IOwnEnumeration {
    private CAJToggleButton stateButton;
    private CAJButton colorButton;
    private int state;


    public JToggleButton getStateButton() {
        return stateButton;
    }

    public JButton getColorButton() {
        return colorButton;
    }

    public int getState() {
        return this.state;
    }

    public CAStateCell(int counter, Color color) {
        super();
        this.stateButton = new CAJToggleButton(null,StringEnumeration.SI_STATE);
        this.colorButton = new CAJButton(StringEnumeration.SI_COLOR);

        // Componentstyle
        setBackground(Color.decode("0xF7E6F0"));
        setAlignmentY(Component.TOP_ALIGNMENT);
        setBorder(new EmptyBorder(0,0,0,5));

        // Style of inner components
        this.state = counter;
        this.stateButton.setText(String.valueOf(counter));
        this.colorButton.setBackground(color);
        this.colorButton.setOpaque(true);
        this.colorButton.setBorderPainted(false);
        this.colorButton.setText(" ");
        this.stateButton.setPreferredSize(new Dimension(50,25));
        this.colorButton.setPreferredSize(new Dimension(50,25));
        // Add components

        add(this.stateButton);
        add(this.colorButton);

    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.CA_CELL;
    }
}
