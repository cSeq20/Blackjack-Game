
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Class which establishes a connection to the database. Has methods to add to
 * and display the database.
 *
 * @author Chris Sequeira & Kelvin Raju
 */
public class Highscore {

    public static Connection conn;
    public static String url = "jdbc:derby://localhost:1527/Highscore";
    public static String userName = "blackjack";
    public static String password = "blackjack";
    public static Statement statement;

    /**
     * Method to establish the connection to the database.
     */
    public void connectHighscoreDB() {
        try {
            conn = DriverManager.getConnection(url, userName, password);
            statement = conn.createStatement();
            System.out.println(url + " connected");
        } catch (SQLException e) {
            Logger.getLogger(Highscore.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Method to add player name and money to score list database.
     *
     * @param fname of the player
     * @param lname of the player
     * @param money remaining at the end of game.
     */
    public void add(String fname, String lname, int money) {
        String query = "INSERT INTO HIGHSCORE " + " VALUES ('" + fname + "', '" + lname + "', " + money + ")";
        try {
            statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Highscore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Displays the scores in a new JFrame.
     */
    public void display() {

        JFrame scores = new JFrame("Scores");
        scores.setSize(700, 600);
        JTable hs = new JTable();
        hs.setBounds(65, 107, 575, 428);

        try {

            String query = "SELECT * FROM HIGHSCORE ORDER BY (MONEY) DESC";
            ResultSet rs = statement.executeQuery(query);

            ResultSetMetaData data = rs.getMetaData();
            int columns = data.getColumnCount();

            DefaultTableModel tabMod = new DefaultTableModel();
            Vector column_names = new Vector();
            Vector data_rows = new Vector();

            for (int i = 1; i <= columns; i++) {
                column_names.addElement(data.getColumnName(i));
            }
            tabMod.setColumnIdentifiers(column_names);

            while (rs.next()) {
                data_rows = new Vector();
                for (int j = 1; j <= columns; j++) {
                    data_rows.addElement(rs.getString(j));
                }
                tabMod.addRow(data_rows);
            }

            hs.setModel(tabMod);
            scores.add(hs);

            scores.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(Highscore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
