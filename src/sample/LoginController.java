package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import sample.Exceptions.IncorectPasswordException;
import sample.Exceptions.UserNotFoundException;
import sample.Handlers.PasswordHandler;
import sample.Main;
import javafx.stage.Stage;
import sample.Controllers.MainController;
import sample.Objects.User;

import javax.swing.*;
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

            goToDifferentView(user);
        }
        catch (UserNotFoundException e){
            sayUserNotFound();
        }
        catch (IncorectPasswordException e){
            sayInvalidPassword();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sayUserNotFound(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("");
        alert.setHeaderText("Login failed");
        alert.setContentText("No user found for this username");
        alert.showAndWait();
    }
    private void sayInvalidPassword(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("");
        alert.setHeaderText("Login failed");
        alert.setContentText("Incorrect username of password");
        alert.showAndWait();
    }

    public void goToDifferentView(User user) throws Exception{
        Stage stage;
        Parent root;

        stage = (Stage) btn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Views/main.fxml"));
        Scene scene = new Scene(root);
        scene.setUserData(user);
        stage.setScene(scene);
        stage.show();
    }
}