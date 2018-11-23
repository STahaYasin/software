package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.Handlers.PasswordHandler;
import sample.Objects.User;
import sample.SQL_Classes.SQLConnectionManager;

public class LoginController {
    @FXML
    private TextField username, password;

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
}