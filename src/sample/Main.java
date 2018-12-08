package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.IHaveStage;
import sample.Controllers.LoginController;
import sample.Controllers.StageHolder;

public class Main extends Application {

    private static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        StageHolder stageHolder = StageHolder.getInstance();
        stageHolder.setStage(primaryStage);

        window = primaryStage;
        //Parent root = FXMLLoader.load(getClass().getResource("Views/Login.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Views/Login.fxml"));
        Parent root = fxmlLoader.load();

        LoginController loginController = fxmlLoader.<LoginController>getController();
        ((IHaveStage) loginController).setStage(primaryStage);

        primaryStage.setTitle("Kassa systeem");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
