package sample.Controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Handlers.ProductsManager;
import sample.Handlers.TableManager;
import sample.Handlers.TableTimesShowUpdateHandler;
import sample.Handlers.TicketHandler;
import sample.Objects.TableReservation;
import sample.Objects.User;

public class MainController implements IHaveStage, IOpenTableReservations {
    User user ;
    Stage stage;

    @FXML private Label name;
    @FXML private Label username;
    @FXML private ImageView pool1, pool2, pool3, pool4, pool5, pool6, pool7, pool8, pool9, pool10;
    @FXML private Label lbl_1, lbl_2, lbl_3, lbl_4, lbl_5, lbl_6, lbl_7, lbl_8, lbl_9, lbl_10;
    @FXML private Button lock;
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
            final Thread mainThread = Thread.currentThread();

            Label[] labels = {lbl_1, lbl_2, lbl_3, lbl_4, lbl_5, lbl_6, lbl_7, lbl_8, lbl_9, lbl_10};

            for(Label label: labels){
                label.setText("");
            }

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
                        tableReservation.setLabel(labels[index]);
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Detail.fxml"));

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
    public void lock() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Lock.fxml"));
        Stage settingsStage = new Stage();
        settingsStage.setScene(new Scene((Pane)loader.load()));

        LockController lockController = loader.<LockController>getController();
        lockController.setPin(user.getPin());
        ((IHaveStage) lockController).setStage(settingsStage);
        settingsStage.setTitle("Settings");
        settingsStage.setResizable(false);
        settingsStage.initStyle(StageStyle.TRANSPARENT);
        settingsStage.initModality(Modality.APPLICATION_MODAL);
        settingsStage.showAndWait();
    }
}
