package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Handlers.AlertHandler;
import sample.Handlers.TicketHandler;

import java.util.ArrayList;

public class LockController implements IHaveStage {
    Stage thisStage;

    ArrayList<Integer> pressedNumbers = new ArrayList<>();

    @FXML
    Button nm1,nm2,nm3,nm4,nm5,nm6,nm7,nm8,nm9,nm0, nmclr;

    @FXML
    CheckBox ch_1, ch_2, ch_3, ch_4;

    Button[] buttons;

    private String pin;

    public static void main(String[] args) {

    }

    @FXML
    void initialize() {
        Platform.runLater(() ->{
            buttons = new Button[] {nm1,nm2,nm3,nm4,nm5,nm6,nm7,nm8,nm9,nm0};
            setup();

            for(int i = 0; i < buttons.length; i ++){
                Button button = buttons[i];

                final int number = (i + 1) % 10;
                //if(number == 10) number = 0;

                button.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
                    if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1){ // Aantal klikken die je wilt "2"
                        numberPressed(number);
                    }
                });
            }

            nmclr.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
                if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1){
                    pressedNumbers = new ArrayList<>();
                    setupLayout();
                }
            });
        });
    }

    private void setup(){
        ch_1.setDisable(true);
        ch_2.setDisable(true);
        ch_3.setDisable(true);
        ch_4.setDisable(true);

        setupLayout();
    }
    private void setupLayout(){
        CheckBox[] checkBoxes = {ch_1, ch_2, ch_3, ch_4};

        for(CheckBox checkBox: checkBoxes){
            checkBox.setSelected(false);
        }

        for(int i = 0; i < pressedNumbers.size(); i ++){
            checkBoxes[i].setSelected(true);
        }
    }

    private void numberPressed(int number){
        if(pressedNumbers == null) pressedNumbers = new ArrayList<>();

        pressedNumbers.add(number);
        setupLayout();

        if(pressedNumbers.size() == 4){ // bv4 hier lengte van pin aanpassen
            StringBuilder stringBuilder = new StringBuilder();

            for(Integer i: pressedNumbers){
                stringBuilder.append(String.valueOf(i));
            }
            String code = stringBuilder.toString();

            if(code.equals(pin)){
                thisStage.close();
            }
            else{
                AlertHandler.ShowWarning("Wrong pin code", "Wrong pin code", "Please try again to unlock the system.");
            }
            pressedNumbers = new ArrayList<>();
            setupLayout();
        }
    }

    @Override
    public void setStage(Stage stage) {
        thisStage = stage;
    }

    public void setPin(String pin){
        this.pin = pin;
    }
}
