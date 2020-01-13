package com.tictactoe.tictactoefx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SaveGameRejectedPopupController implements Initializable
{

    @FXML private void okButtonClicked(ActionEvent event)
    {
        System.out.println("Ok button Clicked");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    }    
    
}
