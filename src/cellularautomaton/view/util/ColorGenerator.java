package cellularautomaton.view.util;

import java.awt.*;
import java.util.TreeSet;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class ColorGenerator {
    public static Color generateColor(int count) {
        switch(count) {
            case 0:
                return Color.decode("0xFFFFFF");
            case 1:
                return Color.decode("0x000000");
        }
        int ran = (int)(Math.random() * 10);
        return Color.getHSBColor((float)((count * ran * 36) % 360) / 360.0f, ((float)Math.random()/2)+0.5f, ((float)Math.random()/2)+0.5f);
    }
}
