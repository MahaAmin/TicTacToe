package popups;

import java.net.URL;
import java.util.ResourceBundle;

import actions.App;
import actions.GameConfig;
import actions.PlayRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.simple.JSONObject;
import player.PlayerHandler;

public class ResetPopupController implements Initializable {
    private JSONObject jsonData ;

    
    @FXML private void noButtonClicked(ActionEvent event)
    {
        // ... user chose "No"
        jsonData.put("response", "false");
        PlayRequest.sendJSONObject(jsonData);
        App.getPopUpWindow().close();

    }
    
    @FXML private void yesButtonClicked(ActionEvent event)
    {
        jsonData.put("response", "true");
        PlayRequest.sendJSONObject(jsonData);
        PlayerHandler.resetGame();
        //App.getPopUpWindow().close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

        jsonData= GameConfig.getResetPobUP();
        jsonData.replace("type", "resetGameAnswer");
       
        
    }    
    
}
