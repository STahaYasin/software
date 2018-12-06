package sample.Objects;

import sample.Handlers.ResetTableHandler;
import sample.Objects.Table;

import java.util.ArrayList;

public class TableReservation {
    private Ticket ticket;
    private ResetTableHandler resetTableHandler;
    private ArrayList<Order> orders;

    private Table table;

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }
    public Ticket getTicket() { return ticket; }

    public TableReservation(Table table, ResetTableHandler resetTableHandler){
        this.table = table;
        this.resetTableHandler = resetTableHandler;
        orders = new ArrayList<>();
        orders.add(new Order(new Product()));
    }
    private void startTableTimer() throws Exception{
        if(ticket == null) throw new TicketNotSetException("Tried to start table timer on a table without a ticket injected.");


    }
    public void stopTableTimer(){

    }

    public String getName(){
        return "Reservation: " + table.getName();
    }
    public ArrayList<Order> getOrders(){ return orders; }

    public class TicketNotSetException extends Exception{
        public TicketNotSetException(String s){ super(s); }
    }

    @Override
    public String toString(){
        return table.toString() + " (" + String.valueOf(orders != null? orders.size(): 0) + " products)";
    }
}
