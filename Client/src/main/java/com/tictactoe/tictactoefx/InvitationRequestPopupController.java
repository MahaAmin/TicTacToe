package com.tictactoe.tictactoefx;

import actions.App;
import actions.PlayRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;
import player.PlayerSoc;
import playerModel.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InvitationRequestPopupController implements Initializable {

    @FXML private Label requestLabel;
    
    @FXML private void noButtonClicked(ActionEvent event)
    {


        try {
            SwitchTo.RequestRejectedPopupScene();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML private void yesButtonClicked(ActionEvent event)
    {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        requestLabel.setText(App.getPlayerSoc().getPlayer().getPlayerName());
    }    
    
}
