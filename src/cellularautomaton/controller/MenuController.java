package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.controller.xml.CellArray;
import cellularautomaton.controller.xml.ObjectFactory;
import cellularautomaton.controller.xml.XMLColor;
import cellularautomaton.model.Automaton;
import cellularautomaton.model.Cell;
import cellularautomaton.view.gui.basicview.menu.CAMenuBar;
import cellularautomaton.view.gui.basicview.windows.CAJAutomatonClassChooser;
import cellularautomaton.view.util.FileHelper;
import cellularautomaton.view.util.IOwnEnumeration;
import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;

import javax.swing.*;
import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

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
            file = new File(FileHelper.removeFileEnding(file.getAbsolutePath())+"."+f.toString());
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

    public void saveAutomatonXML() {
        File file = createFileOrChoose(CAJAutomatonClassChooser.FILTER.XML);
        if(file != null) {
            ObjectFactory objFactory = new ObjectFactory(getModel().getAutomaton());
            cellularautomaton.controller.xml.Automaton xmlAutomaton = (cellularautomaton.controller.xml.Automaton)objFactory.createAutomaton();
            JAXBContext jaxbContext = null;
            try {
                jaxbContext = JAXBContext.newInstance(cellularautomaton.controller.xml.Automaton.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
                try {
                    marshaller.marshal(xmlAutomaton, new FileOutputStream(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadAutomatonXML() {
        File file = createFileOrChoose(CAJAutomatonClassChooser.FILTER.XML);
        if(file != null) {
            JAXBContext jaxbContext = null;
            try {
                jaxbContext = JAXBContext.newInstance(cellularautomaton.controller.xml.Automaton.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                cellularautomaton.controller.xml.Automaton xmlAutomaton = (cellularautomaton.controller.xml.Automaton)unmarshaller.unmarshal(file);
                if(xmlAutomaton != null) {
                    Automaton automaton = getModel().getAutomaton();
                    if(automaton.getNumberOfStates() == xmlAutomaton.getNumberOfStates()) {
                        this.getModel().setSize(xmlAutomaton.getNumberOfRows(), xmlAutomaton.getNumberOfColumns());
                        for(int i = 0; i < xmlAutomaton.getColors().size();i++) {
                            XMLColor color = xmlAutomaton.getColors().get(i);
                            this.getModel().changeColor(i, new Color(color.r, color.g, color.b));
                        }
                        int x = 0;
                        int y;
                        for(CellArray ca: xmlAutomaton.getPopulation()) {
                            y = 0;
                            for(cellularautomaton.controller.xml.Cell c: ca.item) {
                                this.getModel().setState(x,y, c.getState());
                                y++;
                            }
                            x++;
                        }
                    }
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveAutomatonXML2() {
        File file = createFileOrChoose(CAJAutomatonClassChooser.FILTER.XML);
        if(file != null) {
            try {
                Automaton automaton = getModel().getAutomaton();
                XMLOutputFactory xof = XMLOutputFactory.newInstance();
                XMLStreamWriter xsw = new IndentingXMLStreamWriter(xof.createXMLStreamWriter(new FileWriter(file.getAbsolutePath())));

                // start document
                xsw.writeStartDocument("utf-8","1.0");
                // automaton
                xsw.writeStartElement("automaton");
                // num cols
                xsw.writeStartElement("numberOfColumns");
                xsw.writeCharacters(String.valueOf(automaton.getNumberOfColumns()));
                xsw.writeEndElement();
                // num rows
                xsw.writeStartElement("numberOfRows");
                xsw.writeCharacters(String.valueOf(automaton.getNumberOfRows()));
                xsw.writeEndElement();
                // num states
                xsw.writeStartElement("numberOfStates");
                xsw.writeCharacters(String.valueOf(automaton.getNumberOfStates()));
                xsw.writeEndElement();

                // population
                for(Cell[] ca: automaton.getPopulation()) {
                    xsw.writeStartElement("population");
                    for(Cell c: ca) {
                        xsw.writeStartElement("item");
                        xsw.writeStartElement("state");
                        xsw.writeCharacters(String.valueOf(c.getState()));
                        xsw.writeEndElement();
                        xsw.writeEndElement();
                    }
                    xsw.writeEndElement();
                }

                // torus
                xsw.writeStartElement("torus");
                xsw.writeCharacters(String.valueOf(automaton.isTorus()));
                xsw.writeEndElement();

                // colors
                for(Color c: automaton.getColorMapping()) {
                    xsw.writeStartElement("colors");
                    xsw.writeStartElement("r");
                    xsw.writeCharacters(String.valueOf(c.getRed()));
                    xsw.writeEndElement();
                    xsw.writeStartElement("g");
                    xsw.writeCharacters(String.valueOf(c.getGreen()));
                    xsw.writeEndElement();
                    xsw.writeStartElement("b");
                    xsw.writeCharacters(String.valueOf(c.getBlue()));
                    xsw.writeEndElement();
                    xsw.writeEndElement();
                }

                xsw.writeEndElement();
                xsw.writeEndDocument();
                xsw.flush();
                xsw.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadAutomatonXML2() {
        File file = createFileOrChoose(CAJAutomatonClassChooser.FILTER.XML);
        if(file != null) {
            try {
                int type = 0;
                XMLInputFactory xif = XMLInputFactory.newInstance();
                XMLStreamReader xsr = xif.createXMLStreamReader(new FileInputStream(file));
                int numberOfRows = 0;
                int numberOfColumns = 0;
                int numberOfStates = 0;
                boolean isTorus = false;
                ArrayList<Color> colors = new ArrayList<>();
                ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
                int row = -1;
                int col = -1;
                while(xsr.hasNext()) {
                    switch (xsr.getEventType()) {
                        case XMLEvent.START_ELEMENT:
                            switch (String.valueOf(xsr.getName())) {
                                case "numberOfStates": type = 1; break;
                                case "numberOfRows": type = 2; break;
                                case "numberOfColumns": type = 3; break;
                                case "population": type = 4; break;
                                case "item":  type = 5; break;
                                case "state": type = 5; break;
                                case "torus": type = 6; break;
                                case "colors": type = 7; break;
                                case "r": type = 8; break;
                                case "g": type = 9; break;
                                case "b": type = 10; break;
                            }
                            break;
                        case XMLEvent.CHARACTERS:
                            String text = xsr.getText().trim();
                            if(type == 4) {
                                cells.add(new ArrayList<>());
                                row++;
                            }
                            if(type == 7) {
                                colors.add(new Color(0,0,0));
                                col++;
                            }
                            if(text != null && text != "" && text.length() > 0) {
                                switch (type) {
                                    case 1:
                                        numberOfStates = Integer.parseInt(text);
                                        break;
                                    case 2:
                                        numberOfRows = Integer.parseInt(text);
                                        break;
                                    case 3:
                                        numberOfColumns = Integer.parseInt(text);
                                        break;
                                    case 6:
                                        isTorus = Boolean.parseBoolean(text);
                                        break;
                                    case 5:
                                        ArrayList<Cell> d = cells.get(row);
                                        d.add(new Cell(Integer.parseInt(text)));
                                        break;
                                    case 8:
                                        Color a = colors.get(col);
                                        a = new Color(Integer.parseInt(text), a.getGreen(), a.getBlue());
                                        colors.set(col, a);
                                        break;
                                    case 9:
                                        Color b = colors.get(col);
                                        b = new Color(b.getRed(), Integer.parseInt(text), b.getBlue());
                                        colors.set(col, b);
                                        break;
                                    case 10:
                                        Color e = colors.get(col);
                                        e = new Color(e.getRed(), e.getGreen(), Integer.parseInt(text));
                                        colors.set(col, e);
                                        break;
                                }
                            }
                            break;
                    }
                    xsr.next();
                }
                Automaton automaton = getModel().getAutomaton();
                if(numberOfStates == automaton.getNumberOfStates()) {
                    getModel().setSize(numberOfRows, numberOfColumns);
                    for(int i = 0; i < colors.size(); i++) {
                        getModel().changeColor(i, colors.get(i));
                    }
                    for(int x = 0;  x < cells.size();x++) {
                        for(int y = 0;y < cells.get(x).size();y++) {
                            getModel().setState(x,y, cells.get(x).get(y).getState());
                        }
                    }
                }
            } catch (XMLStreamException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
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
            case MI_SAVE_XML:
                saveAutomatonXML2();
                break;
            case MI_LOAD_XML:
                loadAutomatonXML2();
                break;
            case MI_SETTINGS_SAVE:
                getParent().getSettingsController().saveSettings();
                break;
            case MI_SETTINGS_LOAD:
                getParent().getSettingsController().loadSettings();
                break;
            case MI_SETTINGS_DELETE:
                getParent().getSettingsController().deleteSettings();
                break;
            default:
                System.out.println("MenuAction: "+ enm.name()+ " From:"+e.getSource().getClass().getName());
        }
    }
}
