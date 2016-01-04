package cellularautomaton.model;

import cellularautomaton.app.Main;
import cellularautomaton.controller.CellularAutomatonController;
import cellularautomaton.event.AutomatonEventEnum;

import java.awt.*;
import java.util.Observable;

/**
 * Created by Viktor Spadi on 14.10.2015.
 *
 * This is the main class of the cellular automaton
 */
public class CellularAutomaton extends Observable {
    // Attriubues //////////////////////////////////////////////////////////////////////////////////////////////////////
    private CellularAutomatonController automatonController;
    private Automaton automaton;
    private boolean isRunning;
    private int simInterval;
    private AutomatonRunner runner;
    private String sourceCode;
    // Methods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setAutomaton(Automaton automaton) {
        this.automaton = automaton;
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.COLOR_CHANGED);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.SIZE_CHANGED);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
    }
    /*
     * Constructor
     * @param automatonController
     */
    public CellularAutomaton(CellularAutomatonController automatonController, Automaton automaton) {
        this.setAutomaton(automaton);
        this.automatonController = automatonController;
        this.automatonController.bindModel(this);
        this.isRunning = false;
        this.simInterval = 50;
        this.sourceCode = "";
    }

    /*
     * Method to start the Automaton
     */
    public void start() {
        this.automatonController.open();
        this.runner = new AutomatonRunner(this);
        this.runner.start();
    }
    public void setSourceCode(String code) {
        this.sourceCode = code;
    }

    public String getSourceCode() {
        return this.sourceCode;
    }

    public void terminate(boolean all, CellularAutomatonController cac) {
        // ToDo later on "on close do you want to save?" logic
        if(all) {
            System.exit(0);
        } else if(cac != null) {
            /*
            Main.instanceCount--;
            cac.getView().getFrame().dispose();
            if(Main.instanceCount == 0) {
                System.exit(0);
            }
            */
            Main.closeWindow(cac);
        }
    }

    public void pause() {
        this.runner.sleep();
    }

    public void resume() {
        this.runner.wake();
    }

    public int getSimInterval() {
        return this.simInterval;
    }

    public void setSimInterval(int simInterval) {
        pause();
        this.simInterval = simInterval;
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.INTERVAL_CHANGED);
        resume();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.SIMSTATE_CHANGED);
    }

    // Automaton methods

    public int getNumberOfStates() {
        return this.automaton.getNumberOfStates();
    }

    public int getNumberOfRows() {
        return this.automaton.getNumberOfRows();
    }

    public int getNumberOfColumns() {
        return this.automaton.getNumberOfColumns();
    }

    public void setSize(int rows, int columns) {
        pause();
        this.automaton.setSize(rows, columns);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.SIZE_CHANGED);
        resume();
    }

    public void setNumberOfRows(int rows) {
        pause();
        this.automaton.setNumberOfRows(rows);
        resume();
    }

    public void setNumberOfColumns(int columns) {
        pause();
        this.automaton.setNumberOfColumns(columns);
        resume();
    }

    public boolean isTorus() {
        return this.automaton.isTorus();
    }

    public void setTorus(boolean isTorus) {
        pause();
        this.automaton.setTorus(isTorus);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.TORUS_CHANGED);
        resume();
    }

    public boolean isMooreNeighborHood() {
        return this.automaton.isMooreNeighborHood();
    }

    public Cell[][] getPopulation() {
        return this.automaton.getPopulation();
    }

    public void setPopulation(Cell[][] cells) {
        pause();
        this.automaton.setPopulation(cells);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
        resume();
    }

    public void clearPopulation() {
        pause();
        this.automaton.clearPopulation();
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
        resume();
    }

    public void randomPopulation() {
        pause();
        this.automaton.randomPopulation();
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
        resume();
    }

    public void setState(int row, int column, int state) {
        pause();
        this.automaton.setState(row, column, state);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
        resume();
    }

    public Color getColor(int state) {
        return this.automaton.getColor(state);
    }

    public Color[] getColorMapping() {
        return this.automaton.getColorMapping();
    }

    public void changeColor(int state, Color newColor) {
        this.automaton.changeColor(state, newColor);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.COLOR_CHANGED);
    }

    public void nextGeneration() {
        long startTime = System.currentTimeMillis();
        this.automaton.setPopulation(this.automaton.calcNextGeneration());
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        //System.out.printf("Simtime: %d this would be %dfps\n",elapsedTime, 1000/((elapsedTime>0)?elapsedTime:1));
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
    }

    public Automaton getAutomaton() {
        return automaton;
    }

    class AutomatonRunner extends Thread {
        private CellularAutomaton automaton;
        private final Object MONITOR = new Object();
        private boolean yield = false;
        private int waitQueue = 0;

        public AutomatonRunner(CellularAutomaton automaton) {
            this.automaton = automaton;
        }

        @Override
        public void run() {
            simulate();
        }

        private void simulate() {
            while(true) {
                checkYield();
                try {
                    if(automaton.simInterval == 0)
                        Thread.sleep(1);
                    else
                        Thread.sleep(automaton.simInterval);
                    if(automaton.isRunning) {
                        automaton.nextGeneration();
                    }
                } catch(Exception e) {  }
            }
        }

        private void checkYield() {
            synchronized (MONITOR) {
                while(yield) {
                    try {
                        MONITOR.wait();
                    } catch (Exception e) {}
                }
            }
        }

        public void sleep() {
            this.yield = true;
            this.waitQueue++;
        }

        public void wake() {
            synchronized (MONITOR) {
                if(--this.waitQueue == 0) {
                    this.yield = false;
                    MONITOR.notify();
                }
            }
        }

    }
}
