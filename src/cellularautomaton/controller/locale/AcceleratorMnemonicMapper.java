package cellularautomaton.controller.locale;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by Viktor Spadi on 14.10.2015.
 */
public class AcceleratorMnemonicMapper {
    public int getMnemonic(StringEnumeration text) {
        switch(text) {
            case MB_AUTOMATON:
                return KeyEvent.VK_N;
            case MI_NEW:
                return KeyEvent.VK_N;
            case MI_LOAD:
                return KeyEvent.VK_L;
            case MI_EDITOR:
                return KeyEvent.VK_E;
            case MI_QUIT:
                return KeyEvent.VK_B;
            case MB_POPULATION:
                return KeyEvent.VK_P;
            case MI_CHANGE_SIZE:
                return KeyEvent.VK_G;
            case MI_DELETE:
                return KeyEvent.VK_L;
            case MI_CREATE:
                return KeyEvent.VK_E;
            case MI_TORUS:
                return KeyEvent.VK_T;
            case MI_ZOOM_IN:
                return KeyEvent.VK_V;
            case MI_ZOOM_OUT:
                return KeyEvent.VK_E;
            case MS_SAVE:
                return KeyEvent.VK_S;
            case MS_LOAD:
                return KeyEvent.VK_A;
            case MI_SAVE_XML:
                return KeyEvent.VK_X;
            case MI_SAVE_SERIAL:
                return KeyEvent.VK_S;
            case MI_PRINT:
                return KeyEvent.VK_D;
            case MS_SAVE_AS_IMG:
                return KeyEvent.VK_R;
            case MI_GIF:
                return KeyEvent.VK_G;
            case MI_PNG:
                return KeyEvent.VK_P;
            case MB_SIMULATION:
                return KeyEvent.VK_S;
            case MI_STEP:
                return KeyEvent.VK_S;
            case MI_START:
                return KeyEvent.VK_A;
            case MI_STOP:
                return KeyEvent.VK_O;
            case MB_HELP:
                return KeyEvent.VK_H;
            case MI_HELP:
                return KeyEvent.VK_H;
            case MI_INFO:
                return KeyEvent.VK_I;
        }
        return KeyEvent.VK_SPACE;
    }

    public KeyStroke getAccelerator(StringEnumeration text) {
        switch(text) {
            case MI_LOAD:
                return KeyStroke.getKeyStroke("ctrl L");
            case MI_NEW:
                return KeyStroke.getKeyStroke("ctrl N");
            case MI_EDITOR:
                return KeyStroke.getKeyStroke("ctrl E");
            case MI_QUIT:
                return KeyStroke.getKeyStroke("ctrl Q");
            case MI_CHANGE_SIZE:
                return KeyStroke.getKeyStroke("ctrl shift G");
            case MI_DELETE:
                return KeyStroke.getKeyStroke("ctrl shift C");
            case MI_CREATE:
                return KeyStroke.getKeyStroke("ctrl shift E");
            case MI_TORUS:
                return KeyStroke.getKeyStroke("ctrl shift T");
            case MI_ZOOM_IN:
                return KeyStroke.getKeyStroke("ctrl shift I");
            case MI_ZOOM_OUT:
                return KeyStroke.getKeyStroke("ctrl shift O");
            case MI_SAVE_XML:
                return KeyStroke.getKeyStroke("ctrl shift X");
            case MI_SAVE_SERIAL:
                return KeyStroke.getKeyStroke("ctrl shift R");
            case MI_PRINT:
                return KeyStroke.getKeyStroke("ctrl P");
            case MI_GIF:
                return KeyStroke.getKeyStroke("ctrl shift F");
            case MI_PNG:
                return KeyStroke.getKeyStroke("ctrl shift P");
            case MI_STEP:
                return KeyStroke.getKeyStroke("ctrl alt S");
            case MI_START:
                return KeyStroke.getKeyStroke("ctrl alt A");
            case MI_STOP:
                return KeyStroke.getKeyStroke("ctrl alt O");
            case MI_HELP:
                return KeyStroke.getKeyStroke("ctrl H");
            case MI_INFO:
                return KeyStroke.getKeyStroke("ctrl A");
        }
        return null;
    }
}
