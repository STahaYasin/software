package sample.Objects;

import java.util.ArrayList;

public class Ticket {
    private int ticket_id;

    private ArrayList<Order> orders;
    private ArrayList<TableReservation> tableReservations;

    private String name;

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public Ticket(){
        name = "Unnamed ticket";
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
        /**
         * Adding this (ticket) to the table, this helps us with finding the ticket if the table is added to a ticket
         */
        //TableReservation tableReservation = new TableReservation(table);
        //tableReservation.setTicket(this);

        // TODO do something with the resevation
        // TODO start
        // TODO set start
        // TODO set timer

        //this.tableReservations.add(tableReservation);
    }
    public void removeTableReservation(TableReservation tableReservation){
        if(tableReservations == null || tableReservations.size() == 0) return;

        tableReservations.remove(tableReservation);
    }
    public void addTableReservation(TableReservation tableReservation){
        if(tableReservations == null) tableReservations = new ArrayList<>();

        tableReservations.add(tableReservation);
    }
    public ArrayList<TableReservation> getTableReservations(){
        return tableReservations;
    }
    public void stopTable(TableReservation reservation){
        reservation.stopTableTimer();
    }
}