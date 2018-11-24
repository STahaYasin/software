package sample.SQL_Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

public class SQLConnectionManager {

    public static final String HOSTNAME = "localhost";
    public static final String PORT = "3306";

    private static final String USERNAME = "software";
    private static final String PASSWORD = "software_123";

    private static final String DATABASE = "software";

    public static Connection getConnection() throws SQLException{
        Properties properties = new Properties();
        properties.put("user", USERNAME);
        properties.put("password", PASSWORD);

        TimeZone timeZone = TimeZone.getTimeZone("brussels");
        TimeZone.setDefault(timeZone);

        return DriverManager.getConnection(getUrl(HOSTNAME, PORT, DATABASE), properties);
    }
    private static String getUrl(String hostname, String port, String databasename){
        return "jdbc:mysql://" + hostname + ":" + port + "/" + databasename + "?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
    }
    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
