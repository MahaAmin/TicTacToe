package com.tictactoe.tictactoefx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SaveGameRequestPopupController implements Initializable {

    @FXML private void noButtonClicked(ActionEvent event)
    {
        System.out.println("No clicked");
    }
    
    @FXML private void yesButtonClicked(ActionEvent event)
    {
        System.out.println("Yes clicked");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
}
