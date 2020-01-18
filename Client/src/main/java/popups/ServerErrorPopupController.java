package popups;

import java.net.URL;
import java.util.ResourceBundle;

import actions.App;
import actions.GameConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.simple.JSONObject;

public class ServerErrorPopupController implements Initializable {
    private JSONObject jsonData ;

    @FXML private void okButtonClicked(ActionEvent event)
    {
        App.getPopUpWindow().close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        jsonData= GameConfig.getServerErrorPopup();

    }    
    
}
