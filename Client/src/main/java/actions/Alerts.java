package actions;

import com.jfoenix.controls.JFXButton;
import com.tictactoe.tictactoefx.SwitchTo;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.json.simple.JSONObject;

import java.io.IOException;
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
        alert.setContentText("Sorry, " + name + " reject your play request.");
        alert.showAndWait();
    }

    public static void saveGameAlert(JSONObject data) {

//        fromPlayer_name,Integer.parseInt(jsonMsg.get("game_id").toString())
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save The Game");
        alert.setHeaderText("Player against you want to save the game and resume in another time");
        alert.setContentText("Press Yes for confirm that?");

        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonYes = new ButtonType("Yes");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        data.replace("type", "saveGameAnswer");

        if (result.get() == buttonYes) {
            // ... user chose "Yes"
            data.put("response", "true");
            PlayRequest.sendJSONObject(data);
            Platform.runLater(()->{
                try {
                    SwitchTo.changeTo(App.getWindow(),2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } else if (result.get() == buttonNo) {
            // ... user chose "No"
            data.put("response", "false");
            PlayRequest.sendJSONObject(data);
        }
    }

    public static void saveGameAnswerAlert(boolean answer) {
        if (answer == true) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save Game");
            alert.setHeaderText("Game saved successfully!");
            alert.setContentText("You can resume it at anytime^-^");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save Game");
            alert.setContentText("Unfortunately player against you refuse to save the game..");
            alert.showAndWait();
        }
    }


}
