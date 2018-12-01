package sample;

import com.mysql.cj.Query;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.StageHolder;
import sample.Objects.User;
import sample.SQL_Classes.SQLConnectionManager;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    private static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        StageHolder stageHolder = StageHolder.getInstance();
        stageHolder.setStage(primaryStage);

        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Kassa systeem");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
