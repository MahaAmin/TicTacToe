package popups;

import java.net.URL;
import java.util.ResourceBundle;

import actions.App;
import actions.GameConfig;
import actions.PlayRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;

public class SaveGameConfirmationPopupController implements Initializable {
    @FXML private Label playerNameLabel;
    private JSONObject jsonData;
    
    @FXML private void yesButtonClicked(ActionEvent event)
    {
        // ... user chose "Yes"
        jsonData.put("response", "true");
        PlayRequest.sendJSONObject(jsonData);
        App.getPopUpWindow().close();
    }
    
    @FXML private void noButtonClicked(ActionEvent event)
    {
        // ... user chose "No"
        jsonData.put("response", "false");
        PlayRequest.sendJSONObject(jsonData);
        App.getPopUpWindow().close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jsonData= GameConfig.getOldSavePobUp();
        String playerName = jsonData.get("name").toString();
        playerNameLabel.setText("You have an already saved game with " + playerName );
        jsonData.replace("type", "chosenPlayRequest");
    }

    
}
