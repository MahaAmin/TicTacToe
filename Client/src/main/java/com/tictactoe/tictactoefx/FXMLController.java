
package com.tictactoe.tictactoefx;

import actions.PlayRequest;
import animatefx.animation.Flash;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.json.simple.JSONObject;
import org.kordamp.ikonli.javafx.FontIcon;
import player.PlayerHandler;
import player.PlayerSoc;
import playerModel.Player;

public class FXMLController implements Initializable {
    @FXML
    private FontIcon xIcon;
    @FXML
    private FontIcon oIcon;
    @FXML
    private JFXButton mySignupButton;
    @FXML
    private TextField usernameTF;
    @FXML
    private JFXPasswordField passwordPF;
    Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private void signUpButtonClicked(ActionEvent event) throws IOException {
        //Transition into RegisterForm.fxml
        SwitchTo.registerFormScene(event);
    }


    @FXML
    private void signInButtonClicked(ActionEvent event) throws IOException {

//
//        SwitchTo.dashboardScene(event);
        if (usernameTF.getText() == null || passwordPF.getText() == null) {
            alert.setTitle("Information Dialog");
            alert.setHeaderText("WRONG PASSword");
            alert.setContentText("Passwords don't match");
            alert.showAndWait();
        } else {
            validate();
//            if () {
//                //Transition to the Dashboard.fxml scene
//                SwitchTo.dashboardScene(event);
//            } else {
//                alert.setTitle("Information Dialog");
//                alert.setHeaderText("WRONG PASSword");
//                alert.setContentText("Passwords don't match");
//                alert.showAndWait();
//            }
        }
    }

    @FXML
    private void googleButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Google button is clicked");
    }

    @FXML
    private void facebookButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Facebook button is clicked");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Flash(xIcon).setCycleCount(30).setSpeed(.3).setResetOnFinished(true).play();
        new Flash(oIcon).setCycleCount(30).setSpeed(.3).setResetOnFinished(true).setDelay(Duration.millis(300)).play();

    }

    private void validate() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "login");
        map.put("email", usernameTF.getText());
        map.put("password", passwordPF.getText());
        PlayRequest.sendJSON(map);
    }
}
