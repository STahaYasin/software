package sample.Handlers;

import sample.Objects.Ticket;

import java.util.ArrayList;

public class TicketHandler {
    private ArrayList<Ticket> tickets;

    private static TicketHandler instance;

    public static TicketHandler getInstance(){
        if(instance == null) instance = new TicketHandler();

        return instance;
    }

    public TicketHandler(){
        tickets = new ArrayList<>();
    }

    public ArrayList<Ticket> getTickets(){
        return tickets;
    }
    public void AddTicket(Ticket ticket){
        tickets.add(ticket);
    }
    public void RemoveTicket(Ticket ticket){
        if(tickets == null) return;

        tickets.remove(ticket);
    }
}
