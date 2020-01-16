package com.tictactoe.tictactoefx;

import actions.App;
import actions.GameConfig;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import player.PlayerHandler;
import playerModel.Player;
import playerModel.PlayerModel;
import sun.audio.AudioPlayer;


public class DashboardController implements Initializable {
    @FXML
    private JFXListView<Label> scoreBoardLV = new JFXListView();

    /*TableView Columns*/
    //Table 1 {User Specific}
    @FXML
    private TableView<Player> userTable;

    @FXML
    private TableColumn<Player, String> userNameColumnT1;
    @FXML
    private TableColumn<Player, String> scoreColumnT1;
    @FXML
    private TableColumn<Player, String> avatarColumnT1;

    ObservableList<Player> currentUser = FXCollections.observableArrayList();


    @FXML
    private TableView<Player> allUsersTable;
    @FXML
    private TableColumn<Player, String> username;
    @FXML
    private TableColumn<Player, String> score;
    @FXML
    private TableColumn<Player, JFXButton> status;


    @FXML
    private void vsPCbuttonClicked(ActionEvent event) throws IOException {
        //Transition to the GamePlay.fxml
//        SwitchTo.gamePlayScene(event);
        GameConfig.setMode(1);  // pc mode
        SwitchTo.DifficultySelectionScene(event);
    }
    
    @FXML private void logoutButtonClicked(ActionEvent event)
    {
        System.out.println("logout clicked");
    }

    @FXML
    private void vsOnlinePlayerButtonClicked(ActionEvent event) throws IOException {
        //Popup the OnlineListPopUp.fxml scene

        //  SwitchTo.onlineListPopUpScene();


        //  SwitchTo.onlineListPopUpScene();

//        SwitchTo.onlineListPopUpScene();


        PlayerHandler.sendPlayRequest(2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image1;
        try {

            image1 = new Image(new FileInputStream("C:\\Users\\Jaxon\\Desktop\\me.jpg"));
            ImageView img = new ImageView(image1);
            currentUser.add(new Player());
        } catch (FileNotFoundException ex) {
        }
        //currentUser = FXCollections.observableArrayList();

        PlayerModel.players.values()
                .forEach((player) -> {
                    scoreBoardLV.getItems().add(new Label(player.getPlayerName() + ": " + player.getPlayerScore()));
                });

        scoreBoardLV.getStyleClass().add("mylistview");

        //Table 1 [User Profile]
        Player current = App.getPlayerSoc().getPlayer();
        avatarColumnT1.setCellValueFactory(new PropertyValueFactory<>("avatar"));
        userNameColumnT1.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumnT1.setCellValueFactory(new PropertyValueFactory<>("score"));

        currentUser.add(current);
        userTable.setItems(currentUser);

        username.setCellValueFactory(new PropertyValueFactory<>("name"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        allUsersTable.setItems(PlayerModel.playerslist);
    }
}
