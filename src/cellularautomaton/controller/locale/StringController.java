package cellularautomaton.controller.locale;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Viktor Spadi on 14.10.2015.
 *
 * The place to implement localisation later on
 */
public class StringController {
    // Attributes //////////////////////////////////////////////////////////////////////////////////////////////////////
    static Properties props;
    static Locale locale;
    static ResourceBundle bundle;

    private static StringController stringController;
    private AcceleratorMnemonicMapper amMapper;

    // Methods /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     * Constructor
     */

    static {
        StringController.props = new Properties();
        try {
            ClassLoader classLoader = StringController.class.getClassLoader();
            StringController.props.load(classLoader.getResourceAsStream("cellularautomaton.properties"));
            String language = StringController.props.getProperty("language");
            if(language == null) {
                StringController.locale = Locale.getDefault();
            } else {
                StringController.locale = new Locale(language);
            }
        } catch(Throwable e) {
            StringController.locale = Locale.getDefault();
        }
        Locale.setDefault(StringController.locale);
        JComponent.setDefaultLocale(StringController.locale);
        StringController.bundle = ResourceBundle.getBundle("guitext", StringController.locale);
        StringController sc = StringController.getInstance();
        System.out.println(sc.get(StringEnumeration.MI_PRINT));
    }

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
        try{
            return StringController.bundle.getString(text.toString());
        }catch(MissingResourceException e) {
            return "!" + text.toString() + "!";
        }
    }

    public int getMnemonic(StringEnumeration text) {
        return this.amMapper.getMnemonic(text);
    }

    public KeyStroke getAccelerator(StringEnumeration text) {
        return this.amMapper.getAccelerator(text);
    }
}
