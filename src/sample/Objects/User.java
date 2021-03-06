package sample.Objects;

import sample.Exceptions.IncorectPasswordException;
import sample.Exceptions.UserNotFoundException;
import sample.SQL_Classes.SQLConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private int user_id;

    private Name name;
    private Role role;

    public int getUser_id(){ return user_id; }
    public Name getName(){ return name; }
    public Role getRole(){ return role; }

    public User(Integer userId) throws SQLException{
        this.user_id = userId;

        Connection connection = SQLConnectionManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet sqlUser = statement.executeQuery("SELECT * FROM users WHERE user_id = " + user_id + " LIMIT 1");

        if(sqlUser.next()){
            role = Role.Create(sqlUser.getInt("role_id"));
        }

        name = Name.Create(user_id);
    }
    public static User Create(String username, String hash) throws Exception{
        Connection connection = SQLConnectionManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet sqlUserId = statement.executeQuery("SELECT user_id FROM names WHERE username = '" + username + "' LIMIT 1");

        if(! sqlUserId.next()){
            connection.close();
            throw new UserNotFoundException();
        }

        int userId = sqlUserId.getInt("user_id");

        ResultSet sqlHash = statement.executeQuery("SELECT hash FROM passwords WHERE user_id = " + userId + " LIMIT 1");

        if(! sqlHash.next()){
            connection.close();
            throw new UserNotFoundException();
        }

        if(! sqlHash.getString("hash").equals(hash)){
            connection.close();
            throw new IncorectPasswordException();
        }

        User user = new User(userId);
        connection.close();
        return user;
    }

    public static String getSalt(String username) throws Exception {
        Connection connection = SQLConnectionManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resId = statement.executeQuery("SELECT user_id FROM names WHERE username = '" + username + "' LIMIT 1");

        if(! resId.next()){
            connection.close();
            throw new UserNotFoundException();
        }

        int user_id = resId.getInt("user_id");

        ResultSet resSalt = statement.executeQuery("SELECT salt FROM passwords WHERE user_id = " + user_id + " LIMIT 1");

        if(! resSalt.next()){
            connection.close();
            throw new UserNotFoundException();
        }

        String salt = resSalt.getString("salt");
        connection.close();
        return salt;
    }
}
