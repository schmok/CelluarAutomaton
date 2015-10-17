package cellularautomaton.view.gui.basicview.cells;

import cellularautomaton.view.util.ColorGenerator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAStateCell extends JPanel {
    private JButton stateButton;
    private JButton colorButton;


    public CAStateCell(int counter) {
        super();
        this.stateButton = new JButton();
        this.colorButton = new JButton();

        // Componentstyle
        setBackground(Color.decode("0xF7E6F0"));
        setAlignmentY(Component.TOP_ALIGNMENT);
        setBorder(new EmptyBorder(0,0,0,5));

        // Style of inner components
        this.stateButton.setText(String.valueOf(counter));
        this.colorButton.setBackground(ColorGenerator.generateColor(counter));
        this.colorButton.setText(" ");



        // Add components
        add(this.stateButton);
        add(this.colorButton);
    }
}
