package cellularautomaton.view;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;

import java.util.Observer;
import java.util.concurrent.Callable;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public abstract class AbstractAutomatonView implements Observer {
    // Attributes //////////////////////////////////////////////////////////////////////////////////////////////////////
    private StringController stringController;
    public Callable<Void> cTerminate;


    // Mehtods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public AbstractAutomatonView(StringController stringController) {
        this.stringController = stringController;
        this.init();
    }

    public abstract void open();
    public abstract void init();

    // Getter & Setter /////////////////////////////////////////////////////////////////////////////////////////////////
    public StringController getStringController() {
        return this.stringController;
    }

    /*
    * Method for getting the strings a bit easier
    */
    public String getString(StringEnumeration text){
        return this.stringController.get(text);
    }

    // Eventbinding ////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     * Method for binding the terminate signal
     */
    public void bindTerminate(Callable<Void> func) {
        this.cTerminate = func;
    }

    // Events //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void terminate() throws Exception {
        this.cTerminate.call();
    }
}
