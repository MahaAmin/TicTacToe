package com.tictactoe.tictactoefx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tic.player.PlayerHandler;


public class DashboardController implements Initializable
{
    @FXML private void vsPCbuttonClicked(ActionEvent event) throws IOException
    {
        //Transition to the GamePlay.fxml 
        SwitchTo.gamePlayScene(event);
    }

    @FXML private void vsOnlinePlayerButtonClicked(ActionEvent event) throws IOException
    {
        //Popup the OnlineListPopUp.fxml scene
//        PlayerHandler.sendPlayRequest();
        SwitchTo.onlineListPopUpScene();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
}
