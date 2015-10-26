package cellularautomaton.gol;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Viktor Spadi on 18.10.2015.
 */


public class Main extends JPanel {
    private JFrame frame;
    private JToolBar toolbar;
    private JToolBar instrumentsBar;
    public GameOfLifeAutomaton gol;
    public int stepSize = 100;
    private JButton startButton;
    private boolean running = false;
    private Runner r;

    public Main() {
        super();
        this.gol = new GameOfLifeAutomaton(30,30,true);
        createFrame();
        this.r = new Runner();
        this.r.start(this);
    }

    private void createFrame(){
        this.frame = new JFrame("Game of Life Test");
        this.frame.setLayout(new BorderLayout());
        this.toolbar = new JToolBar();
        this.instrumentsBar = generateInstruments();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(600,500);
        JButton randPopButton = new JButton("Random Popoulation");
        randPopButton.addActionListener(e -> populateRandom());
        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(e->simulateStep());
        this.startButton = new JButton("Start");
        this.startButton.addActionListener(e->toggleSimulation());
        JSlider speedSlider = new JSlider();
        speedSlider.addChangeListener(e -> changeStep(e));
        speedSlider.setMinimum(1);

        this.toolbar.add(randPopButton);
        this.toolbar.add(stepButton);
        this.toolbar.add(this.startButton);
        this.toolbar.add(speedSlider);

        this.frame.add(toolbar, BorderLayout.PAGE_START);
        this.frame.add(this.instrumentsBar, BorderLayout.PAGE_END);
        this.frame.add(this, BorderLayout.CENTER);
        this.frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                repaint();
            }
        });
        //this.frame.pack();
        this.frame.setVisible(true);
    }

    public JToolBar generateInstruments() {
        JToolBar tb = new JToolBar();
        // blinker
        JButton blinkerBtn = new JButton("Blinker");
        blinkerBtn.addActionListener(e -> {
            populator(20, 20, new int[][]{

                    {0, 1, 1, 1, 1},
                    {1, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0}

                    /*
                    {1,1,1},
                    {1,0,1},
                    {1,0,1},
                    {0,0,0},
                    {1,0,1},
                    {1,0,1},
                    {1,1,1}
*/
            });
            //this.gol.drawCells();
            repaint();
        });
        tb.add(blinkerBtn);

        return tb;
    }

    public void populateRandom() {
        gol.randomPopulation();
        repaint();
    }

    public void simulateStep() {
        gol.setPopulation(gol.calcNextGeneration());
        repaint();
    }

    public void changeStep(ChangeEvent e) {
        this.stepSize = ((JSlider)e.getSource()).getValue();
    }

    public void toggleSimulation() {
        this.running = !this.running;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Cell[][] cp = gol.getPopulation();
        int smaller = (getWidth() < getHeight())?getWidth():getHeight() / gol.getNumberOfRows();


        gol.iterator(cp, (cell, row, col) -> {
            g.setColor(gol.getColor(cell.getState()));
            g.fillRect(col * smaller, row * smaller, smaller, smaller);
            return cell;
        });
    }

    public static void main(String[] args) {
        Main window = new Main();
    }


    public class Runner extends Thread{
        public void start(Main inst) {
            boolean runnable = true;
            while(runnable) {
                try {
                    Thread.sleep(inst.stepSize);
                    if(inst.running) {
                        inst.gol.setPopulation(inst.gol.calcNextGeneration());
                        inst.repaint();
                    }
                } catch (InterruptedException e) {
                    runnable = false;
                }
            }

        }
    }


    public void populator(int xPos, int yPos, int[][] states) {
        for(int x = 0; x < states.length; x++) {
            for(int y = 0; y < states[0].length; y++) {
                this.gol.setState(xPos + x, yPos + y, states[x][y]);
            }
        }
    }
}
