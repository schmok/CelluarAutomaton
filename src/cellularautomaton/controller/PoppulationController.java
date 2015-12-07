package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.model.Cell;
import cellularautomaton.view.gui.basicview.cells.CAStateCell;
import cellularautomaton.view.gui.basicview.states.CAPopulationContainer;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by vspadi on 08.11.15.
 */
public class PoppulationController extends AbstractController<CAPopulationContainer, CellularAutomatonController> implements MouseListener, MouseMotionListener{
    private Point startPosition;
    private Cell[][] tempCells;
    private int tempCellSize;
    private int tempCellState;
    private boolean dragging = false;


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
        if(this.tempCellState != -1) {
            this.getModel().pause();
        }
        dragging = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.tempCellState != -1) {
            if(SwingUtilities.isLeftMouseButton(e)) {
                this.getModel().setPopulation(tempCells);
            }
            this.getModel().resume();
        }
        if(!dragging && SwingUtilities.isRightMouseButton(e)) {
            getParent().getPopupController().showPopup(e);
        }
        dragging = false;
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
                if(end.y >= 0 && end.x >= 0 && end.y < this.getModel().getNumberOfRows() && end.y < this.getModel().getNumberOfColumns())
                    this.getModel().setState(end.y, end.x, this.tempCellState);
                dragging = true;
            } else if(SwingUtilities.isLeftMouseButton(e)) {
                tempCells = this.getModel().getPopulation();
                // Vorschau anzeigen
                boolean bX = end.x < this.startPosition.x;
                boolean bY = end.y < this.startPosition.y;
                int sX = (bX)?-1:1;
                int sY = (bY)?-1:1;

                for(int x = this.startPosition.x; ((bX)?x > end.y: x < end.x); x+=sX) {
                    for(int y = this.startPosition.y; ((bY)?y > end.y: y < end.y); y+=sY) {
                        if(x >= 0 && y >= 0 && y < this.getModel().getNumberOfRows() && x < this.getModel().getNumberOfColumns())
                            tempCells[y][x] = new Cell(this.tempCellState);
                    }
                }
                
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
