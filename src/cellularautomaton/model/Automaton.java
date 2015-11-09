package cellularautomaton.model;

/**
 * Created by Viktor Spadi on 18.10.2015.
 */
import cellularautomaton.event.AutomatonEventEnum;

import java.util.Observable;

import java.awt.Color;

public abstract class Automaton extends Observable {
    private Cell[][] cells;
    private Color[] colors;
    private int numberOfStates;
    private boolean isTorus;
    private boolean isMooreNeighborHood;

    /**
     * Konstruktor
     *
     * @param rows
     * Anzahl an Reihen
     * @param columns
     * Anzahl an Spalten
     * @param numberOfStates
     * Anzahl an Zust�nden; die Zust�nde des Automaten sind dann die
     * Werte 0 bis numberOfStates-1
     * @param isMooreNeighborHood
     * true, falls der Automat die Moore-Nachbarschaft benutzt;
     * falls, falls der Automat die von-Neumann-Nachbarschaft benutzt
     * @param isTorus
     * true, falls die Zellen als Torus betrachtet werden
     */
    public Automaton(int rows, int columns, int numberOfStates,
                     boolean isMooreNeighborHood, boolean isTorus) {
        this.numberOfStates = numberOfStates;
        this.cells = new Cell[rows][columns];
        iterator(this.cells, (cell, row, col) -> new Cell());
        this.isTorus = isTorus;
        this.isMooreNeighborHood = isMooreNeighborHood;
        defineColors();
    }
    /**
     * Implementierung der Transformationsregel
     *
     * @param cell
     * die betroffene Zelle (darf nicht ver�ndert werden!!!)
     * @param neighbors
     * die Nachbarn der betroffenen Zelle (d�rfen nicht ver�ndert
     * werden!!!)
     * @return eine neu erzeugte Zelle, die gem�� der Transformationsregel aus
     * der betroffenen Zelle hervorgeht
     */
    protected abstract Cell transform(Cell cell, Cell[] neighbors);
    /**
     * Liefert die Anzahl an Zust�nden des Automaten; g�ltige Zust�nde sind
     * int-Werte zwischen 0 und Anzahl-1
     *
     * @return die Anzahl an Zust�nden des Automaten
     */
    public int getNumberOfStates() {
        return this.numberOfStates;
    }
    /**
     * Liefert die Anzahl an Reihen
     *
     * @return die Anzahl an Reihen
     */
    public int getNumberOfRows() {
        return this.cells.length;
    }
    /**
     * Liefert die Anzahl an Spalten
     *
     * @return die Anzahl an Spalten
     */
    public int getNumberOfColumns() {
        return this.cells[0].length;
    }
    /**
     * �ndert die Gr��e des Automaten; Achtung: aktuelle Belegungen nicht
     * gel�schter Zellen sollen beibehalten werden; neue Zellen sollen im
     * Zustand 0 erzeugt werden
     *
     * @param rows
     * die neue Anzahl an Reihen
     * @param columns
     * die neue Anzahl an Spalten
     */
    public void setSize(int rows, int columns) {
        if(rows == this.getNumberOfRows() && columns == this.getNumberOfColumns())
            return;

        Cell[][] old = this.cells.clone();
        this.cells = new Cell[rows][columns];

        int oR = old.length, oC = old[0].length;
        int nR = (oR < rows)?oR:rows;
        int nC = (oC < columns)?oC:columns;

        iterator(this.cells, (cell, row, col) -> {
            if(row < nR && col < nC)
                return old[row][col];
            else
                return new Cell();
        });
        defineColors();
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.SIZE_CHANGED);
    }
    /**
     * �ndert die Anzahl an Reihen des Automaten
     *
     * @param rows
     * die neue Anzahl an Reihen
     */
    public void setNumberOfRows(int rows) {
        this.setSize(rows, this.cells[0].length);
    }

    /**
     * �ndert die Anzahl an Spalten des Automaten
     *
     * @param columns
     * die neue Anzahl an Spalten
     */
    public void setNumberOfColumns(int columns) {
        this.setSize(this.cells.length, columns);
    }
    /**
     * Liefert Informationen, ob der Automat als Torus betrachtet wird
     *
     * @return true, falls der Automat als Torus betrachtet wird; falls sonst
     */
    public boolean isTorus() {
        return this.isTorus;
    }
    /**
     * �ndert die Torus-Eigenschaft des Automaten
     *
     * @param isTorus
     * true, falls der Automat als Torus betrachtet wird; falls sonst
     */
    public void setTorus(boolean isTorus) {
        this.isTorus = isTorus;
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.TORUS_CHANGED);
    }
    /**
     * Liefert Informationen �ber die Nachbarschaft-Eigenschaft des Automaten
     * (Hinweis: Die Nachbarschaftseigenschaft kann nicht ver�ndert werden)
     *
     * @return true, falls der Automat die Moore-Nachbarschaft ber�cksicht;
     * false, falls er die von-Neumann-Nachbarschaft ber�cksichtigt
     */
    public boolean isMooreNeighborHood() {
        return this.isMooreNeighborHood;
    }
    /**
     * Liefert die Population als Cell-Matrix
     *
     * @return die Population als Cell-Matrix
     */
    public Cell[][] getPopulation() {
        return this.cells;
    }
    /**
     * �ndert die Population
     *
     * @param cells
     * eine neue Population des Automaten
     */
    public void setPopulation(Cell[][] cells) {
        this.cells = cells;
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
    }
    /**
     * setzt alle Zellen in den Zustand 0
     */
    public void clearPopulation() {
        iterator(this.cells, (cell, row, col) -> new Cell());
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
    }

    public void drawCells() {
        for(int r = 0; r < this.getNumberOfRows(); r++) {
            for (int c = 0; c < this.getNumberOfColumns(); c++) {
                System.out.print(this.cells[r][c].getState());
            }
            System.out.println("");
        }
    }
    /**
     * setzt f�r jede Zelle einen zuf�llig erzeugten Zustand
     */
    public void randomPopulation() {
        iterator(this.cells, (cell, row, col) -> new Cell((int)(Math.random() * this.numberOfStates)));
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
    }

    /**
     * Interface zum Iterieren durch alle Zellen
     */
    public interface CellIterator{
        Cell interate(Cell cell, int row, int col);
    }

    public static void iterator(Cell[][] cells, CellIterator ci) {
        for(int r = 0; r < cells.length; r++)
            for (int c = 0; c < cells[0].length; c++)
               cells[r][c] = ci.interate(cells[r][c], r, c);
    }

    /**
     * �ndert den Zustand einer Zelle
     *
     * @param row
     * Reihe der Zelle
     * @param column
     * Spalte der Zelle
     * @param state
     * neuer Zustand der Zelle
     */
    public void setState(int row, int column, int state) {
        this.cells[row][column].setState(state);
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
    }
    /**
     * Liefert eine Zelle des Automaten
     *
     * @param row
     * Reihe der Zelle
     * @param column
     * Spalte der Zelle
     * @return Cell-Objekt an Position row/column
     */
    public Cell getCell(int row, int column) {
        int rMax = this.getNumberOfRows(), cMax = this.getNumberOfColumns();
        if(this.isTorus) {
            return this.cells[torusMod(row, rMax)][torusMod(column, cMax)];
        } else if(row < rMax && row >= 0 && column < cMax && column >= 0) {
            return this.cells[row][column];
        }
        return null;
    }

    private int torusMod(int val, int max) {
        return ((val > 0)?val:max+val) % max;
    }
    /**
     * definiert die Farbrepr�sentation der einzelnen Zust�nde; implementiert
     * wie folgt: <br>
     * Anzahl der Zust�nde = 2: 0 = wei�; 1 = schwarz <br>
     * Anzahl der Zust�nde = 3: 0 = wei�; 1 = grau; 2 = schwarz <br>
     * Ansonsten: Farbzuordnung per Zufall
     */
    protected void defineColors() {
        switch(this.numberOfStates) {
            case 2:
                this.colors = new Color[] {Color.WHITE, Color.BLACK};
                break;
            case 3:
                this.colors = new Color[] {Color.WHITE, Color.GRAY, Color.BLACK};
                break;
            default:
                this.colors = new Color[this.numberOfStates];
                for(int i = 0; i <this.numberOfStates; i++)
                    this.colors[i] = Color.getHSBColor( (float)(((Math.random() * 2) * 180) % 360),
                            (float)Math.random() / 2.0f + 0.5f, (float)Math.random() / 2.0f + 0.5f);
        }
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.COLOR_CHANGED);
    }
    /**
     * Liefert die Farbrepr�sentation eines Zustandes
     *
     * @param state
     * der Zustand, dessen Farbrepr�sentation geliefert werden soll
     * @return die Farbrepr�sentation des Zustandes state
     */
    public Color getColor(int state) {
        if(state >= 0 && state < this.numberOfStates)
            return this.colors[state];
        return null;
    }
    /**
     * Liefert die Farbrepr�sentation alles Zust�nde des Automaten
     *
     * @return die Farbrepr�sentation alles Zust�nde des Automaten
     */
    public Color[] getColorMapping() {
        return this.colors;
    }
    /**
     * �ndert die Farbrepr�sentation eines Zustandes
     *
     * @param state
     * der Zustand
     * @param newColor
     * die neue Farbrepr�sentation des Zustandes state
     */
    public void changeColor(int state, Color newColor) {
        if(state >= 0 && state < this.numberOfStates)
            this.colors[state] = newColor;
    }
    /**
     * Berechnet und liefert die n�chste Generation; ruft dabei die abstrakte
     * Methode "transform" f�r alle Zellen auf; Hinweis: zu ber�cksichtigen sind
     * die Nachbarschaftseigenschaft und die Torus-Eigenschaft des Automaten
     *
     * @return
     */
    public Cell[][] calcNextGeneration() {
        Cell[][] newCells = new Cell[this.getNumberOfRows()][this.getNumberOfColumns()];
        int mod = (this.isMooreNeighborHood)?-1:0;
        iterator(newCells, (cell, row, col) -> {
            Cell[] neighbors = new Cell[(mod == -1)?8:4];
            int ctr = 0;
            for(int i = 0; i < 9; i++)
                if(i % 2 > mod && i != 4) {
                    int y = i % 3, x = (i / 3);
                    neighbors[ctr++] = this.getCell((row-1)+x, (col-1)+y);
                }
            return transform(getCell(row,col), neighbors);
        });
        this.setChanged();
        this.notifyObservers(AutomatonEventEnum.CELL_CHANGED);
        return newCells;
    }
}