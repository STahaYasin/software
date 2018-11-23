package sample.Objects;

import com.mysql.cj.xdevapi.SqlDataResult;
import sample.SQL_Classes.SQLConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Name {
    private int name_id;
    private int user_id;

    private String username;
    private String firstname;
    private String lastname;

    public String getUsername(){
        return username;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getLastName(){
        return lastname;
    }

    public void setUserName(String username){  this.username = username; }
    public void setFirstName(String firstName){ this.firstname = firstName; }
    public void setLastName(String lastName){ this.lastname = lastName; }
    public void setNameId(Integer nameId){ this. name_id = nameId; }

    public Name(Integer user_id){
        this.user_id = user_id;
    }

    public static Name Create(Integer user_id) throws SQLException {
        Connection connection = SQLConnectionManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet sqlName = statement.executeQuery("SELECT * FROM names WHERE user_id = " + user_id + " LIMIT 1");

        if(! sqlName.next()){
            connection.close();
            return null;
        }

        Name name = new Name(user_id);
        name.setUserName(sqlName.getString("username"));
        name.setFirstName(sqlName.getString("firstname"));
        name.setLastName(sqlName.getString("lastname"));
        name.setNameId(sqlName.getInt("name_id"));

        connection.close();
        return name;
    }
}
