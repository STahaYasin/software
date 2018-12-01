package sample.Objects;

import sample.Handlers.ResetTableHandler;
import sample.Objects.Table;

public class TableReservation {
    private Ticket ticket;
    private ResetTableHandler resetTableHandler;

    private Table table;

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }

    public TableReservation(Table table, ResetTableHandler resetTableHandler){
        this.table = table;
        this.resetTableHandler = resetTableHandler;
    }
    private void startTableTimer() throws Exception{
        if(ticket == null) throw new TicketNotSetException("Tried to start table timer on a table without a ticket injected.");


    }
    public void stopTableTimer(){

    }

    public String getName(){
        return "Reservation: " + table.getName();
    }

    public class TicketNotSetException extends Exception{
        public TicketNotSetException(String s){ super(s); }
    }
}
