package sample;

import com.mysql.cj.Query;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.SQL_Classes.SQLConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            Connection connection = SQLConnectionManager.getConnection();

            Statement statement = null;
            String query = "SELECT * FROM names WHERE username = 'savrantaha'";

            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);

            while(res.next()){
                System.out.println("-\t id: " + String.valueOf(res.getInt("user_id")));
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
