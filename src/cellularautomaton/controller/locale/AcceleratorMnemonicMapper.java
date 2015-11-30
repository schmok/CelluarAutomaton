package cellularautomaton.controller.locale;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class AcceleratorMnemonicMapper {
    private HashMap<StringEnumeration, Character> usedMnemonics;

    public AcceleratorMnemonicMapper() {
        this.usedMnemonics = new HashMap<>();
    }

    private char getCharForString(StringEnumeration text) {
        if(usedMnemonics.containsKey(text)) {
            return usedMnemonics.get(text);
        }

        String val = StringController.getInstance().get(text);
        if(usedMnemonics.containsValue(val.charAt(0))) {
            for(int i = 0; i < val.length();i++) {
                if(!usedMnemonics.containsValue(val.charAt(i))) {
                    usedMnemonics.put(text,Character.toUpperCase(val.charAt(i)));
                    return Character.toUpperCase(val.charAt(i));
                }
            }
            return Character.toUpperCase(val.charAt(0));
        } else {
            usedMnemonics.put(text,Character.toUpperCase(val.charAt(0)));
            return Character.toUpperCase(val.charAt(0));
        }
    }

    public int getMnemonic(StringEnumeration text) {
        return KeyEvent.getExtendedKeyCodeForChar(getCharForString(text));
    }

    public KeyStroke getAccelerator(StringEnumeration text) {
        switch(text) {
            case MI_LOAD:
                return KeyStroke.getKeyStroke("ctrl "+getCharForString(text));
            case MI_NEW:
                return KeyStroke.getKeyStroke("ctrl "+getCharForString(text));
            case MI_EDITOR:
                return KeyStroke.getKeyStroke("ctrl "+getCharForString(text));
            case MI_QUIT:
                return KeyStroke.getKeyStroke("ctrl "+getCharForString(text));
            case MI_CHANGE_SIZE:
                return KeyStroke.getKeyStroke("ctrl shift "+getCharForString(text));
            case MI_DELETE:
                return KeyStroke.getKeyStroke("ctrl shift "+getCharForString(text));
            case MI_CREATE:
                return KeyStroke.getKeyStroke("ctrl shift "+getCharForString(text));
            case MI_TORUS:
                return KeyStroke.getKeyStroke("ctrl shift "+getCharForString(text));
            case MI_ZOOM_IN:
                return KeyStroke.getKeyStroke("ctrl shift "+getCharForString(text));
            case MI_ZOOM_OUT:
                return KeyStroke.getKeyStroke("ctrl shift "+getCharForString(text));
            case MI_SAVE_XML:
                return KeyStroke.getKeyStroke("ctrl shift "+getCharForString(text));
            case MI_SAVE_SERIAL:
                return KeyStroke.getKeyStroke("ctrl shift "+getCharForString(text));
            case MI_PRINT:
                return KeyStroke.getKeyStroke("ctrl "+getCharForString(text));
            case MI_GIF:
                return KeyStroke.getKeyStroke("ctrl shift "+getCharForString(text));
            case MI_PNG:
                return KeyStroke.getKeyStroke("ctrl shift "+getCharForString(text));
            case MI_STEP:
                return KeyStroke.getKeyStroke("ctrl alt "+getCharForString(text));
            case MI_START:
                return KeyStroke.getKeyStroke("ctrl alt "+getCharForString(text));
            case MI_STOP:
                return KeyStroke.getKeyStroke("ctrl alt "+getCharForString(text));
            case MI_HELP:
                return KeyStroke.getKeyStroke("ctrl "+getCharForString(text));
            case MI_INFO:
                return KeyStroke.getKeyStroke("ctrl "+getCharForString(text));
        }
        return null;
    }
}
