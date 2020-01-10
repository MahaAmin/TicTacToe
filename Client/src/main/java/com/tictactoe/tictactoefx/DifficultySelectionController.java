package com.tictactoe.tictactoefx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DifficultySelectionController implements Initializable {
    
    @FXML private void easyButtonClicked(ActionEvent event) throws IOException
    {
        SwitchTo.gamePlayScene(event);
    }
    
    @FXML private void mediumButtonClicked(ActionEvent event) throws IOException
    {
        SwitchTo.gamePlayScene(event);
    }
        
    @FXML private void hardButtonClicked(ActionEvent event) throws IOException
    {
        SwitchTo.gamePlayScene(event);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
