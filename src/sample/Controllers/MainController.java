package sample.Controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Objects.User;

import javax.jws.soap.SOAPBinding;
import javax.xml.soap.Text;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    User user;
    Stage thisStage;

    @FXML private Label name;
    @FXML private Label username;

   public MainController(){
        /*this.user = user;

        Stage stage = StageHolder.getInstance().getStage();

        StageHolder stageHolder = StageHolder.getInstance();
        stageHolder.setStage(stage);

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Views/main.fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        stage.setTitle("Kassa systeem");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();

        System.out.println(user.getName().getFirstname());*/


    }

    public void setUser(User user){
       this.user = user;
    }

    @FXML
    void initialize() {
        Platform.runLater(() ->{
            System.out.println(user.getName().getFirstname());

            name.setText(user.getName().getFirstname() + " " + user.getName().getLastName());
            username.setText(user.getName().getUsername() + " (" + user.getRole().getRoleName() + ")");
        });
    }
}
