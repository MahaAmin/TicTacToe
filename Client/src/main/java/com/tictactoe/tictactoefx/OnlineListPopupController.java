package com.tictactoe.tictactoefx;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import playerModel.Player;
import playerModel.PlayerModel;


public class OnlineListPopupController implements Initializable {
    @FXML
    private JFXListView<Player> OnlineLV;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OnlineLV.setItems(PlayerModel.playerslist);
        OnlineLV.setCellFactory(param -> new ListCell<Player>() {
            @Override
            protected void updateItem(Player p, boolean empty) {
                super.updateItem(p, empty);

                if (empty || p == null || p.getPlayerName() == null) {
                    setText(null);
                } else {
                    setText(p.getPlayerName()+" "+p.getPlayerStatus());
                }
            }
        });
    }    
    
}
