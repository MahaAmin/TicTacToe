package com.tictactoe.tictactoefx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SaveGameRequestPopupController implements Initializable {

    @FXML private void noButtonClicked(ActionEvent event)
    {
        try {
            SwitchTo.SaveGameRejectedPopupScene();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    @FXML private void yesButtonClicked(ActionEvent event)
    {
        try {
            SwitchTo.SaveGameSuccessPopupScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
}
