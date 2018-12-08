package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import sample.Exceptions.IncorectPasswordException;
import sample.Exceptions.UserNotFoundException;
import sample.Handlers.AlertHandler;
import sample.Handlers.PasswordHandler;
import javafx.stage.Stage;
import sample.Objects.User;


public class LoginController implements IHaveStage {
    Stage stage;

    @FXML
    private TextField username, password;
    @FXML
    private Button btn;


    @FXML
    public void login(ActionEvent event) throws Exception {
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

        User user = null;

        try {
            String salt = User.getSalt(un);

            String hash = PasswordHandler.MakeHash(pw, salt);

            user = User.Create(un, hash);

            if(user == null){
                AlertHandler.ShowWarning("", "Login failed", "Bruhhh");
                return;
            }

            switch (user.getRole().getRoleCode().toLowerCase()){
                case "ad": {
                    goToAdminView(user);
                } break;
                case "ob": {
                    goToTablesView(user);
                } break;
            }
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

    private void goToTablesView(User user) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/main.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setUser(user);
        ((IHaveStage) mainController).setStage(stage);

        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);

        stage.show();
    }
    private void goToAdminView(User user) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Admin.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        AdminController adminController = fxmlLoader.getController();
        adminController.setUser(user);
        ((IHaveStage) adminController).setStage(stage);

        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);

        stage.show();
    }

    public void setStage(Stage stage) { this.stage = stage; }
}