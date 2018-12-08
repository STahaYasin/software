package sample.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Handlers.ProductsManager;
import sample.Handlers.TicketHandler;
import sample.Objects.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;


public class DetailController implements IHaveStage {
    private Stage stage;
    private IOpenTableReservations iOpenTableReservations;

    @FXML private Label table_reservation_name, total_orders,total_price;

    @FXML TabPane tab_pane;
    @FXML Tab tab_tables_in_same_ticket;
    @FXML ListView lv_list_of_tickets, lv_list_of_tables_in_same_ticket;

    @FXML Button btn_add_new_ticket, start, stop, btn_checkout;

    @FXML ListView detail_products_list, detail_products_on_table_list;

    private ProductsManager productsManager;

    private ObservableList<Ticket> ticketList;
    private ObservableList<TableReservation> tableList;

    TableReservation tableReservation;

    public void setTableReservation(TableReservation tableReservation){
        this.tableReservation = tableReservation;
    }

    @FXML
    void initialize() {
        Platform.runLater(this::setup);
    }

    /**
     * To help with resetting the layout, the setup is encapsulated in setupLayout method.
     */
    private void setup(){
        setupProducts();
        setupLayout();

        btn_add_new_ticket.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            // TODO place ticket in the database, and use the id in the sugested name
            TextInputDialog textInputDialog = new TextInputDialog("Ticket Voorbeeld");

            textInputDialog.setTitle("Ticket name");
            textInputDialog.setContentText("Enter ticket name");

            Optional<String> result = textInputDialog.showAndWait();

            Ticket ticket = new Ticket();
            ticket.setName("Test");

            //ticket.addTableReservation(tableReservation);
            //tableReservation.setTicket(ticket);

            TicketHandler ticketHandler = TicketHandler.getInstance();
            ticketHandler.AddTicket(ticket);

            handleTicketMove(ticket, event);

            event.consume();
            setupLayout();
        });

        lv_list_of_tickets.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                Ticket selectedTicket = (Ticket) lv_list_of_tickets.getSelectionModel().getSelectedItem();
                System.out.println("Ticket selected: " + selectedTicket.getName());

                handleTicketMove(selectedTicket, event);
            }
        });

        lv_list_of_tables_in_same_ticket.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                final TableReservation selectedTable = (TableReservation) lv_list_of_tables_in_same_ticket.getSelectionModel().getSelectedItem();

                switch (event.getButton()){
                    case PRIMARY:{
                        if(event.getClickCount() == 2){
                            iOpenTableReservations.OpenTableReservation(selectedTable);
                            stage.close();
                        }
                    } break;
                    case SECONDARY:{

                        /*final ContextMenu contextMenu = new ContextMenu();
                        MenuItem menuItemGoToTable = new MenuItem("Go to table");
                        menuItemGoToTable.addEventHandler(MouseEvent.MOUSE_RELEASED, event1 -> {
                            System.out.println("Go to Table: " + selectedTable.getName());
                        });

                        contextMenu.getItems().addAll(menuItemGoToTable);
                        contextMenu.show(lv_list_of_tables_in_same_ticket, stage.getX() + event.getSceneX(), stage.getY() + event.getSceneY());*/
                    } break;
                }
            }
        });

        start.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1){
                tableReservation.startTableTimer();
            }
        });
        stop.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1){
                tableReservation.stopTableTimer();
                printTotalPrice();
            }
        });
    }
    private void handleTicketMove(Ticket selectedTicket, MouseEvent event){
        if(tableReservation.getTicket() != null){
            tableReservation.getTicket().removeTableReservation(tableReservation);
        }

        selectedTicket.addTableReservation(tableReservation);
        tableReservation.setTicket(selectedTicket);

        setupLayout();
        event.consume();
    }
    private void setupLayout(){
        table_reservation_name.setText(tableReservation.getName());
        total_orders.setAlignment(Pos.CENTER_RIGHT);
        setupTickets();
        setupTables();
        setupProductsOnTable();
    }
    private void setupProducts(){
        productsManager = ProductsManager.getInstance();

        ObservableList<Product> productsList = FXCollections.observableArrayList();
        productsList.addAll(productsManager.getProducts());

        detail_products_list.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                System.out.println(detail_products_list.getSelectionModel().getSelectedItem());

                Order order = new Order((Product) detail_products_list.getSelectionModel().getSelectedItem(), 1);

                tableReservation.AddOrder(order);

                setupLayout();
            }
        });

        detail_products_list.setItems(productsList);
    }
    private void setupProductsOnTable(){

        ObservableList<Order> productsList = FXCollections.observableArrayList();
        productsList.addAll(tableReservation.getOrders());

        detail_products_on_table_list.setItems(productsList);
        total_orders.setText("TOTAL: €" + tableReservation.getPriceOfOrders());
        printTotalPrice();
    }
    private void setupTickets(){
        TicketHandler ticketHandler = TicketHandler.getInstance();

        ticketList = FXCollections.observableArrayList();
        lv_list_of_tickets.refresh();
        ticketList.addAll(ticketHandler.getTickets());
        lv_list_of_tickets.setItems(ticketList);
    }
    private void setupTables(){
        if(tableReservation.getTicket() == null){
            tab_tables_in_same_ticket.setDisable(true);
            return;
        }

        tableList = FXCollections.observableArrayList();
        tab_tables_in_same_ticket.setDisable(false);

        tableList.addAll(tableReservation.getTicket().getTableReservations());
        lv_list_of_tables_in_same_ticket.setItems(tableList);

        SelectionModel<Tab> selectionModel = tab_pane.getSelectionModel();
        selectionModel.select(tab_tables_in_same_ticket);
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setOpenTableReservationListener(IOpenTableReservations iOpenTableReservations){
        this.iOpenTableReservations = iOpenTableReservations;
    }

    private void printTotalPrice(){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        total_price.setText("Price To Pay: " + numberFormat.format(tableReservation.getPrice()));
    }
}
