package cellularautomaton.view.gui.basicview.menu;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class CAPopulationMenu extends JMenu implements Observer, IOwnEnumeration {
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

    public CAJMenuItem getChangeSizeItem() {
        return changeSizeItem;
    }

    public CAJMenuItem getDeleteItem() {
        return deleteItem;
    }

    public CAJMenuItem getCreateItem() {
        return createItem;
    }

    public CAJMenuItem getTorusItem() {
        return torusItem;
    }

    public CAJMenuItem getZoomInItem() {
        return zoomInItem;
    }

    public CAJMenuItem getZoomOutItem() {
        return zoomOutItem;
    }

    public CAJSubMenu getSaveItem() {
        return saveItem;
    }

    public CAJMenuItem getSaveXMLItem() {
        return saveXMLItem;
    }

    public CAJMenuItem getSaveSerialItem() {
        return saveSerialItem;
    }

    public CAJSubMenu getLoadItem() {
        return loadItem;
    }

    public CAJMenuItem getLoadXMLItem() {
        return loadXMLItem;
    }

    public CAJMenuItem getLoadSerialItem() {
        return loadSerialItem;
    }

    public CAJMenuItem getPrintItem() {
        return printItem;
    }

    public CAJSubMenu getSaveAsImageItem() {
        return saveAsImageItem;
    }

    public CAJMenuItem getGIFItem() {
        return GIFItem;
    }

    public CAJMenuItem getPNGItem() {
        return PNGItem;
    }

    public CAPopulationMenu(StringController stringController) {
        super(stringController.get(StringEnumeration.MB_POPULATION));

        // Add components
        add(this.changeSizeItem = new CAJMenuItem(StringEnumeration.MI_CHANGE_SIZE));
        add(this.deleteItem = new CAJMenuItem(StringEnumeration.MI_DELETE));
        add(this.createItem = new CAJMenuItem(StringEnumeration.MI_CREATE));
        add(this.torusItem = new CAJMenuItem(StringEnumeration.MI_TORUS));
        addSeparator();
        add(this.zoomInItem = new CAJMenuItem(StringEnumeration.MI_ZOOM_IN));
        add(this.zoomOutItem = new CAJMenuItem(StringEnumeration.MI_ZOOM_OUT));
        add(this.saveItem = new CAJSubMenu(StringEnumeration.MS_SAVE));
        this.saveItem.add(this.saveXMLItem = new CAJMenuItem(StringEnumeration.MI_SAVE_XML));
        this.saveItem.add(this.saveSerialItem = new CAJMenuItem(StringEnumeration.MI_SAVE_SERIAL));
        add(this.loadItem = new CAJSubMenu(StringEnumeration.MS_LOAD));
        this.loadItem.add(this.loadXMLItem = new CAJMenuItem(StringEnumeration.MI_LOAD_XML));
        this.loadItem.add(this.loadSerialItem = new CAJMenuItem(StringEnumeration.MI_LOAD_SERIAL));
        addSeparator();
        add(this.printItem = new CAJMenuItem(StringEnumeration.MI_PRINT));
        add(this.saveAsImageItem = new CAJSubMenu(StringEnumeration.MS_SAVE_AS_IMG));
        this.saveAsImageItem.add(this.GIFItem = new CAJMenuItem(StringEnumeration.MI_GIF));
        this.saveAsImageItem.add(this.PNGItem = new CAJMenuItem(StringEnumeration.MI_PNG));
    }

    @Override
    public void update(Observable o, Object arg) {

    }


    @Override
    public StringEnumeration getEnumeration() {
        return StringEnumeration.CA_POPULATIONMENU;
    }
}
