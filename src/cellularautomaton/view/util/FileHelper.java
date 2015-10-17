package cellularautomaton.view.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Viktor Spadi on 17.10.2015.
 */
public class FileHelper {
    private static FileHelper fileHelper;
    private String basePath;

    public static FileHelper getInstance() {
        if(fileHelper == null)
            fileHelper = new FileHelper();
        return fileHelper;
    }

    private FileHelper() {
        this.basePath = System.getProperty("user.dir");
    }

    public ImageIcon getIcon(String name) {
        ImageIcon icon = null;
        try {
            Image img = ImageIO.read(new File(this.basePath+"/res/img/icons/"+name));
            icon = new ImageIcon(img);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return icon;
    }
}
