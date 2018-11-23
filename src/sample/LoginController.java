package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import sample.Handlers.PasswordHandler;
import sample.Main;
import javafx.stage.Stage;
import sample.Controllers.MainController;
import sample.Objects.User;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField username, password;
    @FXML
    private Button btn;


    @FXML
    public void login(ActionEvent event){
        String un, pw;

        un = username.getText();
        pw = password.getText();


        try {
            String salt = User.getSalt(un);

            String hash = PasswordHandler.MakeHash(pw, salt);
            User user = User.Create(un, hash);

            System.out.println(user.getName().getFirstname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sayUserNotFound(){


    }
    private void sayInvalidPassword(){

    }

    public void goToDifferentView(String a) throws Exception{
            Stage stage;
            Parent root;

            stage = (Stage) btn.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Views/main.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }


}