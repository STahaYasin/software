package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Handlers.MoneyFormatHandler;
import sample.Objects.Payable;
import sample.Objects.TableReservation;
import sample.Objects.Ticket;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class PayController implements IHaveStage {
    private Stage thisStage;
    private OnPayListener onPayListener;
    private Payable payable;
    private boolean payyingTheWholeTicket;

    private BigDecimal moneyReceived;

    @FXML
    Button ok_button;

    @FXML
    Button e100, e50, e20, e10, e5, e2, e1, c50, c20, c10, c5, c2, c1;

    private BigDecimal[] values = {new BigDecimal(100), new BigDecimal(50), new BigDecimal(20), new BigDecimal(10), new BigDecimal(5), new BigDecimal(2), new BigDecimal(1), new BigDecimal(0.5), new BigDecimal(0.2), new BigDecimal(0.1), new BigDecimal(0.05), new BigDecimal(0.02), new BigDecimal(0.01)};

    @FXML
    Label pay_bedrag, lbl_received_money, pay_terug;

    @Override
    public void setStage(Stage stage) {
        thisStage = stage;
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
        moneyReceived = new BigDecimal(0);
    }

    @FXML
    void initialize() {
        Platform.runLater(() ->{
            setup();
        });
    }
    private void setup(){
        pay_bedrag.setText("Amount payable: " + MoneyFormatHandler.getAsText(payable.getPrice()));

        Button[] buttons = {e100, e50, e20, e10, e5, e2, e1, c50, c20, c10, c5, c2, c1};

        for(int i = 0; i < buttons.length; i ++){
            final int index = i;
            buttons[i].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                addValue(values[index]);
            });
        }

        ok_button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            payed();
        });

        setupLayout();
    }
    private void addValue(BigDecimal value){
        moneyReceived = moneyReceived.add(value);
        setupLayout();
    }
    private void setupLayout(){
        lbl_received_money.setText(MoneyFormatHandler.getAsText(moneyReceived.doubleValue()));
        BigDecimal difference = moneyReceived.subtract(new BigDecimal(payable.getPrice()));
        pay_terug.setText(MoneyFormatHandler.getAsText(difference.compareTo(new BigDecimal(0)) > 0? difference.doubleValue(): 0));
        ok_button.setDisable(moneyReceived.compareTo(new BigDecimal(payable.getPrice())) < 0);
    }
    private void payed(){
        onPayListener.OnPayed(payable);

        thisStage.close();
    }
}