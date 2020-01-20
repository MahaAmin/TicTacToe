package actions;

import com.jfoenix.controls.JFXButton;
import com.tictactoe.tictactoefx.GamePlayController;
import com.tictactoe.tictactoefx.SwitchTo;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import org.json.simple.JSONObject;
import player.PlayerHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Alerts {

    public static void sendRequestAlert(JSONObject data) {
        String name = data.get("from_name").toString();

//        fromPlayer_name,Integer.parseInt(jsonMsg.get("game_id").toString())
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (data.containsKey("old_game")) {
            alert.setTitle("Resume Old Game");
            alert.setHeaderText(name + " want to resume the old game with you!");
            alert.setContentText("Are You want to resume it?");
        } else {
            alert.setTitle("Invitation");
            alert.setHeaderText(name + " Want To Play With You");
            alert.setContentText("Are You Ready?");
        }

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

    public static void chooseGameAlert(JSONObject data) {
        String name = data.get("from_name").toString();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choose Game Type");
        alert.setHeaderText("You already have a saved game with " + name + "!");
        alert.setContentText("Are You want to resume it?");

        ButtonType buttonNo = new ButtonType("No,Start new", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonYes = new ButtonType("Yes, I want");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);
        Optional<ButtonType> result = alert.showAndWait();
        data.replace("type", "chosenPlayRequest");

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
            Platform.runLater(() -> {
                try {
                    SwitchTo.changeTo(App.getWindow(), 2);
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

    public static void serverIsShuttingDown() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Server");
        alert.setHeaderText("Oops,Server has a problem");
        alert.setContentText("Sorry, we will fix it soon");
        alert.showAndWait();
    }

    public static void gameFailedAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Game Failed");
        alert.setHeaderText("Oops,Game Failed");
        alert.setContentText("Unfortunately,It seems your competitor close the game");
        alert.showAndWait();
    }

    public static void resetGameRequestAlert(JSONObject data) {

//        fromPlayer_name,Integer.parseInt(jsonMsg.get("game_id").toString())
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset The Game");
        alert.setHeaderText("Competitor want to play with you again. ");
        alert.setContentText("Are you ready?");

        ButtonType buttonNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonYes = new ButtonType("Yes");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        data.replace("type", "resetGameAnswer");

        if (result.get() == buttonYes) {
            // ... user chose "Yes"
            data.put("response", "true");
            PlayRequest.sendJSONObject(data);
            PlayerHandler.resetGame();
        } else if (result.get() == buttonNo) {
            // ... user chose "No"
            data.put("response", "false");
            PlayRequest.sendJSONObject(data);
        }
    }

}
