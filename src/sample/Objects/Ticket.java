package sample.Objects;

import java.util.ArrayList;

public class Ticket {
    private int ticket_id;

    private ArrayList<Order> orders;
    private ArrayList<TableReservation> tableReservations;

    public Ticket(){

    }

    public ArrayList<Order> getOrders(){
        return orders;
    }
    public void addProductToOrders(Product product){
        if(orders == null)
            orders = new ArrayList<>();

        orders.add(new Order(product));
    }

    public void addTable(Table table){
        // TODO do something with the table

        TableReservation tableReservation = new TableReservation(table);

        // TODO do something with the resevation
        // TODO start
        // TODO set start
        // TODO set timer

        this.tableReservations.add(tableReservation);
    }
    public ArrayList<TableReservation> getTableReservations(){
        return tableReservations;
    }
    public void stopTable(TableReservation reservation){
        reservation.stopTableTimer();
    }
}
