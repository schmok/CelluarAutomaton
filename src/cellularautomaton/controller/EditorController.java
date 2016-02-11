package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.windows.editor.CAAutomatonEditorWindow;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.tools.Diagnostic;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
                tryComile();
                break;
        }
    }

    private void tryComile() {
        String code = this.getView().getTextPane().getText();
        if(AutomatonCompiler.getInstance().compile(code, this.getModel().getSourceFile())) {
            this.getParent().getAutomatonLoaderController().loadAutomatonClassFromName(AutomatonCompiler.getInstance().getLastClassName());
            try {
                PrintWriter out = new PrintWriter(this.getModel().getSourceFile().getPath().replace(".class",".java"));
                out.println(this.getView().getTextPane().getText());
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            ArrayList<Diagnostic> diagnostics = AutomatonCompiler.getInstance().getLastDiagnostics();
            String msg = "";
            for(Diagnostic d: diagnostics) {
                msg += d.toString()+"\n";
            }
            JOptionPane.showMessageDialog(this.getView(),msg);
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
