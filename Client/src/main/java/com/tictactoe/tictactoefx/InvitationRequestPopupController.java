package com.tictactoe.tictactoefx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class InvitationRequestPopupController implements Initializable {

    @FXML private Label requestLabel;
    
    @FXML private void noButtonClicked(ActionEvent event)
    {
        System.out.println("No button clicked");
    }
    
    @FXML private void yesButtonClicked(ActionEvent event)
    {
        System.out.println("Yes button Clicked");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        requestLabel.setText("Username goes Here");
       
    }    
    
}
