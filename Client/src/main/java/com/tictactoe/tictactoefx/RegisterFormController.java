package com.tictactoe.tictactoefx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXSnackbarLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class RegisterFormController implements Initializable
{
    @FXML private JFXPasswordField  passwordPF, confirmPasswordPF;
    @FXML private JFXTextField usernameTF ,emailTF;
    @FXML private AnchorPane anchorPane;
    @FXML private JFXButton registerButton;
    
    RegexValidator regexValidator = new RegexValidator(); //Checks the email.
    RequiredFieldValidator validator = new RequiredFieldValidator(); 

    @FXML private void registerButtonClicked(ActionEvent event) throws IOException
    {
        regexValidator.setMessage("*Please enter a valid email");
        validator.setMessage("*Input Required");
        
        if(usernameTF.getText().trim().isEmpty())
        {
            usernameTF.getValidators().add(validator);
            usernameTF.validate();
        }
        
        //Check if email was left empty.
        if(emailTF.getText().trim().isEmpty())
        {
            emailTF.getValidators().add(validator);
            emailTF.validate();
        }
        
        //Check the sanity of the email TF.
        //From the tutorial https://www.techgalery.com/2019/08/javafx-email-validation-using-jfoenix.html
        if(!emailTF.getText().trim().isEmpty())
        {
            regexValidator.setRegexPattern("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            emailTF.setValidators(regexValidator);
            emailTF.validate();
        }
        
        if(passwordPF.getText().trim().isEmpty())
        {
            passwordPF.getValidators().add(validator);
            passwordPF.validate();
        }
        
        if(confirmPasswordPF.getText().trim().isEmpty())
        {
            confirmPasswordPF.getValidators().add(validator);
            confirmPasswordPF.validate();
        }
        
        //Check if password mismatch occured
        if(!passwordPF.getText().equals(confirmPasswordPF.getText()))
        {
            JFXSnackbar snackbar = new JFXSnackbar();
            
            snackbar.registerSnackbarContainer(anchorPane);

           //snackbar.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("Toast Message " + 10000)));
           snackbar.fireEvent(new SnackbarEvent(new JFXSnackbarLayout("Passwords don't match, please renter you password" ,  "Ok", (event2) -> {
               snackbar.unregisterSnackbarContainer(anchorPane);
           }), Duration.INDEFINITE, null));
        }
    
        //Check if the passwords match and make sure they're not empty
        if(passwordPF.getText().equals(confirmPasswordPF.getText()) && !passwordPF.getText().trim().isEmpty() && !confirmPasswordPF.getText().trim().isEmpty() )
        {
            if(emailTF.validate())//If the mail was valid ..
            {
                if(!usernameTF.getText().trim().isEmpty())//if the username wasn't empty
                    //Go back to the startup Scene.fxml if passwords match.
                    SwitchTo.mainScene(event);  
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
}
