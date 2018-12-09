package sample.Objects;

import sample.Exceptions.IncorectPasswordException;
import sample.Exceptions.UserNotFoundException;
import sample.SQL_Classes.SQLConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The User object in the data structure
 *
 * The user object is able to compare a password hash with the one in the database. The object does not hold the password hash in it.
 */
public class User {
    private int user_id;

    private Name name;
    private Role role;

    private String pin;

    public int getUser_id(){ return user_id; }
    public Name getName(){ return name; }
    public Role getRole(){ return role; }
    private void setPin(String pin){ this.pin = pin; }
    public String getPin() {return pin; }

    public User(Integer userId) throws SQLException{
        this.user_id = userId;

        Connection connection = SQLConnectionManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet sqlUser = statement.executeQuery("SELECT * FROM users WHERE user_id = " + user_id + " LIMIT 1");

        if(sqlUser.next()){
            int a = sqlUser.getInt("role_id");
            role = Role.Create(sqlUser.getInt("role_id"));
        }

        name = Name.Create(user_id);
    }

    /**
     * Creates the user object by getting its data from the
     * @param username
     * @param hash
     * @return
     * @throws Exception
     */
    public static User Create(String username, String hash) throws Exception{
        Connection connection = SQLConnectionManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet sqlUserId = statement.executeQuery("SELECT user_id FROM names WHERE username = '" + username + "' LIMIT 1");

        if(! sqlUserId.next()){
            connection.close();
            throw new UserNotFoundException();
        }

        int userId = sqlUserId.getInt("user_id");

        ResultSet sqlHash = statement.executeQuery("SELECT hash, pin FROM passwords WHERE user_id = " + userId + " LIMIT 1");

        if(! sqlHash.next()){
            connection.close();
            throw new UserNotFoundException();
        }

        if(! sqlHash.getString("hash").equals(hash)){
            connection.close();
            throw new IncorectPasswordException();
        }

        User user = new User(userId);
        user.setPin(sqlHash.getString("pin"));

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
