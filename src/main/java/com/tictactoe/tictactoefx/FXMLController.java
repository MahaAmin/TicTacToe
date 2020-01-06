package com.tictactoe.tictactoefx;

import animatefx.animation.Flash;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

public class FXMLController implements Initializable {
    @FXML private FontIcon xIcon;
    @FXML private FontIcon oIcon;
    @FXML private JFXButton mySignupButton;
    
    @FXML private void signUpButtonClicked(ActionEvent event) throws IOException
    {
        //Transition into RegisterForm.fxml
        SwitchTo.registerFormScene(event);
    }
    
    @FXML private void signInButtonClicked(ActionEvent event) throws IOException
    {
        //Transition to the Dashboard.fxml scene
        SwitchTo.dashboardScene(event);
    }
    
    @FXML private void googleButtonClicked(ActionEvent event) throws IOException
    {
        System.out.println("Google button is clicked");
    }
    
    @FXML private void facebookButtonClicked(ActionEvent event) throws IOException
    {
        System.out.println("Facebook button is clicked");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    new Flash(xIcon).setCycleCount(30).setSpeed(.3).setResetOnFinished(true).play();
    new Flash(oIcon).setCycleCount(30).setSpeed(.3).setResetOnFinished(true).setDelay(Duration.millis(300)).play();
        
    }    
}
