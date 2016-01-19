//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2016.01.11 um 07:04:27 PM CET 
//


package cellularautomaton.controller.xml;

import javax.xml.bind.annotation.XmlRegistry;
import java.awt.*;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private Automaton automaton;

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory(cellularautomaton.model.Automaton rAuto) {
        this.automaton = new Automaton() {
            {
                this.setNumberOfColumns(rAuto.getNumberOfColumns());
                this.setNumberOfRows(rAuto.getNumberOfRows());
                this.setNumberOfStates(rAuto.getNumberOfStates());
                this.setTorus(rAuto.isTorus());
                for(int i = 0; i < rAuto.getPopulation()[0].length;i++) {
                    CellArray cellArray = new CellArray();
                    for(cellularautomaton.model.Cell c: rAuto.getPopulation()[i]) {
                        cellArray.item.add(new Cell(c.getState()));
                    }
                    this.population.add(cellArray);
                }
                for(Color c: rAuto.getColorMapping()) {
                    XMLColor xmlColor = new XMLColor();
                    xmlColor.r = c.getRed();
                    xmlColor.g = c.getGreen();
                    xmlColor.b = c.getBlue();
                    this.colors.add(xmlColor);
                }
            }
        };
    }

    /**
     * Create an instance of {@link CellArray }
     * 
     */
    public CellArray createCellArray() {
        return new CellArray();
    }

    /**
     * Create an instance of {@link Cell }
     * 
     */
    public Cell createCell() {
        return new Cell();
    }

    public Automaton createAutomaton() { return automaton; }

}
