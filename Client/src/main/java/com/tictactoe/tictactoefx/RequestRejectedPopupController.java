package com.tictactoe.tictactoefx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class RequestRejectedPopupController implements Initializable {

    @FXML private Label informingLabel;
   
   @FXML private void okButtonClicked(ActionEvent event)
   {
       System.out.println("Ok button Clicked");
   }
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {

       informingLabel.setText("Sorry but "+ " Username"  + " rejected your play request.");
    }    
    
}
