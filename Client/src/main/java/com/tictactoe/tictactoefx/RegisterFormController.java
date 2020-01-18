package com.tictactoe.tictactoefx;

import actions.App;
import actions.PlayRequest;
import animatefx.animation.Flash;
import com.jfoenix.controls.JFXPasswordField;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXSnackbarLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.tictactoe.tictactoefx.SwitchTo;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.json.simple.JSONObject;
import org.kordamp.ikonli.javafx.FontIcon;
import player.PlayerHandler;
import player.PlayerSoc;


import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import playerModel.PlayerModel;

public class RegisterFormController implements Initializable {
    @FXML
    private JFXPasswordField passwordPF, confirmPasswordPF;
    @FXML
    private JFXTextField usernameTF, emailTF;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton registerButton;

    Alert alert = new Alert(AlertType.ERROR);
    public String ah="here";
    @FXML private FontIcon xIcon, oIcon;

    RegexValidator regexValidator = new RegexValidator(); //Checks the email.
    RequiredFieldValidator validator = new RequiredFieldValidator();

    @FXML
    private void registerButtonClicked(ActionEvent event) throws IOException {
        regexValidator.setMessage("*Please enter a valid email");
        validator.setMessage("*Input Required");

        if (usernameTF.getText().trim().isEmpty()) {
            usernameTF.getValidators().add(validator);
            usernameTF.validate();
        }

        //Check if email was left empty.
        if (emailTF.getText().trim().isEmpty()) {
            emailTF.getValidators().add(validator);
            emailTF.validate();
        }

        //Check the sanity of the email TF.
        if (!emailTF.getText().trim().isEmpty()) {
            regexValidator.setRegexPattern("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            emailTF.setValidators(regexValidator);
            emailTF.validate();
        }

        if (passwordPF.getText().trim().isEmpty()) {
            passwordPF.getValidators().add(validator);
            passwordPF.validate();
        }

        if (confirmPasswordPF.getText().trim().isEmpty()) {
            confirmPasswordPF.getValidators().add(validator);
            confirmPasswordPF.validate();
        }

        //Check if password mismatch occured
        if (!passwordPF.getText().equals(confirmPasswordPF.getText())) {
            JFXSnackbar snackbar = new JFXSnackbar();

            snackbar.registerSnackbarContainer(anchorPane);

            //snackbar.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("Toast Message " + 10000)));
            snackbar.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("Passwords don't match, please renter you password", "Ok", (event2) -> {
                snackbar.unregisterSnackbarContainer(anchorPane);
            }), Duration.INDEFINITE, null));
        }

        //Check if the passwords match and make sure they're not empty
        if (passwordPF.getText().equals(confirmPasswordPF.getText()) && !passwordPF.getText().trim().isEmpty() && !confirmPasswordPF.getText().trim().isEmpty()) {
            if (emailTF.validate())//If the mail was valid ..
            {
                if (!usernameTF.getText().trim().isEmpty())//if the username wasn't empty
                    //Go back to the startup Scene.fxml if passwords match.
                    validate();
            }
        }


//        if (passwordPF.getText().equals(confirmPasswordPF.getText())) {
//            if (validate()) {
//                alert.setTitle("Information Dialog");
//                alert.setHeaderText("success ");
//                alert.setContentText("succsefully signed up!");
//                alert.showAndWait();
//                //Go back to the startup Scene.fxml
//                SwitchTo.mainScene(event);
//            } else {
//                //put red component here and don't switch
//                alert.setTitle("Information Dialog");
//                alert.setHeaderText("WRONG email or username ");
//                alert.setContentText("email or username might be wrong");
//                alert.showAndWait();
//            }
//        } else {
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText("WRONG PASSword");
//            alert.setContentText("Passwords don't match");
//            alert.showAndWait();
//        }
    }

    @FXML private void backButtonClicked(ActionEvent event) throws IOException
    {
        SwitchTo.mainScene(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Flash(xIcon).setCycleCount(60).setSpeed(.3).setResetOnFinished(true).play();
        new Flash(oIcon).setCycleCount(60).setSpeed(.3).setResetOnFinished(true).setDelay(Duration.millis(300)).play();
    }


    public void validate() {
        
        Map<String, String> map = new HashMap<>();
        map.put("type", "register");
        map.put("name", usernameTF.getText());
        map.put("email", emailTF.getText());
        map.put("password", passwordPF.getText());
        System.out.println(map);
        PlayRequest.sendJSON(map);
    }
}
