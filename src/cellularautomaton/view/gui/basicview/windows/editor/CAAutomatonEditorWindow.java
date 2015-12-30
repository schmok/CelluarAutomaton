package cellularautomaton.view.gui.basicview.windows.editor;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.components.CAJFrame;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.*;

/**
 * Created by viktorspadi on 30.12.15.
 */
public class CAAutomatonEditorWindow extends CAJFrame implements IOwnEnumeration {
    private CAJEditorToolbar editorToolbar;
    private JTextPane textPane;

    public CAJEditorToolbar getEditorToolbar() { return editorToolbar; }
    public JTextPane getTextPane() { return textPane; }

    public CAAutomatonEditorWindow() {
        super(StringEnumeration.W_AUTOMATON_EDITOR);

        this.editorToolbar = new CAJEditorToolbar();
        this.setLayout(new BorderLayout());
        this.add(this.editorToolbar, BorderLayout.PAGE_START);
        this.textPane = new JTextPane();
        this.textPane.setPreferredSize(new Dimension(500,500));
        this.add(this.textPane, BorderLayout.CENTER);
        this.pack();
    }

    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.W_AUTOMATON_EDITOR;
    }

}
