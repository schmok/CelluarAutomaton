package cellularautomaton.view.gui.basicview.states;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.cells.CAStateCell;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAStateContainer extends JPanel implements IOwnEnumeration{
    private JPanel container;
    private HashMap<Integer, CAStateCell> cells;

    public JPanel getStateContainer() {
        return container;
    }

    public HashMap<Integer, CAStateCell> getCells() {
        return cells;
    }

    public CAStateContainer() {
        super();
        this.container = new JPanel();
        add(this.container);
        this.cells = new HashMap<>();
        this.container.setLayout(new BoxLayout(this.container, BoxLayout.Y_AXIS));
        setBackground(Color.decode("0xF7E6F0"));
    }

    public CAStateCell addCell() {
        CAStateCell cell = new CAStateCell(cells.size());
        this.cells.put(cells.size(), cell);
        this.container.add(cell);
        return cell;
    }

    public void removeCell(CAStateCell cell) {
        this.cells.remove(cell);
        this.container.remove(cell);
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.CA_STATECONTAINER;
    }
}
