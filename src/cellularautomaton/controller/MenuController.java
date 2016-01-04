package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.model.Automaton;
import cellularautomaton.view.gui.basicview.menu.CAMenuBar;
import cellularautomaton.view.gui.basicview.windows.CAJAutomatonClassChooser;
import cellularautomaton.view.util.FileHelper;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Created by vspadi on 08.11.15.
 */
public class MenuController extends AbstractController<CAMenuBar, CellularAutomatonController> {

    MenuController(CAMenuBar view, CellularAutomatonController cac) {
        super(view, cac);
    }

    public File createFileOrChoose(CAJAutomatonClassChooser.FILTER f) {
        CAJAutomatonClassChooser chooser = this.getParent().getAutomatonLoaderController().getView();
        chooser.setFilter(f);
        int returnVal = chooser.showDialog(getParent().getView().getFrame(), StringController.getInstance().get(StringEnumeration.W_AUTOMATON_CHOOSER));
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            file = new File(FileHelper.removeFileEnding(file.getAbsolutePath())+".ser");
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return file;
        }
        return null;
    }

    private void saveAutomatonSerial() {
        File file = createFileOrChoose(CAJAutomatonClassChooser.FILTER.SERIAL);
        if(file != null) {
            try {
                file.delete();
                file.createNewFile();
                FileOutputStream fops = new FileOutputStream(file);
                ObjectOutputStream oops = new ObjectOutputStream(fops);
                oops.writeObject(this.getModel().getAutomaton());
                oops.close();
                fops.close();
                System.out.println("File has been serialized");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadAutomatonSerial() {
        File file = createFileOrChoose(CAJAutomatonClassChooser.FILTER.SERIAL);
        if(file != null ) {
            try {
                FileInputStream fips = new FileInputStream(file.getAbsolutePath());
                ObjectInputStream oips = new ObjectInputStream(fips);
                Automaton newAutomaton = (Automaton)oips.readObject();
                if(newAutomaton != null) {
                    Automaton current = this.getModel().getAutomaton();
                    if(current.isMooreNeighborHood() == newAutomaton.isMooreNeighborHood() &&
                            current.getNumberOfStates() == current.getNumberOfStates()) {
                        this.getModel().setAutomaton(newAutomaton);

                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {
            // Automatonmenu
            case MI_SAVE_SERIAL:
                saveAutomatonSerial();
                break;
            case MI_LOAD_SERIAL:
                loadAutomatonSerial();
                break;
            default:
                //System.out.println("MenuAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());
        }
    }
}
