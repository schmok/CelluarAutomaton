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
    private int[] colors;

    private Cell[][] lastPopulation;
    private int cellSize = 10;
    private Dimension virtualDimension;
    private BufferedImage buffer = null;
    private Graphics graphics;
    private boolean turn = false;

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
        if(this.cellSize > 1) {
            this.cellSize = (int)((float)this.cellSize / 1.1);
            if(this.cellSize < 1)
                this.cellSize = 1;
        }
        this.fitPopulation();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long startTime = System.currentTimeMillis();
        int[] pixels = ((DataBufferInt) this.buffer.getRaster().getDataBuffer()).getData();

        // adjust scrollpos
        this.viewPort.setSize(this.scrollPane.getSize());
        this.viewPort.setLocation(this.location);

        int oX = this.viewPort.getLocation().x;
        int oY = this.viewPort.getLocation().y;
        int mX = this.viewPort.width;
        int mY = this.viewPort.height;
        int maxX = this.lastPopulation.length;
        int maxY = this.lastPopulation[0].length;

        for(int x = 0; x < mX; x++) {
            for(int y = 0; y < mY; y++) {
                int i = x + (y* screenWidth);
                int rY = x - oX;
                int rX = y - oY;
                int cX = rX / this.cellSize;
                int cY = rY / this.cellSize;

                if(rX <= this.cellSize* maxX && rY <= this.cellSize * maxY) {
                    // draw lines
                    if (this.cellSize > 5 && (rX % this.cellSize == 0 | rY % this.cellSize == 0)) {
                        pixels[i] = 0x000000;
                    } else {
                        if(cX >= 0 && cY >= 0 && cX < this.lastPopulation.length && cY < this.lastPopulation[0].length)
                            pixels[i] = this.colors[this.lastPopulation[cX][cY].getState()];
                    }
                } else {
                    // outside!
                    pixels[i] = 0xFFFFDC;
                }
            }
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        g.drawImage(this.buffer, 5,5, this);
        System.out.printf("Drawtime: %d this would be %dfps\n",elapsedTime, 1000/((elapsedTime > 0)?elapsedTime:1));
    }

    private void setPopulationWindowSize(int width, int height) {
        this.setBorder(new EmptyBorder(0,0,height,width));
        this.virtualDimension = new Dimension(width+10, height+10);
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


    public void setColorMapping(Color[] colors) {
        this.colors = new int[colors.length];
        for(int i = 0; i< colors.length; i++) {
            this.colors[i] =    colors[i].getRed() << 16 |
                                colors[i].getGreen() << 8 |
                                colors[i].getBlue();

        }
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
