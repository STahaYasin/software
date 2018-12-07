package sample.ListCells;

import com.sun.deploy.util.FXLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class OrderOnTableCell<Order> extends ListCell<Order> {



    @Override
    protected void updateItem(Order item, boolean empty) {
        super.updateItem(item, empty);

        if(empty || item == null){
            setText(null);
            setGraphic(null);

            return;
        }


    }
}
