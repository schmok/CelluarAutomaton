package cellularautomaton.view.gui.basicview.states;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.model.Automaton;
import cellularautomaton.model.Cell;
import cellularautomaton.model.GameOfLifeAutomaton;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAPopulationContainer extends JPanel implements IOwnEnumeration {
    private Cell[][] lastPopulation;
    private int cellSize = 10;
    private BufferedImage bufferA = null;
    private BufferedImage bufferB = null;
    private boolean turn = false;
    private Color[] colors;

    public Cell[][] getLastPopulation() {
        return lastPopulation;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        if(cellSize >= 1)
            this.cellSize = cellSize;
        this.fitPopulation();
        this.fillBuffer();

    }

    public Color[] getColors() {
        return colors;
    }



    public CAPopulationContainer() {
        super();
        setPopulationWindowSize(900, 900);
        fitPopulation();
        GameOfLifeAutomaton gol = new GameOfLifeAutomaton(100,100,true);

        gol.randomPopulation();
        this.setColorMapping(gol.getColorMapping());

        this.drawPopulation(gol.getPopulation());
        this.drawPopulation(gol.getPopulation());

        // Componentstyle
        this.setBackground(Color.decode("0xFFFFDC"));
    }

    private void fitPopulation() {
        int newSize = 5;
        if(this.lastPopulation != null)
            newSize = this.cellSize * this.lastPopulation.length;
        this.setPopulationWindowSize(newSize, newSize);
        this.bufferA = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
        this.bufferB = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
    }

    public void increaseCellSize() {
        this.cellSize = (int)((float)this.cellSize * 1.1)+1;
        this.fitPopulation();
        this.fillBuffer();
    }

    public void decreaseCellSize() {
        if(this.cellSize > 1)
            this.cellSize = (int)((float)this.cellSize / 1.1);
        this.fitPopulation();
        this.fillBuffer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage buff = getCurrentBuffer();
        if(buff != null) {
            g.drawImage(buff, 5,5, this);
        }
    }

    private void setPopulationWindowSize(int width, int height) {
        this.setBorder(new EmptyBorder(0,0,height,width));
        this.setSize(width+1, height+1);
    }

    private void fillBuffer() {
        BufferedImage buff = getNextBuffer();
        Graphics2D g2 = buff.createGraphics();
        boolean drawBorder = this.cellSize > 5;
        g2.setColor(Color.decode("0xFFFFDC"));
        if(drawBorder)
            g2.fillRect(0, 0, getWidth()+1, getHeight()+1);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        Automaton.iterator(this.lastPopulation, (cell, x, y) -> {
            g2.setColor(this.getColor(cell.getState()));
            g2.fillRect(x * this.cellSize, y * this.cellSize, this.cellSize, this.cellSize);
            if(drawBorder) {
                g2.setColor(Color.BLACK);
                g2.drawRect(x * this.cellSize, y * this.cellSize, this.cellSize, this.cellSize);
            }
            return cell;
        });
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, getWidth()+1, getHeight()+1);
        this.repaint();
    }

    public void drawPopulation(Cell[][] cells) {
        boolean firstPaint = this.lastPopulation == null;
        this.lastPopulation = cells;
        if(firstPaint)
            fitPopulation();
        this.fillBuffer();
    }

    private BufferedImage getCurrentBuffer() {
        return (turn)?this.bufferA:this.bufferB;
    }

    private BufferedImage getNextBuffer() {
        return (turn = !turn)?this.bufferA:this.bufferB;
    }

    private Color getColor(int state) {
        if(colors != null && colors.length > state && state >= 0)
            return this.colors[state];
        else
            return Color.BLACK;
    }

    private void setColorMapping(Color[] colors) {
        this.colors = colors;
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.CA_POPULATIONCONTAINER;
    }
}
