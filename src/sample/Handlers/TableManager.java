package sample.Handlers;

import javafx.scene.image.ImageView;
import sample.Objects.Table;
import sample.Objects.TableReservation;

public class TableManager {
    private static TableManager instance;

    private TableReservation[] tableReservations;
    private Table[] tables;

    public void setTableButtons(int countOfTables){
        tableReservations = new TableReservation[countOfTables];
        tables = new Table[countOfTables];

        for(int i = 0; i < countOfTables; i ++){
            tables[i] = new Table();
            tables[i].setName("Table " + (i + 1));
        }
    }

    public static TableManager getInstance(){
        if(instance == null) instance = new TableManager();

        return instance;
    }

    private TableManager(){

    }

    public TableReservation getTableReservation(int tableIndex){
        if(tableIndex < 0 || tableIndex > tableReservations.length) return null;

        if(tableReservations[tableIndex] == null) tableReservations[tableIndex] = new TableReservation(tables[tableIndex], new ResetTableHandler(tableIndex, this));
        return tableReservations[tableIndex];
    }

    public void removeTableReservation(int index) {
        if(tableReservations == null || index < 0 || index > tableReservations.length) throw new IllegalArgumentException("Tried to remove a table that doesn't exist");

        tableReservations[index] = null;
        System.out.println("Table reservation removed");
    }
}