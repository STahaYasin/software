package sample.Controllers;

import javafx.stage.Stage;

public class StageHolder {

    private Stage stage;

    private static StageHolder instance;

    private StageHolder(){

    }
    public static StageHolder getInstance(){
        if(instance == null) instance = new StageHolder();

        return instance;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
    public Stage getStage(){
        return stage;
    }
}
