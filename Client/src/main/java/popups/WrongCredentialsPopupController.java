package popups;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.kordamp.ikonli.javafx.FontIcon;

public class WrongCredentialsPopupController implements Initializable {

    @FXML private FontIcon lockIcon;
    @FXML private JFXButton okButton;
    @FXML private void okButtonClicked(ActionEvent event)
    {
        System.out.println("Ok clicked");
        okButton.getScene().getWindow().hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        new Shake(lockIcon).setCycleCount(30).setSpeed(.2).setResetOnFinished(true).play();
    }    
    
}
