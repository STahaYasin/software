package sample;

import sample.SQL_Classes.SQLConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private int user_id;

    private Name name;
    private Password password;
    private Role role;

    public static String getSalt(String username) throws SQLException {
        Connection connection = SQLConnectionManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resId = statement.executeQuery("SELECT user_id FROM names WHERE username = '" + username + "' LIMIT 1");

        //int user_id = resId.next()

        //while (user_id_statement.)

        return null;
    }
}
