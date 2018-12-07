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
import sample.Handlers.ProductsManager;
import sample.Handlers.TableManager;
import sample.Handlers.TicketHandler;
import sample.Objects.TableReservation;
import sample.Objects.Ticket;
import sample.Objects.User;

public class MainController implements IHaveStage, IOpenTableReservations {
    User user ;
    Stage stage;

    @FXML private Label name;
    @FXML private Label username;
    @FXML private ImageView pool1, pool2, pool3, pool4, pool5, pool6, pool7, pool8, pool9, pool10;
    private ImageView[] imageViews;

    private ProductsManager productsManager;

    TableManager tableManager;

    public MainController(){
        productsManager = ProductsManager.getInstance();
    }

    public void setUser(User user){
        this.user = user;
    }

    public void OpenTableReservation(TableReservation tableReservation){
        try {
            openDetailWindow(tableReservation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        Platform.runLater(() ->{
            imageViews = new ImageView[] {pool1, pool2, pool3, pool4, pool5, pool6, pool7, pool8, pool9, pool10};
            tableManager = TableManager.getInstance();
            tableManager.setTableButtons(imageViews.length);

            TicketHandler ticketHandler = TicketHandler.getInstance();

            for(int i = 0; i < imageViews.length; i ++){
                ImageView imageView = imageViews[i];
                final int index = i;
                imageView.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
                    try{
                        TableReservation tableReservation = tableManager.getTableReservation(index);
                        openDetailWindow(tableReservation);
                    } catch (Exception e) {
                        System.out.println("Error with opening the window");
                        System.out.println(e.getMessage());
                    }
                });
            }

            name.setText(user.getName().getFirstname() + " " + user.getName().getLastName());
            username.setText(user.getName().getUsername() + " (" + user.getRole().getRoleName() + ")");
        });
    }
    private void openDetailWindow(TableReservation tableReservation) throws Exception{
        if(tableReservation == null){
            // TODO notify table reservation cannot be null

            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/detail.fxml"));

        Stage stage1 = new Stage(StageStyle.DECORATED);
        stage1.setScene(new Scene((Pane)loader.load()));
        DetailController detailController = loader.<DetailController>getController();
        detailController.setOpenTableReservationListener(this);
        ((IHaveStage) detailController).setStage(stage1);

        detailController.setTableReservation(tableReservation);

        stage1.show();
    }

    public void setStage(Stage stage) {
       this.stage = stage;
    }
}
