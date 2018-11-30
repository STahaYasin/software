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
import javafx.stage.StageStyle;
import sample.Exceptions.IncorectPasswordException;
import sample.Exceptions.UserNotFoundException;
import sample.Handlers.AlertHandler;
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

        boolean failed = false;

        if(un == null || un.isEmpty()){
            AlertHandler.ShowWarning("", "Login failed", "Username cannot leaved empty.");
            failed = true;
        }
        if(pw == null || pw.isEmpty()){
            AlertHandler.ShowWarning("", "Login failed", "Password field cannot leaved empty.");
            failed = true;
        }

        if(failed){
            // Doe de rest niet zolang de gebruiker zijn username en wachtwoord  ingeeft
            return;
        }

        try {
            String salt = User.getSalt(un);

            String hash = PasswordHandler.MakeHash(pw, salt);

            User user = User.Create(un, hash);

            System.out.println("Logged in");
            goToDifferentView(user);
        }
        catch (UserNotFoundException e){
            AlertHandler.ShowWarning("", "Login failed", "No user found for this username");
        }
        catch (IncorectPasswordException e){
            AlertHandler.ShowWarning("", "Login failed", "Incorrect username of password");
        }
        catch (Exception e) {
            AlertHandler.ShowError("Error!", "Exception", e.getMessage());
        }
    }

    private void goToDifferentView(User user) throws Exception{
        Stage stage;
        Parent root;
        stage = (Stage) btn.getScene().getWindow();
        //root = FXMLLoader.load(getClass().getResource("Views/main.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Views/main.fxml"));
        root = (Parent) fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setUser(user);
        mainController.setStage(stage);

        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);

        stage.show();

    }
    private  void opVenster() throws Exception{
        Stage stage;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/main.fxml"));
        stage = (Stage) btn.getScene().getWindow();

        Stage stage1 = new Stage(StageStyle.DECORATED);
        stage1.setScene(new Scene((Pane)loader.load()));
        MainController controller = loader.<MainController>getController();

        stage1.show();
    }
}