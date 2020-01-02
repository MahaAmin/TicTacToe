package com.tictactoe.tictactoefx;

import animatefx.animation.Flash;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

public class FXMLController implements Initializable {
    @FXML private FontIcon xIcon;
    @FXML private FontIcon oIcon;
    
    @FXML private void signUpButtonClicked(ActionEvent event) throws IOException
    {
        //Transition to the RegisterForm.fxml scene
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/RegisterForm.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    @FXML private void signInButtonClicked(ActionEvent event) throws IOException
    {
        //Transition to the Dashboard.fxml scene
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    new Flash(xIcon).setCycleCount(30).setSpeed(.3).setResetOnFinished(true).play();
    new Flash(oIcon).setCycleCount(30).setSpeed(.3).setResetOnFinished(true).setDelay(Duration.millis(300)).play();
        
    }    
}
