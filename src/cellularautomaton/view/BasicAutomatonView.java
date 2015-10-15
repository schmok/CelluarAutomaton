package cellularautomaton.view;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.menu.CABasicMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;

/**
 * Created by Viktor Spadi on 14.10.2015.
 * Basic implementation of the interface AbstractAutomatonView
 */
public class BasicAutomatonView extends AbstractAutomatonView {
    // Attributes //////////////////////////////////////////////////////////////////////////////////////////////////////
    private JFrame frame;
    private CABasicMenuBar menuBar;
    // Methods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     * Constructor
     */
    public BasicAutomatonView(StringController stringController) {
        super(stringController);
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
        this.frame = new JFrame(getString(StringEnumeration.WINDOW_TITLE));
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());
        this.frame.setJMenuBar(this.menuBar = new CABasicMenuBar(getStringController()));
        bindEvents();
    }

    // Events //////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void bindEvents() {
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
