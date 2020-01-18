package popups;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import actions.App;
import actions.GameConfig;
import actions.PlayRequest;
import com.tictactoe.tictactoefx.SwitchTo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.simple.JSONObject;

public class SaveGameRejectedPopupController implements Initializable
{
    private JSONObject jsonData;

    @FXML private void okButtonClicked(ActionEvent event)
    {
        App.getPopUpWindow().close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
      //  String name = jsonData.get("from_name").toString();
      jsonData=GameConfig.getSaveGameRejectedPobUp();


    }    
    
}
