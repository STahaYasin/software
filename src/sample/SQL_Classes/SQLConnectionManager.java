package sample.SQL_Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

/**
 * Handles the sql connection to the database
 */
public class SQLConnectionManager {

    private static final String HOSTNAME = "localhost";
    private static final String PORT = "3306";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "beydilli956";

    private static final String DATABASE = "software";

    /**
     *
     * @return Resturns the created sql connection when a connection is successfully established
     * @throws SQLException throws an exception if the server rejects the connection or when something goes wrong
     */
    public static Connection getConnection() throws SQLException{
        Properties properties = new Properties();
        properties.put("user", USERNAME);
        properties.put("password", PASSWORD);

        TimeZone timeZone = TimeZone.getTimeZone("brussels");
        TimeZone.setDefault(timeZone);

        return DriverManager.getConnection(getUrl(HOSTNAME, PORT, DATABASE), properties);
    }

    /**
     * Private method creates a url in string format
     * @param hostname
     * @param port
     * @param databasename
     * @return
     */
    private static String getUrl(String hostname, String port, String databasename){
        return "jdbc:mysql://" + hostname + ":" + port + "/" + databasename + "?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
    }

    /**
     * Coses the connection to the server. every connection that's established with the server needs to be closed correctly
     * @param connection the connection that needs to be closed.
     */
    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
