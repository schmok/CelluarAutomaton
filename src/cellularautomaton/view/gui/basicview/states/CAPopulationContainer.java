package cellularautomaton.view.gui.basicview.states;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.model.Automaton;
import cellularautomaton.model.Cell;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAPopulationContainer extends JPanel implements IOwnEnumeration {
    private JScrollPane scrollPane;
    private JViewport viewPort;
    private Point location;


    private Cell[][] lastPopulation;
    private int cellSize = 10;
    private Dimension virtualDimension;
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

    @Override
    public Dimension getPreferredSize() {
        return this.virtualDimension;
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        this.location = new Point();
        this.bufferA = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        this.bufferB = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        // Componentstyle
        this.setBackground(Color.decode("0xFFFFDC"));
    }

    public void fitPopulation() {
        int newSize = 5;
        if(this.lastPopulation != null)
            newSize = this.cellSize * this.lastPopulation.length;
        this.setPopulationWindowSize(newSize, newSize);
        /*
        this.bufferA = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
        this.bufferB = new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
        */
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
        this.virtualDimension = new Dimension(width+1, height+1);
        this.setSize(this.virtualDimension);
    }

    public void fillBuffer() {
        BufferedImage buff = getNextBuffer();
        Graphics2D g2 = buff.createGraphics();

        // adjust scrollpos
        this.viewPort = this.scrollPane.getViewport();


        boolean drawBorder = this.cellSize > 5;
        g2.setColor(Color.decode("0xFFFFDC"));
        if(drawBorder) {
            g2.fillRect(0, 0, getWidth()+1, getHeight()+1);
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        ///*
        long startTime = System.currentTimeMillis();
        for(int x = 0; x < this.lastPopulation.length; x++) {
            for(int y = 0; y < this.lastPopulation[0].length; y++) {
                Cell cell = this.lastPopulation[x][y];
            }
        }
        /*
        Automaton.iterator(this.lastPopulation, (cell, x, y) -> {
            //g2.setColor(this.getColor(cell.getState()));
            //g2.fillRect(x * this.cellSize, y * this.cellSize, this.cellSize, this.cellSize);
            return cell;
        });
        */
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        //System.out.printf("Drawtime: %d this would be %dfps\n",elapsedTime, 1000/((elapsedTime > 0)?elapsedTime:1));
        //*/

        if(drawBorder) {
            g2.setColor(Color.BLACK);
            for(int x = 0; x < this.lastPopulation.length+1; x++) {
                g2.drawLine(x*this.cellSize,0,x*this.cellSize,this.lastPopulation[0].length*this.cellSize);
            }
            for(int y = 0; y < this.lastPopulation[0].length+1; y++) {
                g2.drawLine(0,y*this.cellSize,this.lastPopulation[0].length*this.cellSize,y*this.cellSize);
            }
        }
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

    public void setColorMapping(Color[] colors) {
        this.colors = colors;
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.CA_POPULATIONCONTAINER;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    @Override
    public void setLocation(int x, int y) {
        this.location.setLocation(x, y);
    }

    @Override
    public Point getLocation() {
        return this.location.getLocation();
    }
}
