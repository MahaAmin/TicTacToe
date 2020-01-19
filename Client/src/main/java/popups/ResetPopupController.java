package popups;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ResetPopupController implements Initializable {

    
    @FXML private void noButtonClicked(ActionEvent event)
    {
        System.out.println("NOOO");
    }
    
    @FXML private void yesButtonClicked(ActionEvent event)
    {
        System.out.println("YEESSS");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
        
    }    
    
}
