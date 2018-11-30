package sample.Controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Objects.User;

import javax.jws.soap.SOAPBinding;
import javax.xml.soap.Text;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    User user ;
    Stage thisStage;

    @FXML private Label name;
    @FXML private Label username;
    @FXML private ImageView pool1,pool2,pool3,pool4,pool5,pool6,pool7,pool8,pool9,pool10;

   public MainController(){}


    private  void opVenster(MouseEvent event) throws Exception{
        Stage stage;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/main.fxml"));
        stage = (Stage) pool1.getScene().getWindow();

        Stage stage1 = new Stage(StageStyle.DECORATED);
        stage1.setScene(new Scene((Pane)loader.load()));

        stage1.show();



    }
    public void setUser(User user){
       this.user = user;
    }

    @FXML
    public void handle(MouseEvent event) throws Exception{
           if(event.getSource()== pool1) {
               System.out.printf("pool1");
               opVenster(event);
           }
    }

    @FXML
    void initialize() {
        Platform.runLater(() ->{
//            System.out.println(user.getName().getFirstname());

//            name.setText(user.getName().getFirstname() + " " + user.getName().getLastName());
//            username.setText(user.getName().getUsername() + " (" + user.getRole().getRoleName() + ")");
            name.setText("fatih kilic");
            username.setText("15: toogbediende");
        });

    }

    public void setStage(Stage stage) {
       thisStage = stage;
    }
}
