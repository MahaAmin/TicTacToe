package popups;

import animatefx.animation.Tada;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.kordamp.ikonli.javafx.FontIcon;

public class SaveGameSuccessPopupController implements Initializable {

    @FXML private FontIcon heartIcon;
    @FXML private void okButtonClicked(ActionEvent event)
    {
        System.out.println("Ok clicked");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        new Tada(heartIcon).setCycleCount(30).setSpeed(.9).setResetOnFinished(true).play();
    }    
    
}
