package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Objects.User;

public class MainController implements IHaveStage {
    User user ;
    Stage stage;

    @FXML private Label name;
    @FXML private Label username;
    @FXML private ImageView pool1,pool2,pool3,pool4,pool5,pool6,pool7,pool8,pool9,pool10;

   public MainController(){}


    private  void opVenster(MouseEvent event) throws Exception{


        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/detail.fxml"));


        Stage stage1 = new Stage(StageStyle.DECORATED);
        stage1.setScene(new Scene((Pane)loader.load()));
        DetailController controller = loader.<DetailController>getController();
        ((IHaveStage) controller).setStage(stage);


        stage1.show();
    }
    public void setUser(User user){
       this.user = user;
    }

    @FXML
    public void handle(MouseEvent event) throws Exception{
       System.out.println("Clicked");

           if(event.getSource()== pool1) {
               System.out.printf("pool1");
               opVenster(event);
           }
        if(event.getSource()== pool2) {
            System.out.printf("pool2");
            //opVenster(event);
        }
    }

    @FXML
    void initialize() {
        Platform.runLater(() ->{
//            System.out.println(user.getName().getFirstname());

//            name.setText(user.getName().getFirstname() + " " + user.getName().getLastName());
//            username.setText(user.getName().getUsername() + " (" + user.getRole().getRoleName() + ")");
            name.setText("fatih kilic");
            username.setText("15: toogbediende");
        });

    }

    public void setStage(Stage stage) {
       this.stage = stage;
    }
}
