package com.tictactoe.tictactoefx;

import com.jfoenix.controls.JFXPasswordField;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;


public class RegisterFormController implements Initializable
{
    @FXML private TextField usernameTF,emailTF;
    @FXML private JFXPasswordField passwordPF,confirmPasswordPF;
    Alert alert = new Alert(AlertType.ERROR);
    Alert s = new Alert(AlertType.INFORMATION);
    @FXML
    private void registerButtonClicked(ActionEvent event) throws IOException
    {
        if(passwordPF.getText().equals(confirmPasswordPF.getText()))
        {
            if(validate()){
                s.setTitle("Information Dialog");
                s.setHeaderText("success ");
                s.setContentText("succsefully signed up!");
                s.showAndWait();
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
            Socket s = new Socket("127.0.0.1",5005);
            System.out.println("Connection is up");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            PrintStream os = new PrintStream(s.getOutputStream());
            System.out.println("streams created");
            JSONObject jsonMsg = new JSONObject();
            jsonMsg.put("type", "register");
            jsonMsg.put("name", usernameTF.getText());
            jsonMsg.put("email", emailTF.getText());
            jsonMsg.put("password", passwordPF.getText());
            System.out.println("json created");
            StringWriter out = new StringWriter();
            jsonMsg.writeJSONString(out);
            os.println(out.toString());
            String resp = dis.readLine();
            return Boolean.parseBoolean(resp);
        }
        catch(IOException e){
            System.out.println("Changing json to string failed!!");
            return false;
        }
    
    }
    
}
