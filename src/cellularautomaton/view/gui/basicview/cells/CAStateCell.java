package cellularautomaton.view.gui.basicview.cells;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.ColorGenerator;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAStateCell extends JPanel implements IOwnEnumeration {
    private JToggleButton stateButton;
    private JButton colorButton;
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

    public CAStateCell(int counter) {
        super();
        this.stateButton = new JToggleButton();
        this.colorButton = new JButton();

        // Componentstyle
        setBackground(Color.decode("0xF7E6F0"));
        setAlignmentY(Component.TOP_ALIGNMENT);
        setBorder(new EmptyBorder(0,0,0,5));

        // Style of inner components
        this.stateButton.setText(String.valueOf(counter));
        this.colorButton.setBackground(ColorGenerator.generateColor(counter));
        this.colorButton.setOpaque(true);
        this.colorButton.setBorderPainted(false);
        this.colorButton.setText(" ");

        // Add components
        add(this.stateButton);
        add(this.colorButton);
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.CA_CELL;
    }
}
