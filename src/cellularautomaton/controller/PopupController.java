package cellularautomaton.controller;

import cellularautomaton.annotation.Callable;
import cellularautomaton.model.Automaton;
import cellularautomaton.model.CellularAutomaton;
import cellularautomaton.view.gui.basicview.menu.CAJPopupMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vspadi on 07.12.15.
 */
public class PopupController extends AbstractController<CAJPopupMenu ,CellularAutomatonController>{
    HashMap<JMenuItem, Method> automatonMethods;

    public PopupController(CAJPopupMenu popupMenu, CellularAutomatonController cac) {
        super(popupMenu, cac);
    }

    public void showPopup(MouseEvent e) {
        queryMethodsFromActualAutomaton();
        getView().show(e.getComponent(), e.getX(), e.getY());
    }

    private void queryMethodsFromActualAutomaton() {
        getView().removeAll();
        automatonMethods = new HashMap<>();

        Class<? extends Automaton> automatonClass = getModel().getAutomaton().getClass();
        for(Method method: automatonClass.getMethods()) {
            if(method.getDeclaringClass() == automatonClass &&
                    !Modifier.isPrivate(method.getModifiers()) &&
                    !Modifier.isStatic(method.getModifiers()) ) {
                Callable callableAnnotation = method.getAnnotation(Callable.class);
                if(callableAnnotation != null) {
                    JMenuItem item = new JMenuItem(method.getName());
                    item.addActionListener(this);
                    getView().add(item);
                    automatonMethods.put(item, method);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JMenuItem) {
            JMenuItem item = (JMenuItem)(e.getSource());
            if(this.automatonMethods.containsKey(item)) {
                try {
                    this.automatonMethods.get(item).invoke(getModel().getAutomaton());
                } catch (IllegalAccessException e1) {
                    //e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    //e1.printStackTrace();
                }
            }
        }
    }
}
