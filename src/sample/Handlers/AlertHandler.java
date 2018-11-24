package sample.Handlers;

import javafx.scene.control.Alert;

public class AlertHandler {
    public static void ShowAlert(String title, String header, String body){
        show(Alert.AlertType.NONE, title, header, body);
    }
    public static void ShowConfirm(String title, String header, String body){
        show(Alert.AlertType.NONE, title, header, body);
    }
    public static void ShowInfo(String title, String header, String body){
        show(Alert.AlertType.CONFIRMATION, title, header, body);
    }
    public static void ShowWarning(String title, String header, String body){
        show(Alert.AlertType.WARNING, title, header, body);
    }
    public static void ShowError(String title, String header, String body){
        show(Alert.AlertType.ERROR, title, header, body);
    }
    private static void show(Alert.AlertType type, String title, String header, String body){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(body);
        alert.showAndWait();
    }
}
