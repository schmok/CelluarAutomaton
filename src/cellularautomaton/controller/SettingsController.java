package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.gui.basicview.menu.CAJSettingsMenu;
import cellularautomaton.view.util.FileHelper;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Viktor Spadi on 18.01.2016.
 */
public class SettingsController extends AbstractController<CAJSettingsMenu,CellularAutomatonController> {
    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String table = "CellularAutomaton";
    private static String connStr = "";
    private static Connection conn = null;

    SettingsController(CAJSettingsMenu view, CellularAutomatonController parent) {
        super(view, parent);
    }

    static {
        createConnection();
    }

    private static void createConnection() {
        String basePath = FileHelper.getInstance().getAutomataPath()+File.separator+"derby"+File.separator;
        File dbDir = new File(basePath);
        if(!dbDir.exists()) {
            dbDir.mkdir();
        }
        connStr = "jdbc:derby:"+basePath+table+";";
        System.out.println(connStr);
        // get class
        try {
            Class.forName(driver).newInstance();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        // try connect, if not, create db
        try {
            conn = DriverManager.getConnection(connStr);
        } catch (SQLException e) {
            if(e.getErrorCode() == 40000) {
                try {
                    InputStream is = FileHelper.getInstance().getInputStream("create_settings.sql");
                    String createTable = FileHelper.convertStreamToString(is);
                    conn = DriverManager.getConnection(connStr+"create=true;");
                    Statement  stmt = conn.createStatement();
                    stmt.executeLargeUpdate(createTable);
                    stmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void closeConnection() {
        System.out.println("Closing connection");
        try {
            if(conn != null) {
                conn.close();
                DriverManager.getConnection(connStr+"shutdown=true");
            }
        } catch(SQLException sqlEx) {
            if(!sqlEx.getSQLState().contains("08006")) {
                sqlEx.printStackTrace();
                System.out.println(sqlEx.getSQLState());
            }
        }
    }

    public void loadSettings() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select settings_name from ca_settings");
            ArrayList<Object> tmpList = new ArrayList<>();
            while(results.next()) {
                tmpList.add(results.getString(1));
            }
            Object[] names = tmpList.toArray();
            String tmp = (String)JOptionPane.showInputDialog(this.getParent().getView().getFrame(), StringController.getInstance().get(StringEnumeration.W_SETTINGS_SAVED),
                    StringController.getInstance().get(StringEnumeration.W_SETTINGS_LOAD), JOptionPane.PLAIN_MESSAGE, null, names, null);
            if(tmp != null && tmp != "") {
                results = stmt.executeQuery("select * from ca_settings where settings_name = '" + tmp + "'");
                while(results.next()) {
                    this.getParent().getView().getFrame().setLocation(results.getInt(2), results.getInt(3));
                    this.getParent().getView().getFrame().setSize(results.getInt(4), results.getInt(5));
                    this.getParent().getPoppulationController().getView().setCellSize(results.getInt(6));
                    this.getModel().setSimInterval(results.getInt(7));
                }
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveSettings() {
        String inputValue = JOptionPane.showInputDialog(this.getParent().getView().getFrame(), StringController.getInstance().get(StringEnumeration.W_SETTINGS_NAME),
                StringController.getInstance().get(StringEnumeration.W_SETTINGS_SAVE), JOptionPane.PLAIN_MESSAGE);
        if(inputValue != null && inputValue != "") {
            System.out.println(inputValue);
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                String insertStatement = String.format("insert into ca_settings values('%s', %d, %d, %d, %d, %d, %d)",
                        inputValue,
                        (int) getParent().getView().getFrame().getLocation().getX(),
                        (int) getParent().getView().getFrame().getLocation().getY(),
                        getParent().getView().getFrame().getWidth(),
                        getParent().getView().getFrame().getHeight(),
                        getParent().getPoppulationController().getView().getCellSize(),
                        getModel().getSimInterval()
                        );
                stmt.execute(insertStatement);
                stmt.close();
            } catch(SQLException sqlEx) {
                if(sqlEx.getSQLState().contains("23505")) {
                    JOptionPane.showMessageDialog(null, StringController.getInstance().get(StringEnumeration.W_SETTINGS_DUB_ENTRY));
                } else {
                    sqlEx.printStackTrace();
                    System.out.println(sqlEx.getSQLState());
                }
            }
        }
    }

    public void deleteSettings() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select settings_name from ca_settings");
            ArrayList<Object> tmpList = new ArrayList<>();
            while(results.next()) {
                tmpList.add(results.getString(1));
            }
            Object[] names = tmpList.toArray();
            String tmp = (String)JOptionPane.showInputDialog(this.getParent().getView().getFrame(), StringController.getInstance().get(StringEnumeration.W_SETTINGS_SAVED),
                    StringController.getInstance().get(StringEnumeration.W_SETTINGS_DELETE), JOptionPane.PLAIN_MESSAGE, null, names, null);
            if(tmp != null && tmp != "") {
                stmt.execute("delete from ca_settings where settings_name = '" + tmp + "'");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringEnumeration enm = ((IOwnEnumeration)e.getSource()).getEnumeration();
        switch (enm) {
            default:
                System.out.println("Settingscontroller: "+enm);
        }
    }
}
