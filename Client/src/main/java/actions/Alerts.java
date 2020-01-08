package actions;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Alerts {

    public static void sendRequestAlert(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Invitation");
        alert.setHeaderText(name + " Want To Play With You");
        alert.setContentText("Are You Ready?");

        ButtonType buttonYes = new ButtonType("Yes");
        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        Map<String, String> map = new HashMap<>();
        map.put("type", "acceptRequest");
        map.put("from_", "acceptRequest");

        PlayRequest.sendJSON(map);
        if (result.get() == buttonYes) {
            // ... user chose "Yes"
            map.put("response", "true");

        } else if (result.get() == buttonNo) {
            // ... user chose "No"
            map.put("response", "false");
        }
    }
    public static void wrongPasswordAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("wrong information");
        alert.setContentText("please enter the right credentials!");
        alert.showAndWait();
    }




}
