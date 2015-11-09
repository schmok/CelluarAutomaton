package cellularautomaton.controller;

import cellularautomaton.model.CellularAutomaton;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

/**
 * Created by vspadi on 08.11.15.
 */
public abstract class AbstractController<T extends IOwnEnumeration,C> implements ActionListener {
    private CellularAutomaton cellularAutomaton;
    private C parent;
    private T view;
    private int depth = 0;

    AbstractController(T view, C parent) {
        this.view = view;
        this.parent = parent;
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
        String pi = "";
        boolean found = false;
        for(int i = 0; i < depth; i++) {
            pi += "\t";
        }

        System.out.printf("%sI AM A %s and my Subclasses are:\n",pi, this.getView().getClass().getSimpleName());

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
                    System.out.println(pi+f.getName()+" ->"+f.getType());
                    found = true;
                } catch (Exception e) {
                    System.out.println("NULL " + theClass.getName() +"\n" + mn + child);
                   //e.printStackTrace();
                }
            }
        }
        if(found) {
            depth++;
        }
    }

    abstract public void actionPerformed(ActionEvent e);
}
