package com.tictactoe.tictactoefx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ChangeProfilePictureController implements Initializable {

    @FXML private void backButtonClicked(ActionEvent event) throws IOException
    {
        SwitchTo.dashboardScene(event);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        
    }    
    
}
