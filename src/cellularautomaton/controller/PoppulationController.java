package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.model.Cell;
import cellularautomaton.view.gui.basicview.cells.CAStateCell;
import cellularautomaton.view.gui.basicview.states.CAPopulationContainer;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by vspadi on 08.11.15.
 */
public class PoppulationController extends AbstractController<CAPopulationContainer, CellularAutomatonController> implements MouseListener, MouseMotionListener{
    private Point startPosition;
    private int tempCellSize;
    private int tempCellState;


    PoppulationController(CAPopulationContainer view, CellularAutomatonController cac) {
        super(view, cac);
        this.getView().addMouseListener(this);
        this.getView().addMouseMotionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {
            default:
                System.out.println("PoppulationAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.tempCellSize = this.getParent().getView().getPopulationContainer().getCellSize();
        this.startPosition = new Point(e.getX()-5, e.getY()-5);
        this.startPosition = div(this.startPosition, this.tempCellSize);
        CAStateCell cell = this.getParent().getStateController().getActiveCell();
        this.tempCellState = (cell == null)?-1:cell.getState();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(this.tempCellState != -1) {
            Point end = new Point(e.getX()-5, e.getY()-5);
            end = div(end, this.tempCellSize);
            if(SwingUtilities.isRightMouseButton(e)) {
                if(end.x >= 0 && end.y >= 0 && end.x < this.getModel().getNumberOfRows() && end.y < this.getModel().getNumberOfColumns())
                    this.getModel().setState(end.x, end.y, this.tempCellState);
            } else if(SwingUtilities.isLeftMouseButton(e)) {
                Cell[][] cells = this.getModel().getPopulation();
                // Vorschau anzeigen
                boolean bX = end.x < this.startPosition.x;
                boolean bY = end.y < this.startPosition.y;
                int sX = (bX)?-1:1;
                int sY = (bY)?-1:1;

                for(int x = this.startPosition.x; ((bX)?x > end.x: x < end.x); x+=sX) {
                    for(int y = this.startPosition.y; ((bY)?y > end.y: y < end.y); y+=sY) {
                        if(x >= 0 && y >= 0 && x < this.getModel().getNumberOfRows() && y < this.getModel().getNumberOfColumns())
                            cells[x][y] = new Cell(this.tempCellState);
                    }
                }
                this.getModel().setPopulation(cells);
            }
        }

        // for dragging
        if(SwingUtilities.isMiddleMouseButton(e)) {
            //System.out.println("Middle");
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private Point sub(Point a, Point b) {
        return new Point(a.x - b.x, a.y - b.y);
    }

    private Point add(Point a, Point b) {
        return new Point(a.x + b.x, a.y + b.y);
    }

    private Point div(Point a, int val) {
        return new Point(a.x / val, a.y / val);
    }
}
