package com.tictactoe.tictactoefx;

import com.jfoenix.controls.JFXListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import player.PlayerHandler;
import player.PlayerSoc;


public class DashboardController implements Initializable {
    @FXML
    private JFXListView<Label> scoreBoardLV = new JFXListView();

    /*TableView Columns*/
    //Table 1 {User Specific}
    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> userNameColumnT1;
    @FXML
    private TableColumn<User, String> rankColumnT1;
    @FXML
    private TableColumn<User, String> scoreColumnT1;
    @FXML
    private TableColumn<User, String> avatarColumnT1;

    ObservableList<User> currentUser = FXCollections.observableArrayList();

    //Table 2 For all users
    @FXML
    private TableView<User> allUsersTable;

    @FXML
    private TableColumn<User, String> statusColumnT2;
    @FXML
    private TableColumn<User, String> usernameColumnT2;
    @FXML
    private TableColumn<User, String> rankColumnT2;
    @FXML
    private TableColumn<User, String> scoreColumnT2;

    ObservableList<User> allUsers = FXCollections.observableArrayList();

    @FXML
    private void vsPCbuttonClicked(ActionEvent event) throws IOException {
        //Transition to the GamePlay.fxml 
        SwitchTo.gamePlayScene(event);
    }

    @FXML
    private void vsOnlinePlayerButtonClicked(ActionEvent event) throws IOException {
        //Popup the OnlineListPopUp.fxml scene
//        SwitchTo.onlineListPopUpScene();
        PlayerHandler.sendPlayRequest(2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image1;
        try {

            image1 = new Image(new FileInputStream("C:\\Users\\Jaxon\\Desktop\\me.jpg"));
            ImageView img = new ImageView(image1);
            currentUser.add(new User(img, "Jaxon Tani", "2nd Tani", "11111"));
        } catch (FileNotFoundException ex) {
        }
    //playerModel.PlayerModel.players.get("1").getID() ;
       // System.err.println("AAA"+    playerModel.PlayerModel.players.get(1).getPlayerName());
        //currentUser = FXCollections.observableArrayList();
        
        for (int key:playerModel.PlayerModel.players.keySet())
             
            
            scoreBoardLV.getItems().add(new Label(playerModel.PlayerModel.players.get(key).getPlayerName() + ": " + playerModel.PlayerModel.players.get(key).getPlayerScore()));
        scoreBoardLV.getStyleClass().add("mylistview");

        //Table 1 [User Profile]
        userNameColumnT1.setCellValueFactory(new PropertyValueFactory<>("userName"));
        rankColumnT1.setCellValueFactory(new PropertyValueFactory<>("rank"));
        scoreColumnT1.setCellValueFactory(new PropertyValueFactory<>("score"));
        avatarColumnT1.setCellValueFactory(new PropertyValueFactory<>("avatar"));
       // player.PlayerSoc.getPlayerName();
     //   p.getPlayer().getPlayerName();
        //for (int key:playerModel.PlayerModel.players.keySet())
        currentUser.add(new User("Zalabia", "2nd", "100"));
        userTable.setItems(currentUser);

        //Table 2 [All users]
        statusColumnT2.setCellValueFactory(new PropertyValueFactory<>("status"));
        usernameColumnT2.setCellValueFactory(new PropertyValueFactory<>("userName"));
        rankColumnT2.setCellValueFactory(new PropertyValueFactory<>("rank"));
        scoreColumnT2.setCellValueFactory(new PropertyValueFactory<>("score"));

        allUsers.add(new User("Ahmed", "20", "100", "Offline"));
        allUsersTable.setItems(allUsers);
    }
}
