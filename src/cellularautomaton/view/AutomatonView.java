package cellularautomaton.view;

import cellularautomaton.controller.locale.*;
import cellularautomaton.view.gui.basicview.footer.CAFooter;
import cellularautomaton.view.gui.basicview.menu.CAMenuBar;
import cellularautomaton.view.gui.basicview.states.*;
import cellularautomaton.view.gui.basicview.toolbar.CAToolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;

/**
 * Created by Viktor Spadi on 14.10.2015.
 * Basic implementation of the interface AbstractAutomatonView
 */
public class AutomatonView extends AbstractAutomatonView {
    // Attributes //////////////////////////////////////////////////////////////////////////////////////////////////////
    private JFrame frame;
    private CAMenuBar menuBar;
    private CAToolbar toolbar;
    private CAFooter footer;
    private CAStateContainer stateContainer;
    private JScrollPane stateScrollPane;
    private CAPopulationContainer populationContainer;
    private JScrollPane populationScrollPane;

    // Methods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     * Constructor
     */
    public AutomatonView() {
        super();
    }

    /*
     * Mehtod to show the window
     */
    public void open() {
        this.frame.pack();
        this.frame.setVisible(true);
    }

    /*
     * Method to initialize all components
     */
    public void init() {
        // Create Components
        this.frame = new JFrame(this.stringController.get(StringEnumeration.WINDOW_TITLE));
        this.menuBar = new CAMenuBar(this.stringController);
        this.toolbar = new CAToolbar(StringController.getInstance());
        this.footer = new CAFooter();
        this.populationContainer = new CAPopulationContainer();
        this.populationScrollPane = new JScrollPane(this.populationContainer,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.stateContainer = new CAStateContainer();
        this.stateScrollPane = new JScrollPane(this.stateContainer,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.toolbar.zoomInButton.addActionListener(e -> {
            this.populationContainer.increaseCellSize();
        });

        this.toolbar.zoomOutButton.addActionListener(e -> {
            this.populationContainer.decreaseCellSize();
        });

        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());
        this.frame.setJMenuBar(this.menuBar);

        // Add components
        this.frame.add(this.toolbar, BorderLayout.PAGE_START);
        this.frame.add(this.footer, BorderLayout.SOUTH);
        this.frame.add(this.stateScrollPane, BorderLayout.WEST);
        this.frame.add(this.populationScrollPane, BorderLayout.CENTER);


        // Push some basic cells into it
        this.stateContainer.addCell();
        this.stateContainer.addCell();
        this.stateContainer.addCell();

        bindEvents();
    }

    // Events //////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void bindEvents() {
        // ToDo - Remove Events from here to Controller
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    terminate();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
