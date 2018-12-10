package sample.Controllers;

import sample.Objects.Payable;
import sample.Objects.TableReservation;
import sample.Objects.Ticket;

public interface OnPayListener {
    public void OnPayed(Payable payable);
}
