package com.tictactoe.tictactoefx;

import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.json.simple.JSONObject;
import player.PlayerHandler;
import player.PlayerSoc;


public class RegisterFormController implements Initializable
{
    @FXML private TextField usernameTF,emailTF;
    @FXML private JFXPasswordField  passwordPF;
    @FXML private JFXPasswordField confirmPasswordPF;
    Alert alert = new Alert(AlertType.ERROR);

    
    @FXML private void registerButtonClicked(ActionEvent event) throws IOException
    {
         if(passwordPF.getText().equals(confirmPasswordPF.getText()))
        {
            if(validate()){
                alert.setTitle("Information Dialog");
                alert.setHeaderText("success ");
                alert.setContentText("succsefully signed up!");
                alert.showAndWait();
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
        try{
            PlayerSoc player = PlayerHandler.playerSoc;
            System.out.println("streams created");
            JSONObject jsonMsg = new JSONObject();
            jsonMsg.put("type", "register");
            jsonMsg.put("name", usernameTF.getText());
            jsonMsg.put("email", emailTF.getText());
            jsonMsg.put("password", passwordPF.getText());
            System.out.println("json created");
            StringWriter out = new StringWriter();
            jsonMsg.writeJSONString(out);
            player.ps.println(out.toString());
            boolean resp = player.dis.readBoolean();
            return resp;
        }
        catch(IOException e){
            System.out.println("Changing json to string failed!!");
            return false;
        }
    
    }
}
