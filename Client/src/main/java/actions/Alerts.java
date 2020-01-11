package actions;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Alerts {

    public static void sendRequestAlert(JSONObject data) {
        String name = data.get("from_name").toString();

//        fromPlayer_name,Integer.parseInt(jsonMsg.get("game_id").toString())
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Invitation");
        alert.setHeaderText(name + " Want To Play With You");
        alert.setContentText("Are You Ready?");

        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonYes = new ButtonType("Yes");


        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        Map<String, String> map = new HashMap<>();
        data.replace("type", "acceptRequest");

        if (result.get() == buttonYes) {
            // ... user chose "Yes"
            data.put("response", "true");
            PlayRequest.sendJSONObject(data);

        } else if (result.get() == buttonNo) {
            // ... user chose "No"
            data.put("response", "false");
            PlayRequest.sendJSONObject(data);
        }
    }

    public static void wrongPasswordAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("wrong information");
        alert.setContentText("please enter the right credentials!");
        alert.showAndWait();
    }


    public static void gameRequestRejected(JSONObject data) {
        String name = data.get("from_name").toString();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Game Request");
        alert.setHeaderText("Oops,Game Rejected");
        alert.setContentText("Sorry, "+name+" reject your play request.");
        alert.showAndWait();
    }
    public static void serverIsShuttingDown(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Server");
        alert.setHeaderText("Oops,Server has a problem");
        alert.setContentText("Sorry, we will fix it soon");
        alert.showAndWait();
    }

}
