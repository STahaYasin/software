package sample.Objects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TableKind {
    private double pricePerMin;
    private String name;
    private int kind_id;

    public TableKind(int kind_id, Connection connection) throws Exception{
        this.kind_id = kind_id;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM table_kinds WHERE table_kinds.table_kind_id = 1 LIMIT 1");

        if(resultSet.next()){
            name = resultSet.getString("kind_name");
            pricePerMin = resultSet.getDouble("price");
        }
    }

    public double getPrice(){
        return pricePerMin;
    }
}
