package sample.Objects;

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

        if(! resId.next()) return null;

        int user_id = resId.getInt("user_id");

        ResultSet resSalt = statement.executeQuery("SELECT salt FROM passwords WHERE user_id = " + user_id + " LIMIT 1");

        if(! resSalt.next()) return null;
        return resSalt.getString("salt");
    }
}
