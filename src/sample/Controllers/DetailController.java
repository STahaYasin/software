package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetailController implements IHaveStage {
    private Stage stage;

    @FXML
    void initialize() {
        Platform.runLater(() ->{

        });

    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
