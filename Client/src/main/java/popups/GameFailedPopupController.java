package popups;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class GameFailedPopupController implements Initializable {
    @FXML private  void okButtonClicked(ActionEvent event)
    {
        System.out.println("Ok clicked");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
