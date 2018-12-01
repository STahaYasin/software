package sample.Handlers;

public class ResetTableHandler {
    private int index;
    private TableManager tableManager;

    public ResetTableHandler(int index, TableManager tableManager){
        this.index = index;
        this.tableManager = tableManager;
    }

    public void ResetTable(){
        tableManager.removeTableReservation(index);
    }
}