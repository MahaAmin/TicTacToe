package com.tictactoe.tictactoefx;

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

public class DashboardController implements Initializable
{
    @FXML private void vsPCbuttonClicked(ActionEvent event) throws IOException
    {
        //Transition to the GamePlay.fxml 
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/fxml/GamePlay.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML private void vsOnlinePlayerButtonClicked(ActionEvent event) throws IOException
    {
        System.out.println("Triggered Online player button");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/OnlineListPopup.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Online friends List.");
            stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
}
