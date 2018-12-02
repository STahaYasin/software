package sample.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.CellViews.TableCellView;
import sample.Handlers.TicketHandler;
import sample.Objects.Table;
import sample.Objects.TableReservation;
import sample.Objects.Ticket;


public class DetailController implements IHaveStage {
    private Stage stage;
    private IOpenTableReservations iOpenTableReservations;

    @FXML private Label table_reservation_name;

    @FXML TabPane tab_pane;
    @FXML Tab tab_tables_in_same_ticket;
    @FXML ListView lv_list_of_tickets;
    @FXML ListView lv_list_of_tables_in_same_ticket;

    @FXML Button btn_add_new_ticket;

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
        setupLayout();

        btn_add_new_ticket.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Ticket ticket = new Ticket();
            ticket.setName("Test");

            // TODO voordat deze tableReservation object in de list ..
            // TODO van de nieuwe ticket word gestoken moet verwijderd worden uit de oude ticket

            ticket.addTableReservation(tableReservation);
            tableReservation.setTicket(ticket);

            TicketHandler ticketHandler = TicketHandler.getInstance();
            ticketHandler.AddTicket(ticket);

            event.consume();
            setupLayout();
        });

        lv_list_of_tickets.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                Ticket selectedTicket = (Ticket) lv_list_of_tickets.getSelectionModel().getSelectedItem();
                System.out.println("Ticket selected: " + selectedTicket.getName());

                if(tableReservation.getTicket() != null){
                    tableReservation.getTicket().removeTableReservation(tableReservation);
                }

                selectedTicket.addTableReservation(tableReservation);
                tableReservation.setTicket(selectedTicket);

                setupLayout();
                event.consume();
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
    }
    private void setupLayout(){
        table_reservation_name.setText(tableReservation.getName());

        setupTickets();
        setupTables();
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

        Table table = new Table();
        table.setName("Bla bla");
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
}
