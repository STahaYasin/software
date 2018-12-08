package sample.Objects;

import sample.SQL_Classes.SQLConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {
    private String name;
    private TableKind tableKind;

    public Table(int id) throws Exception{
        Connection connection = SQLConnectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tables WHERE tables.table_id = " + id + " LIMIT 1");

        if(resultSet.next()){
            name = resultSet.getString("label");
            tableKind = new TableKind(resultSet.getInt("kind_id"), connection);
        }

        connection.close();
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public double getPrice(){
        if(tableKind == null) throw new IllegalStateException("No kind defined for the selected table");
        return tableKind.getPrice();
    }
}
