package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Objects.TableReservation;

public class DetailController implements IHaveStage {
    private Stage stage;

    @FXML private Label table_reservation_name;

    TableReservation tableReservation;

    public void setTableReservation(TableReservation tableReservation){
        this.tableReservation = tableReservation;
    }

    @FXML
    void initialize() {
        Platform.runLater(() ->{
            table_reservation_name.setText(tableReservation.getName());
        });

    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
