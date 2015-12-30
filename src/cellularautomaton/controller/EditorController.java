package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.windows.editor.CAAutomatonEditorWindow;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;

/**
 * Created by viktorspadi on 30.12.15.
 */
public class EditorController extends AbstractController<CAAutomatonEditorWindow, CellularAutomatonController> implements DocumentListener{

    EditorController(CAAutomatonEditorWindow view, CellularAutomatonController parent) {
        super(view, parent);

        view.getTextPane().getDocument().addDocumentListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {
            case MI_COMPILE:
                // TODO Compile
                break;
        }
    }

    private void applyStyle() {
        String[] text = this.getView().getTextPane().getText().split(" ");

        this.getModel().setSourceCode(this.getView().getTextPane().getText());
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        applyStyle();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        applyStyle();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
