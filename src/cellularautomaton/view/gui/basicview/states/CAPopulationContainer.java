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
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class CAPopulationContainer extends JPanel implements IOwnEnumeration {
    private JScrollPane scrollPane;
    private Rectangle viewPort;
    private Point location;
    private int screenWidth;
    private int screenHeight;

    private Cell[][] lastPopulation;
    private int cellSize = 10;
    private Dimension virtualDimension;
    private BufferedImage buffer = null;
    private Graphics graphics;
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
        this.repaint();
    }

    public Color[] getColors() {
        return colors;
    }



    public CAPopulationContainer() {
        super();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = (int)screenSize.getWidth();
        this.screenHeight = (int)screenSize.getHeight();
        this.location = new Point();
        this.viewPort = new Rectangle();
        this.buffer = new BufferedImage(this.screenWidth,this.screenHeight,BufferedImage.TYPE_INT_RGB);
        this.graphics = this.buffer.createGraphics();
        // Componentstyle
        this.setBackground(Color.decode("0xFFFFDC"));
    }

    public void fitPopulation() {
        int newSize = 5;
        if(this.lastPopulation != null)
            newSize = this.cellSize * this.lastPopulation.length;
        this.setPopulationWindowSize(newSize, newSize);
    }

    public void increaseCellSize() {
        this.cellSize = (int)((float)this.cellSize * 1.1)+1;
        this.fitPopulation();
        this.repaint();
    }

    public void decreaseCellSize() {
        if(this.cellSize > 1)
            this.cellSize = (int)((float)this.cellSize / 1.1);
        this.fitPopulation();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] pixels = ((DataBufferInt) this.buffer.getRaster().getDataBuffer()).getData();

        // adjust scrollpos
        this.viewPort.setSize(this.scrollPane.getSize());
        this.viewPort.setLocation(this.location);

        boolean drawBorder = this.cellSize > 5;
        this.graphics.setColor(Color.decode("0xFFFFDC"));
        if(true) {
            this.graphics.fillRect(0, 0, this.screenWidth+1, this.screenHeight+1);
        }
        //this.graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.graphics.setColor(Color.BLACK);
        ///*
        long startTime = System.currentTimeMillis();
        /*for(int x = 0; x < this.lastPopulation.length; x++) {
            for(int y = 0; y < this.lastPopulation[0].length; y++) {
                Cell cell = this.lastPopulation[x][y];
            }
        }*/
        for(int x = 0; x < pixels.length; x++) {
            pixels[x] = 0x0000FF00;
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
        this.graphics.fillRect(this.location.getLocation().x,this.location.getLocation().y,250,250);
        if(drawBorder && true) {
            this.graphics.setColor(Color.BLACK);
            for(int x = 0; x < this.lastPopulation.length+1; x++) {
                this.graphics.drawLine(x*this.cellSize,0,x*this.cellSize,this.lastPopulation[0].length*this.cellSize);
            }
            for(int y = 0; y < this.lastPopulation[0].length+1; y++) {
                this.graphics.drawLine(0,y*this.cellSize,this.lastPopulation[0].length*this.cellSize,y*this.cellSize);
            }
        }
        this.graphics.setColor(Color.BLACK);

        if(this.buffer != null) {
            g.drawImage(this.buffer, 5,5, this);
        }
    }

    private void setPopulationWindowSize(int width, int height) {
        this.setBorder(new EmptyBorder(0,0,height,width));
        this.virtualDimension = new Dimension(width+1, height+1);
        this.setSize(this.virtualDimension);
    }

    public void fillBuffer() {


        //g2.drawRect(0, 0, getWidth()+1, getHeight()+1);
        this.repaint();
    }

    public void drawPopulation(Cell[][] cells) {
        boolean firstPaint = this.lastPopulation == null;
        this.lastPopulation = cells;
        if(firstPaint)
            fitPopulation();
        this.repaint();
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
        this.repaint();
        return this.location.getLocation();
    }
}
