package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class CABasicPopulationMenu extends JMenu implements Observer {
    private CAJMenuItem changeSizeItem;
    private CAJMenuItem deleteItem;
    private CAJMenuItem createItem;
    private CAJMenuItem torusItem;

    private CAJMenuItem zoomInItem;
    private CAJMenuItem zoomOutItem;
    private CAJSubMenu saveItem;
    private CAJMenuItem saveXMLItem;
    private CAJMenuItem saveSerialItem;
    private CAJSubMenu loadItem;
    private CAJMenuItem loadXMLItem;
    private CAJMenuItem loadSerialItem;

    private CAJMenuItem printItem;
    private CAJSubMenu saveAsImageItem;
    private CAJMenuItem GIFItem;
    private CAJMenuItem PNGItem;


    public CABasicPopulationMenu(StringController stringController) {
        super(stringController.get(StringEnumeration.MB_POPULATION));
        add(this.changeSizeItem = new CAJMenuItem(StringEnumeration.MI_CHANGE_SIZE));
        add(this.deleteItem = new CAJMenuItem(StringEnumeration.MI_DELETE));
        add(this.createItem = new CAJMenuItem(StringEnumeration.MI_CREATE));
        add(this.torusItem = new CAJMenuItem(StringEnumeration.MI_TORUS));
        addSeparator();
        add(this.zoomInItem = new CAJMenuItem(StringEnumeration.MI_ZOOM_IN));
        add(this.zoomOutItem = new CAJMenuItem(StringEnumeration.MI_ZOOM_OUT));
        add(this.saveItem = new CAJSubMenu(StringEnumeration.MS_SAVE));
        this.saveItem.add(this.saveXMLItem = new CAJMenuItem(StringEnumeration.MI_XML));
        this.saveItem.add(this.saveSerialItem = new CAJMenuItem(StringEnumeration.MI_SERIAL));
        add(this.loadItem = new CAJSubMenu(StringEnumeration.MS_LOAD));
        this.loadItem.add(this.loadXMLItem = new CAJMenuItem(StringEnumeration.MI_XML));
        this.loadItem.add(this.loadSerialItem = new CAJMenuItem(StringEnumeration.MI_SERIAL));
        addSeparator();
        add(this.printItem = new CAJMenuItem(StringEnumeration.MI_PRINT));
        add(this.saveAsImageItem = new CAJSubMenu(StringEnumeration.MS_SAVE_AS_IMG));
        this.saveAsImageItem.add(this.GIFItem = new CAJMenuItem(StringEnumeration.MI_GIF));
        this.saveAsImageItem.add(this.PNGItem = new CAJMenuItem(StringEnumeration.MI_PNG));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
