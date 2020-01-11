package com.tictactoe.server;

import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.json.simple.JSONObject;

public class FXMLController implements Initializable 
{
    @FXML private JFXToggleButton serverToggleButton;
    @FXML private void serverToggleClicked(ActionEvent event)
    {
        if(serverToggleButton.isSelected() == true)
        {
            serverToggleButton.setText("Close Server");
            new Server();
        }
        else 
        {
            serverToggleButton.setText("Start Server");
            JSONObject jsono=new JSONObject();
            jsono.put("type", "serverClose");
            ServerHandler.playersSoc.forEach((socket)->{
                try {
                    ServerHandler soc=(ServerHandler)socket;
                    soc.ps.println(jsono);
                    soc.ps.close();
                    soc.dis.close();
                    soc.soc.close();
                    // close dis , ps , socket
                } catch (IOException ex) {
                    Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
