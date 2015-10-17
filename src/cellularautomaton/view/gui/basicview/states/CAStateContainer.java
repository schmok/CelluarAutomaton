package cellularautomaton.view.gui.basicview.states;

import cellularautomaton.view.gui.basicview.cells.CAStateCell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAStateContainer extends JPanel {

    public JPanel container;
    public ArrayList<CAStateCell> cells;

    public CAStateContainer() {
        super();
        this.container = new JPanel();
        add(this.container);
        this.cells = new ArrayList<>();
        this.container.setLayout(new BoxLayout(this.container, BoxLayout.Y_AXIS));
        setBackground(Color.decode("0xF7E6F0"));
    }

    public CAStateCell addCell() {
        CAStateCell cell = new CAStateCell(cells.size());
        this.cells.add(cell);
        this.container.add(cell);
        return cell;
    }

    public void removeCell(CAStateCell cell) {
        this.cells.remove(cell);
        this.container.remove(cell);
    }
}
