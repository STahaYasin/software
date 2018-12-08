package sample.Objects;

import sample.Handlers.ResetTableHandler;
import sample.Objects.Table;

import java.sql.SQLOutput;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

public class TableReservation {
    private Ticket ticket;
    private ResetTableHandler resetTableHandler;
    private ArrayList<Order> orders;

    private LocalDateTime startTime;
    private LocalDateTime stopTime;

    private Table table;

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }
    public Ticket getTicket() { return ticket; }

    public TableReservation(Table table, ResetTableHandler resetTableHandler){
        this.table = table;
        this.resetTableHandler = resetTableHandler;
        orders = new ArrayList<>();
    }
    public void startTableTimer(){
        //if(ticket == null) throw new TicketNotSetException("Tried to start table timer on a table without a ticket injected.");

        startTime = LocalDateTime.now();
        System.out.println(startTime.toString());
    }
    public void stopTableTimer(){
        if(startTime == null) throw new IllegalStateException("Cannot stop a timet that's not started");
        stopTime = LocalDateTime.now();
    }
    public long getSeconds(){
        if(startTime == null) return 0;
        //Period period = Period.between(LocalDate.parse(stopTime.toString()), LocalDate.parse(startTime.toString()));
        //System.out.println(period.);

        LocalDateTime a;
        if(stopTime == null) a = LocalDateTime.now();
        else a = stopTime;

        Duration duration = Duration.between(startTime, a);
        System.out.println(duration.getSeconds());

        return duration.getSeconds();
    }

    public String getName(){
        return "Reservation: " + table.getName();
    }
    public ArrayList<Order> getOrders(){ return orders; }

    public void AddOrder(Order order){
        if(orders == null) orders = new ArrayList<>();

        orders.add(order);
    }

    public class TicketNotSetException extends Exception{
        public TicketNotSetException(String s){ super(s); }
    }

    public double getPriceOfOrders(){
        double a = 0;

        for(Order order: orders) a += order.getPrice();

        return a;
    }

    public double getPrice(){
        double a = 0;

        a += getPriceOfOrders();

        a += ((double) getSeconds() / 60) * table.getPrice(); // By casting it to double we wil lose all seconds behind the full minutes

        return a;
    }

    @Override
    public String toString(){
        return table.toString() + " (" + String.valueOf(orders != null? orders.size(): 0) + " products)";
    }
}
