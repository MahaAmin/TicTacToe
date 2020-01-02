package com.tictactoe.tictactoefx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class RegisterFormController implements Initializable
{
    @FXML private void registerButtonClicked(ActionEvent event) throws IOException
    {
        //Go back to the startup Scene.fxml
         SwitchTo.mainScene(event);  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
    
}
