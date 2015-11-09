package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.model.CellularAutomaton;
import cellularautomaton.view.AutomatonView;
import cellularautomaton.view.util.IOwnEnumeration;

import java.awt.event.*;

/**
 * Created by Viktor Spadi on 14.10.2015.
 *
 *
 */
public class CellularAutomatonController extends AbstractController<AutomatonView, Object> {
    // Attributes //////////////////////////////////////////////////////////////////////////////////////////////////////
    private MenuController menuController;
    private StateController stateController;
    private ToolbarController toolbarController;
    private PoppulationController poppulationController;

    // Methods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    * Constructor
    *
    * @param viewController     the ViewController to use
    *
    */
    public CellularAutomatonController(AutomatonView automatonView) {
        super(automatonView, null);
        this.menuController = new MenuController(getView().getMenuBar(), this);
        this.stateController = new StateController(getView().getStateContainer(), this);
        this.toolbarController = new ToolbarController(getView().getToolbar(), this);
        this.poppulationController = new PoppulationController(getView().getPopulationContainer(), this);
        bindEvents();
    }

    /*
     * Pass call to the view
     */
    public void open() {
        this.getView().open();
    }

    public void bindModel(CellularAutomaton cellularAutomaton) {
        super.bindModel(cellularAutomaton);
        this.menuController.bindModel(cellularAutomaton);
        this.stateController.bindModel(cellularAutomaton);
        this.toolbarController.bindModel(cellularAutomaton);
        this.poppulationController.bindModel(cellularAutomaton);

        // observer binding
        this.getModel().addObserver(this.getView());

        // additional suff like first cell obtain
        this.stateController.bindStates();
    }

    public void bindEvents() {
        // Terminate
        this.getView().getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                getModel().terminate();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {
            case MI_QUIT:
                getModel().terminate();
                break;
            default:
                //System.out.println("Event: "+ enm.name());
        }
    }
}
