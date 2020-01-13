package com.tictactoe.tictactoefx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class RequestRejectedPopupController implements Initializable {


    {
      /*  try {
            SwitchTo.RequestRejectedPopupScene();
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
    }


    @FXML private Label informingLabel;
   
   @FXML private void okButtonClicked(ActionEvent event)
   {

   }
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {

       informingLabel.setText("Sorry but "+ "username"  + " rejected your play request.");
    }    
    
}
