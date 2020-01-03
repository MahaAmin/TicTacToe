package com.tictactoe.tictactoefx;

import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class RegisterFormController implements Initializable
{
    @FXML private JFXPasswordField  passwordPF;
    @FXML private JFXPasswordField confirmPasswordPF;
    Alert alert = new Alert(AlertType.ERROR);

    
    @FXML private void registerButtonClicked(ActionEvent event) throws IOException
    {
        //Check if the passwords match.
        if(passwordPF.getText().equals(confirmPasswordPF.getText()) )
        {
            //Go back to the startup Scene.fxml if passwords match.
             SwitchTo.mainScene(event);  
        }
        else 
        {
            alert.setTitle("Information Dialog");
            alert.setHeaderText("WRONG FOCKING PASS");
            alert.setContentText("I have a great message for you!");

            alert.showAndWait();
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    
    
}
