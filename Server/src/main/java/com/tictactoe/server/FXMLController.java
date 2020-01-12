package com.tictactoe.server;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {
    @FXML
    private JFXToggleButton serverToggleButton;

    @FXML
    private void serverToggleClicked(ActionEvent event) {
        if (serverToggleButton.isSelected() == true) {
            System.out.println("server start");
            serverToggleButton.setText("Close Server");
            new Server();
        } else {
            System.out.println("Server closed");
            serverToggleButton.setText("Start Server");
            for (ServerHandler soc : ServerHandler.playersSoc) {
                try {
                    soc.ps.close();
                    soc.dis.close();
                    soc.soc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // close dis , ps , socket
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
