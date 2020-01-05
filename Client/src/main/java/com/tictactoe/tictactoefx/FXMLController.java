package com.tictactoe.tictactoefx;

import animatefx.animation.Flash;
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
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.json.simple.JSONObject;
import org.kordamp.ikonli.javafx.FontIcon;

public class FXMLController implements Initializable {
    @FXML private FontIcon xIcon;
    @FXML private FontIcon oIcon;
    @FXML private TextField usernameTF;
    @FXML private JFXPasswordField passwordPF;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    
    @FXML private void signUpButtonClicked(ActionEvent event) throws IOException
    {
        //Transition into RegisterForm.fxml
        SwitchTo.registerFormScene(event);
    }
    
    @FXML private void signInButtonClicked(ActionEvent event) throws IOException
    {
        if(usernameTF.getText()==null||passwordPF.getText() == null){
            alert.setTitle("Information Dialog");
            alert.setHeaderText("WRONG PASSword");
            alert.setContentText("Passwords don't match");
            alert.showAndWait();
        }else{
            if(validate()){
                //Transition to the Dashboard.fxml scene
                SwitchTo.dashboardScene(event);
            }else{
                alert.setTitle("Information Dialog");
                alert.setHeaderText("WRONG PASSword");
                alert.setContentText("Passwords don't match");
                alert.showAndWait();
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    new Flash(xIcon).setCycleCount(30).setSpeed(.3).setResetOnFinished(true).play();
    new Flash(oIcon).setCycleCount(30).setSpeed(.3).setResetOnFinished(true).setDelay(Duration.millis(300)).play();
    }   
    
    public boolean validate(){
        try{
            Socket s = new Socket("127.0.0.1",5005);
            System.out.println("Connection is up");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            PrintStream os = new PrintStream(s.getOutputStream());
            System.out.println("streams created");
            JSONObject jsonMsg = new JSONObject();
            jsonMsg.put("type", "login");
            jsonMsg.put("name", usernameTF.getText());
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
