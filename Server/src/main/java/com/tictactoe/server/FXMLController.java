package com.tictactoe.server;

import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FXMLController implements Initializable 
{
    @FXML private JFXToggleButton serverToggleButton;
    @FXML private void serverToggleClicked(ActionEvent event)
    {
        if(serverToggleButton.isSelected() == true)
        {
            serverToggleButton.setText("Close Server");
        }
        else 
        {
            serverToggleButton.setText("Start Server");
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
