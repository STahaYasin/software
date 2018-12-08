package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Handlers.TicketHandler;

import java.util.ArrayList;

public class LockController implements IHaveStage {
    Stage thisStage;

    ArrayList<Integer> pressedNumbers = new ArrayList<>();

    @FXML
    Button nm1,nm2,nm3,nm4,nm5,nm6,nm7,nm8,nm9,nm0;

    Button[] buttons;

    public static void main(String[] args) {

    }

    @FXML
    void initialize() {
        Platform.runLater(() ->{
            buttons = new Button[] {nm1,nm2,nm3,nm4,nm5,nm6,nm7,nm8,nm9,nm0};

            TicketHandler ticketHandler = TicketHandler.getInstance();

            for(int i = 0; i < buttons.length; i ++){
                Button button = buttons[i];

                final int number = i + 1;

                button.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
                    if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1){ // Aantal klikken die je wilt "2"
                        numberPressed(number);
                    }
                });

            }

//            name.setText(user.getName().getFirstname() + " " + user.getName().getLastName());
            //          username.setText(user.getName().getUsername() + " (" + user.getRole().getRoleName() + ")");
        });
    }
    private void numberPressed(int number){
        if(pressedNumbers == null) pressedNumbers = new ArrayList<>();

        pressedNumbers.add(number);

        if(pressedNumbers.size() == 4){ // bv4 hier lengte van pin aanpassen
            StringBuilder stringBuilder = new StringBuilder();
            for(Integer i: pressedNumbers){
                stringBuilder.append(String.valueOf(i));
                String code = stringBuilder.toString();

                if(code.equals("3169")){
                    System.out.println("ok");

                    // Doe iets
                    thisStage.close();
                }
                pressedNumbers = new ArrayList<>();
            }
        }
    }

    @Override
    public void setStage(Stage stage) {
        thisStage = stage;
    }
}
