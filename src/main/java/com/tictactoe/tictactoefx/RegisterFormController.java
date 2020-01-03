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
import javafx.scene.control.TextField;


public class RegisterFormController implements Initializable
{
    @FXML private TextField userName,email;
    @ FXML private JFXPasswordField passwordPF,confirmPasswordPF;
    Alert alert = new Alert(AlertType.ERROR);
    
    @FXML
    private void registerButtonClicked(ActionEvent event) throws IOException
    {
        if(passwordPF.getText().equals(confirmPasswordPF.getText()))
        {
            if(validate()){
                //Go back to the startup Scene.fxml
                SwitchTo.mainScene(event);
            }else{
                //put red component here and don't switch
                alert.setTitle("Information Dialog");
                alert.setHeaderText("WRONG email or username ");
                alert.setContentText("email or username might be wrong");
                alert.showAndWait();
            }
        }
        else 
        {
            alert.setTitle("Information Dialog");
            alert.setHeaderText("WRONG PASSword");
            alert.setContentText("Passwords don't match");
            alert.showAndWait();
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }
    public boolean validate(){
        return false;
    }
    
}
