package popups;

import animatefx.animation.Swing;
import animatefx.animation.Tada;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.kordamp.ikonli.javafx.FontIcon;

public class RegisterationSuccessfulPopupController implements Initializable {

    @FXML private FontIcon childIcon;
    
    @FXML private void okButtonClicked(ActionEvent event)
    {
        System.out.println("Ok clicked");  
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Swing(childIcon).setCycleCount(30).setSpeed(.99).setResetOnFinished(true).play();
        
    }    
    
}
