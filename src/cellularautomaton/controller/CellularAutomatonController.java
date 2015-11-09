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
public class CellularAutomatonController extends AbstractController<AutomatonView> {
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
        super(automatonView);
        this.menuController = new MenuController(getView().getMenuBar());
        this.stateController = new StateController(getView().getStateContainer());
        this.toolbarController = new ToolbarController(getView().getToolbar());
        this.poppulationController = new PoppulationController(getView().getPopulationContainer());
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
