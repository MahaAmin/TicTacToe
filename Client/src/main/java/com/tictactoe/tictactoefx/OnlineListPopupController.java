package com.tictactoe.tictactoefx;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;
import java.io.FileInputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import playerModel.Player;
import playerModel.PlayerModel;


public class OnlineListPopupController implements Initializable {
    @FXML
    private JFXListView<Object> onlinePlayersLV;
    //Here all usernames are stored
    ObservableList<String> listView = FXCollections.observableArrayList("User 1", "User 2", "User3");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        OnlineLV.setItems(PlayerModel.playerslist);
//        OnlineLV.setCellFactory(param -> new ListCell<Player>() {
//            @Override
//            protected void updateItem(Player p, boolean empty) {
//                super.updateItem(p, empty);
//
//                if (empty || p == null || p.getPlayerName() == null) {
//                    setText(null);
//                } else {
//                    setText(p.getPlayerName()+" "+p.getPlayerStatus());
//                }
//            }
//        });

        JFXButton testButton = new JFXButton("Demo");
        Label label = new Label("Test Label");
        //ImageView img = new ImageView(avatar);
        onlinePlayersLV.getItems().addAll( testButton, label);
    

    }    
    
}
