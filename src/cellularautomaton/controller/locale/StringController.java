package cellularautomaton.controller.locale;

import javax.swing.*;

/**
 * Created by Viktor Spadi on 14.10.2015.
 *
 * The place to implement localisation later on
 */
public class StringController {
    // Attributes //////////////////////////////////////////////////////////////////////////////////////////////////////

    private static StringController stringController;
    private AcceleratorMnemonicMapper amMapper;

    // Methods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     * Constructor
     */
    protected StringController(){
        this.amMapper = new AcceleratorMnemonicMapper();
    }

    /*
     * Singleton
     */
    public static StringController getInstance() {
        if(stringController == null)
            stringController = new StringController();
        return stringController;
    }

    /*
     * Method to get String by given Enumeration
     * @param text Enumerationvalue as identifier for the String
     */

    public String get(StringEnumeration text) {
        return text.toString();
    }

    public int getMnemonic(StringEnumeration text) {
        return this.amMapper.getMnemonic(text);
    }

    public KeyStroke getAccelerator(StringEnumeration text) {
        return this.amMapper.getAccelerator(text);
    }
}
