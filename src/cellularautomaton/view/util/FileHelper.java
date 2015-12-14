package cellularautomaton.view.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;

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
            Image img = ImageIO.read(getInputStream("img/icons/" + name));
            icon = new ImageIcon(img);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return icon;
    }

    public InputStream getInputStream(String name) {
        return FileHelper.class.getClassLoader().getResourceAsStream(name);
    }

    public static String getPath(File file) {
        return file.getPath().substring(0, file.getPath().lastIndexOf(File.separator));
    }

    public String getAutomataPath() {
        String filePath = "";
        try {
            File location = new File(FileHelper.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            if(location.isDirectory()) {
                filePath = location.getPath();
            } else {
                filePath = FileHelper.getPath(location);
                System.out.println(filePath);
            }
            location = new File(filePath + File.separator + "automata");
            filePath = location.getPath();
            if(!location.exists()) {
                location.mkdirs();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
