package cellularautomaton.view;

import cellularautomaton.controller.CellularAutomatonController;
import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;

import java.util.Observer;
import java.util.concurrent.Callable;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public abstract class AbstractAutomatonView implements Observer {
    // Attributes //////////////////////////////////////////////////////////////////////////////////////////////////////
    public StringController stringController;
    public Callable<Void> cTerminate;


    // Mehtods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public AbstractAutomatonView() {
        this.stringController = StringController.getInstance();
        this.init();
    }

    public abstract void open();
    public abstract void init();

    // Getter & Setter /////////////////////////////////////////////////////////////////////////////////////////////////

    // Eventbinding ////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     * Method for binding the terminate signal
     */
    public void bindTerminate(Callable<Void> func) {
        // ToDo - Messed up -< Change that
        this.cTerminate = func;
    }

    // Events //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void terminate() throws Exception {
        this.cTerminate.call();
    }
}
