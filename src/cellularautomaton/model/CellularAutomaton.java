package cellularautomaton.model;

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
    private Runner r;
    private int interVal;
    // Methods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setAutomaton(Automaton automaton) {
        this.automaton = automaton;
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.NEW_AUTOMATON);
    }
    /*
     * Constructor
     * @param automatonController
     */
    public CellularAutomaton(CellularAutomatonController automatonController) {
        this.setAutomaton(new GameOfLifeAutomaton(900 , 900, true));
        this.automatonController = automatonController;
        this.automatonController.bindModel(this);
        System.out.println(this.automaton.getNumberOfStates());
    }

    /*
     * Method to start the Automaton
     */
    public void start() {
        this.automatonController.open();
        this.r = new Runner();
        this.r.start(this);
    }

    public void terminate() {
        // ToDo later on "on close do you want to save?" logic
        System.exit(0);
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
        this.automaton.setSize(rows, columns);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.SIZE_CHANGED);
    }

    public void setNumberOfRows(int rows) {
        this.automaton.setNumberOfRows(rows);
    }

    public void setNumberOfColumns(int columns) {
        this.automaton.setNumberOfColumns(columns);
    }

    public boolean isTorus() {
        return this.automaton.isTorus();
    }

    public void setTorus(boolean isTorus) {
        this.automaton.setTorus(isTorus);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.TORUS_CHANGED);
    }

    public boolean isMooreNeighborHood() {
        return this.automaton.isMooreNeighborHood();
    }

    public int[] getPopulation() {
        return this.automaton.data;
    }

    public void setPopulation(Cell[][] cells) {
        this.automaton.setPopulation(cells);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
    }

    public void clearPopulation() {
        this.automaton.clearPopulation();
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
    }

    public void randomPopulation() {
        this.automaton.randomPopulation();
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
    }

    public void setState(int row, int column, int state) {
        this.automaton.setState(row, column, state);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
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
        //this.automaton.setPopulation(this.automaton.calcNextGeneration());
        this.automaton.calcNextGeneration();
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
    }

    public Cell[][] getPopulationCells() {
        return this.automaton.getPopulation();
    }

    public void setIsRunning(boolean running) {
        this.isRunning = running;
    }

    public void setInterVal(int interVal) {
        this.interVal = interVal;
    }

    public class Runner extends Thread{
        public void start(CellularAutomaton inst) {
            boolean runnable = true;
            while(runnable) {
                try {
                    sleep(inst.interVal*2);
                    if(inst.isRunning) {
                        inst.nextGeneration();
                    }
                } catch (InterruptedException e) {
                    runnable = false;
                }
            }

        }
    }
}
