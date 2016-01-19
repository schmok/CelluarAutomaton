package cellularautomaton.controller.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by vspadi on 11.01.16.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "color", propOrder = {
        "r",
        "g",
        "b"
})
public class XMLColor {
    public int r;
    public int g;
    public int b;

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
