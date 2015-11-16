package cellularautomaton.controller;

import cellularautomaton.event.AutomatonEventEnum;
import cellularautomaton.model.CellularAutomaton;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by vspadi on 08.11.15.
 */
public abstract class AbstractController<T extends IOwnEnumeration,C> implements ActionListener {
    private CellularAutomaton cellularAutomaton;
    private C parent;
    private T view;
    private HashMap<AutomatonEventEnum, ControllerCallable> actions;

    AbstractController(T view, C parent) {
        this.view = view;
        this.parent = parent;
        this.actions = new HashMap<>();
    }

    public T getView() {
        return this.view;
    }

    public C getParent() { return this.parent; }

    public void bindModel(CellularAutomaton cellularAutomaton) {
        this.cellularAutomaton = cellularAutomaton;
        this.bindEvents(this.getView().getClass(), this.getView());
    }

    public CellularAutomaton getModel() {
        return this.cellularAutomaton;
    }

    public void bindEvents(Class<?> theClass, Object obj) {
        for(Field f: theClass.getDeclaredFields()) {
            if(IOwnEnumeration.class.isAssignableFrom(f.getType())) {
                String mn = "get"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1);
                Object child = null;
                try {
                    child = theClass.getMethod(mn).invoke(obj);
                    if(AbstractButton.class.isAssignableFrom(f.getType())) {
                        AbstractButton raw = (AbstractButton) child;
                        raw.addActionListener(this);
                    }
                    this.bindEvents(f.getType(), child);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        // Todo Event dispatcher
    }
}
