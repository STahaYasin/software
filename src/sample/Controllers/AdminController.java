package sample.Controllers;

import javafx.stage.Stage;
import sample.Objects.User;

public class AdminController implements IHaveStage {
    User user;

    public static void main(String[] args) {

    }

    @Override
    public void setStage(Stage stage) {

    }

    public void setUser(User user){
        this.user = user;
    }
}
