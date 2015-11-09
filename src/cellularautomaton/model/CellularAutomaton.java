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
        this.setAutomaton(new GameOfLifeAutomaton(15, 15, true));
        this.automatonController = automatonController;
        this.automatonController.bindModel(this);
        System.out.println(this.automaton.getNumberOfStates());
    }

    /*
     * Method to start the Automaton
     */
    public void start() {
        this.automatonController.open();
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

    public Cell[][] getPopulation() {
        return this.automaton.getPopulation();
    }

    public void setPopulation(Cell[][] cells) {
        this.automaton.setPopulation(cells);
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
    }

    public Color getColor(int state) {
        return this.automaton.getColor(state);
    }

    public Color[] getColorMapping() {
        return this.automaton.getColorMapping();
    }

    public void changeColor(int state, Color newColor) {
        this.automaton.changeColor(state, newColor);
    }

    public void step() {
        this.automaton.setPopulation(this.automaton.calcNextGeneration());
    }

    public void nextGeneration() {
        this.automaton.setPopulation(this.automaton.calcNextGeneration());
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
    }
}
