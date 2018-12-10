package sample.Objects;

import sample.Handlers.ResetTableHandler;

import java.util.ArrayList;

public class TableReservation implements Payable {
    private Ticket ticket;
    private ResetTableHandler resetTableHandler;
    private ArrayList<Order> orders;

    private ArrayList<StartStopHolder> times;

    private Table table;

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }
    public Ticket getTicket() { return ticket; }
    public ArrayList<StartStopHolder> getTimes(){ return this.times; }

    public TableReservation(Table table, ResetTableHandler resetTableHandler){
        this.table = table;
        this.resetTableHandler = resetTableHandler;
        orders = new ArrayList<>();
        times = new ArrayList<>();
    }
    public void startTableTimer(){
        if(times == null) times = new ArrayList<>();

        if(times.isEmpty() || !times.get(times.size() - 1).isRunning()){
            times.add(new StartStopHolder());
        }
        else{
            System.out.println("First stop the running timer");
        }
    }
    public void stopTableTimer(){
        if(times == null || times.isEmpty()) return;

        StartStopHolder startStopHolder = times.get(times.size() - 1);

        if(startStopHolder.isRunning()){
            startStopHolder.Stop();
            System.out.println("Timer stopped");
        }
        else{
            System.out.println("Timer already stopped, start another one before stopping it.");
        }
    }
    public long getSeconds(){
        long a = 0;

        for(StartStopHolder startStopHolder: times){
            if(startStopHolder == null) continue;
            a += startStopHolder.getTime();
        }

        return a;
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

    @Override
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

    public void Delete(){
        resetTableHandler.ResetTable();
    }
}
