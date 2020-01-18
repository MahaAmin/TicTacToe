package popups;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SaveGameConfirmationPopupController implements Initializable {
    @FXML private Label playerNameLabel;
    
    @FXML private void yesButtonClicked(ActionEvent event)
    {
        System.out.println("Yes clicked");
    }
    
    @FXML private void noButtonClicked(ActionEvent event)
    {
        System.out.println("No clicked");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String playerName = "Player 1";
        playerNameLabel.setText("You have an already saved game with " + playerName );
    }    
    
}
