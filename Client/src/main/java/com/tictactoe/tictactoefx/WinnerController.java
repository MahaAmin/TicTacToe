package com.tictactoe.tictactoefx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class WinnerController implements Initializable 
{
    @FXML private void resetButtonClicked(ActionEvent event)
    {
        System.out.println("Reset was Pressed");
    }
    
    @FXML private void shareButtonClicked(ActionEvent event)
    {
        System.out.println("Share was pressed");
    }
    
    @FXML private void exitButtonClicked(ActionEvent event)
    {
        System.out.println("Exit was pressed");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
}
