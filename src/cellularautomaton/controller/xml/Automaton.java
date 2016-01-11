//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2016.01.11 um 07:04:27 PM CET 
//


package cellularautomaton.controller.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>Java-Klasse für automaton complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="automaton">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numberOfColumns" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numberOfRows" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="population" type="{}cellArray" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="torus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Cell.class,CellArray.class, XMLColor.class})
@XmlType(name = "automaton", propOrder = {
    "numberOfColumns",
    "numberOfRows",
    "numberOfStates",
    "population",
    "torus",
    "colors"
})
public class Automaton {

    public int numberOfColumns;
    public int numberOfRows;
    @XmlElement(nillable = true)
    public ArrayList<CellArray> population;
    public boolean torus;
    public ArrayList<XMLColor> colors;
    public int numberOfStates;

    public Automaton() {
        this.population = new ArrayList<>();
        this.colors = new ArrayList<>();
    }

    /**
     * Ruft den Wert der numberOfColumns-Eigenschaft ab.
     * 
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * Legt den Wert der numberOfColumns-Eigenschaft fest.
     * 
     */
    public void setNumberOfColumns(int value) {
        this.numberOfColumns = value;
    }

    /**
     * Ruft den Wert der numberOfRows-Eigenschaft ab.
     * 
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * Legt den Wert der numberOfRows-Eigenschaft fest.
     * 
     */
    public void setNumberOfRows(int value) {
        this.numberOfRows = value;
    }

    /**
     * Gets the value of the population property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the population property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPopulation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CellArray }
     * 
     * 
     */
    public ArrayList<CellArray> getPopulation() {
        if (population == null) {
            population = new ArrayList<CellArray>();
        }
        return this.population;
    }

    /**
     * Ruft den Wert der torus-Eigenschaft ab.
     * 
     */
    public boolean isTorus() {
        return torus;
    }

    /**
     * Legt den Wert der torus-Eigenschaft fest.
     * 
     */
    public void setTorus(boolean value) {
        this.torus = value;
    }

    public ArrayList<XMLColor> getColors() {
        return colors;
    }

    public int getNumberOfStates() {
        return numberOfStates;
    }

    public void setNumberOfStates(int numberOfStates) {
        this.numberOfStates = numberOfStates;
    }
}
