package sample.Handlers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Controllers.OnUpdateLabelsListener;
import sample.Objects.TableReservation;


public class TableTimesShowUpdateHandler extends Thread {
    private Label[] labels;
    private Thread mainThread;
    public OnUpdateLabelsListener onUpdateLabelsListener;

    public TableTimesShowUpdateHandler(Label[] tableLablePairs, Thread mainThread){
        this.labels = tableLablePairs;
        this.mainThread = mainThread;
    }
    public void setOnUpdateLabelsListener(OnUpdateLabelsListener onUpdateLabelsListener){
        this.onUpdateLabelsListener = onUpdateLabelsListener;
    }

    @Override
    public void run(){
        for(;;){
            try {
                Thread.sleep(1000);
            }
            catch (Exception e){

            }

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    onUpdateLabelsListener.Update();

                }
            });
            t.start();
        }
    }
}
