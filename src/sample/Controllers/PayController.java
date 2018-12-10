package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Objects.Payable;
import sample.Objects.TableReservation;
import sample.Objects.Ticket;

public class PayController implements IHaveStage {
    private Stage thisStage;
    private OnPayListener onPayListener;
    private Payable payable;
    private boolean payyingTheWholeTicket;

    private double price;
    private double moneyReceived;

    @FXML
    Button ok_button;

    @Override
    public void setStage(Stage stage) {
        thisStage = stage;
    }

    public void setPrice(double price){
        this.price = price;
    }
    public void setOnPayListener(OnPayListener onPayListener, boolean payyingTheWholeTicket){
        this.onPayListener = onPayListener;
    }
    public void setOnPayListener(OnPayListener onPayListener){
        this.onPayListener = onPayListener;
    }
    public void setPayable(Payable payable){
        this.payable = payable;
        this.payyingTheWholeTicket = payyingTheWholeTicket;
    }

    public PayController(){
        price = 0;
        moneyReceived = 0;
    }

    @FXML
    void initialize() {
        Platform.runLater(() ->{
            ok_button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                payed();
            });
        });
    }
    private void payed(){
        onPayListener.OnPayed(payable);

        thisStage.close();
    }
}