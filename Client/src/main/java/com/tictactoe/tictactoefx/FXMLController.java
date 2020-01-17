
package com.tictactoe.tictactoefx;

import actions.PlayRequest;
import animatefx.animation.Flash;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.JFXFillTransition;
import com.jfoenix.validation.RequiredFieldValidator;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import playerModel.PlayerModel;


public class FXMLController implements Initializable {
    @FXML
    private FontIcon xIcon;
    @FXML
    private FontIcon oIcon;
    @FXML
    private JFXButton mySignupButton;
    @FXML
    private JFXTextField usernameTF;
    @FXML
    Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private JFXPasswordField passwordTF;
    RequiredFieldValidator validator = new RequiredFieldValidator();


    @FXML
    private JFXButton googleButton, facebookButton;
    boolean enteredGoogleBTN, enteredFacebookBTN; //Handle changing colors on hover (it detects if button was hovered over more than once)



    @FXML
    private void signUpButtonClicked(ActionEvent event) throws IOException {
        //Transition into RegisterForm.fxml
        SwitchTo.registerFormScene(event);
    }


    @FXML
    private void signInButtonClicked(ActionEvent event) throws IOException {
        //Check if usernameTF was left empty
        if (usernameTF.getText().trim().isEmpty()) {
            validator.setMessage("*Input Required");
            usernameTF.getValidators().add(validator);
            usernameTF.validate();
        }

        //Check if passwordTF was left empty
        if (passwordTF.getText().trim().isEmpty()) {
            validator.setMessage("*Input Required");
            passwordTF.getValidators().add(validator);
            passwordTF.validate();
        }
        //make sure that both fields aren't empty before attempting to switch the scene.
        validate();
    }

    @FXML
    private void googleButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Google button is clicked");
        SwitchTo.GooglePopupScene();
    }

    @FXML
    private void facebookButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Facebook button is clicked");
    }


    private void validate() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "login");
        map.put("email", usernameTF.getText());
        map.put("password", passwordTF.getText());
        System.out.println(map);
        PlayRequest.sendJSON(map);
    }

    /*
    Styling functions section, leave and move on.
    */
    //Hovering over google button.
    @FXML
    private void googleButtonHovered(MouseEvent event) throws IOException {
        if (enteredGoogleBTN)
            googleButton.setStyle("-fx-background-color: #F8327E; ");
        else
            transition(event, googleButton, "0xDA7FBF", "0xF8327E");
        enteredGoogleBTN = true;
    }

    //Leaving the google Button
    @FXML
    private void googleButtonExited(MouseEvent event) throws IOException {
        googleButton.setStyle("-fx-background-color: #F8327E; ");
    }

    @FXML
    private void facebookButtonHovered(MouseEvent event) throws IOException {
        if (enteredFacebookBTN)
            facebookButton.setStyle("-fx-background-color: #6493D0; ");
        else
            transition(event, facebookButton, "0x64D0F9", "0x6493D0");
        enteredFacebookBTN = true;
    }

    @FXML
    private void facebookButtonExited(MouseEvent event) throws IOException {
        facebookButton.setStyle("-fx-background-color: #6493D0; ");
    }

    //Generic Transition function.
    private void transition(MouseEvent event, JFXButton button, String colorFrom, String colorTo) {
        JFXFillTransition transition = new JFXFillTransition();
        transition.setDuration(Duration.millis(1500));
        transition.setRegion(button);
        transition.setFromValue(Color.web(colorFrom));
        transition.setToValue(Color.web(colorTo));
        transition.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Flash(xIcon).setCycleCount(30).setSpeed(.3).setResetOnFinished(true).play();
        new Flash(oIcon).setCycleCount(30).setSpeed(.3).setResetOnFinished(true).setDelay(Duration.millis(300)).play();
    }

}
