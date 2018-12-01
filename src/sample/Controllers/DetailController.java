package sample.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.CellViews.TableCellView;
import sample.Handlers.TicketHandler;
import sample.Objects.Table;
import sample.Objects.TableReservation;
import sample.Objects.Ticket;


public class DetailController implements IHaveStage {
    private Stage stage;

    @FXML private Label table_reservation_name;

    @FXML Tab tab_tables_in_same_ticket;
    @FXML ListView lv_list_of_tickets;
    @FXML ListView lv_list_of_tables_in_same_ticket;

    @FXML Button btn_add_new_ticket;

    private final ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
    private final ObservableList<TableReservation> tableList = FXCollections.observableArrayList();

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

            ticket.addTableReservation(tableReservation);
            tableReservation.setTicket(ticket);

            TicketHandler ticketHandler = TicketHandler.getInstance();

            ticketHandler.AddTicket(ticket);

            event.consume();
            setupLayout(); // TODO
        });
    }
    private void setupLayout(){
        table_reservation_name.setText(tableReservation.getName());

        setupTickets();
        setupTables();
    }
    private void setupTickets(){
        TicketHandler ticketHandler = TicketHandler.getInstance();

        ticketList.removeAll();
        lv_list_of_tickets.refresh();
        ticketList.addAll(ticketHandler.getTickets());
        lv_list_of_tickets.setItems(ticketList);
    }
    private void setupTables(){
        if(tableReservation.getTicket() == null){
            tab_tables_in_same_ticket.setDisable(true);
            return;
        }
        tab_tables_in_same_ticket.setDisable(false);

        Table table = new Table();
        table.setName("Bla bla");
        tableList.addAll(new TableReservation(table, null), new TableReservation(table, null));
        lv_list_of_tables_in_same_ticket.setItems(tableList);
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
