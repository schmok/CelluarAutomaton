package cellularautomaton.controller;

import cellularautomaton.app.Main;
import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.model.Automaton;
import cellularautomaton.view.gui.basicview.windows.CAJAutomatonClassChooser;

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
        int returnVal = getView().showDialog(getParent().getView().getFrame(), StringController.getInstance().get(StringEnumeration.W_AUTOMATON_CHOOSER));
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = getView().getSelectedFile();
            try {
                URL[] urls = new URL[]{file.getParentFile().getAbsoluteFile().toURI().toURL()};
                ClassLoader cl = new URLClassLoader(urls);
                //String className = "cellularautomaton.model.automata."+file.getName().replace(".class", "");
                String className = file.getName().replace(".class", "");
                //Class clazz = cl.loadClass(className);
                Class clazz = Class.forName(className, true, cl);
                Constructor[] constructors = clazz.getConstructors();

                if(constructors.length == 1 && Automaton.class.isAssignableFrom(clazz)) {
                    // only if one constructor...
                    Constructor constructor = constructors[0];
                    Object[] args = new Object[constructor.getParameterCount()];
                    for(int i = 0; i < args.length; i++) {
                        Class<?> param = constructor.getParameterTypes()[i];
                        // TODO hier noch auswahlfenster anzeigen
                    }
                    // tmp
                    args[0] = 50;
                    args[1] = 50;
                    args[2] = true;

                    Automaton automaton = (Automaton)constructor.newInstance(args);
                    if(automaton != null) {
                        Main.createAutomatonWindow(automaton);
                    }
                    System.out.println("Yoooo");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
