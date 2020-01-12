package com.tictactoe.server;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import com.tictactoe.database.playerModel.Player;
import com.tictactoe.database.playerModel.PlayerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {
    @FXML
    private JFXToggleButton serverToggleButton;
    @FXML
    TableView<Player> allUsersTable;
    @FXML
    private TableColumn<Player, JFXButton> statusColumnT2;
    @FXML
    private TableColumn<Player, String> usernameColumnT2;
    @FXML
    private TableColumn<Player, Integer> scoreColumnT2;
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
        PlayerModel.getPlayersJSON();
        statusColumnT2.setCellValueFactory(new PropertyValueFactory<>("status"));
        usernameColumnT2.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumnT2.setCellValueFactory(new PropertyValueFactory<>("score"));
        allUsersTable.setItems(PlayerModel.playerslist);
        System.out.println(PlayerModel.playerslist);

    }
}
