package popups;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import actions.App;
import actions.GameConfig;
import actions.PlayRequest;
import com.tictactoe.tictactoefx.SwitchTo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.simple.JSONObject;

public class SaveGameRequestPopupController implements Initializable {
    private JSONObject jsonData;


    @FXML private void noButtonClicked(ActionEvent event)
    {
        // ... user chose "No"
        jsonData.put("response", "false");
        PlayRequest.sendJSONObject(jsonData);
        Platform.runLater(() -> {
        try {

            jsonData=GameConfig.getSaveGamePobUpJson();
            SwitchTo.SaveGameRejectedPopupScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
        });
        App.getWindow().close();



    }
    
    @FXML private void yesButtonClicked(ActionEvent event)
    {

            // ... user chose "Yes"
            jsonData.put("response", "true");
            PlayRequest.sendJSONObject(jsonData);

            Platform.runLater(() -> {

                try {
                    GameConfig.setSaveGameSuccesPobUpPobUp(jsonData);
                    SwitchTo.SaveGameSuccessPopupScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
              /*  try {
                    SwitchTo.changeTo(App.getWindow(), 2);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            });
        App.getWindow().close();


    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        jsonData = GameConfig.getSaveGamePobUpJson();
        jsonData.replace("type", "saveGameAnswer");



    }

    
}
