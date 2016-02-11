package cellularautomaton.controller;

import cellularautomaton.app.Main;
import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.model.Automaton;
import cellularautomaton.model.CellularAutomaton;
import cellularautomaton.view.gui.basicview.windows.CAJAutomatonClassChooser;
import cellularautomaton.view.util.FileHelper;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by vspadi on 07.12.15.
 */
public class AutomatonLoaderController extends AbstractController<CAJAutomatonClassChooser, CellularAutomatonController> {

    AutomatonLoaderController(CAJAutomatonClassChooser view, CellularAutomatonController parent) {
        super(view, parent);

    }

    public void loadAutomaton() {
        getView().setFilter(CAJAutomatonClassChooser.FILTER.CLASS);
        int returnVal = getView().showDialog(getParent().getView().getFrame(), StringController.getInstance().get(StringEnumeration.W_AUTOMATON_CHOOSER));
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = getView().getSelectedFile();
            loadAutomatonClass(file);
        }
    }

    public boolean loadAutomatonClassFromName(String name) {
        try {
            URLClassLoader cl = new URLClassLoader(new URL[]{new File("./").toURI().toURL()});
            Class<?> compiledClass = null;
            compiledClass = cl.loadClass(name);
            Automaton automaton = (Automaton)compiledClass.newInstance();
            if(automaton != null) {
                this.getModel().setAutomaton(automaton);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean loadAutomatonClass(File file) {
        try {
            URL[] urls = new URL[]{file.getParentFile().getAbsoluteFile().toURI().toURL()};
            ClassLoader cl = new URLClassLoader(urls);
            String className = file.getName().replace(".class", "");
            Class clazz = Class.forName(className, true, cl);
            Automaton automaton = (Automaton)clazz.newInstance();
            if(automaton != null) {
                CellularAutomaton ca = Main.createAutomatonWindow(automaton);
                File sc = new File(file.getAbsolutePath().replace("class", "java"));
                this.getModel().setSourceFile(sc);
                String code = FileHelper.getStringFromFile(sc);
                ca.setSourceCode(code);
                ca.setSourceFile(file);
                return true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

}
