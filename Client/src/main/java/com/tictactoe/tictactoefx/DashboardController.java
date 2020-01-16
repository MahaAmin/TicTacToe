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
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import player.PlayerHandler;
import playerModel.Player;
import playerModel.PlayerModel;
import sun.audio.AudioPlayer;

public class DashboardController implements Initializable {
    @FXML
    private JFXListView<Label> scoreBoardLV = new JFXListView();
    @FXML private JFXButton changePictureButton;
    
    /*TableView Columns*/
    @FXML
    private TableView<Player> allUsersTable;
    @FXML
    private TableColumn<Player, String> username;
    @FXML
    private TableColumn<Player, String> score;
    @FXML
    private TableColumn<Player, JFXButton> status;
    @FXML private Circle profilePicture;
    @FXML private Label usernameLabel , scoreValueLabel;
    
    //CSSing some components
    @FXML private Region profileRegion, dashboardRegion, scoreBoardRegion, playersListRegion ;
    DropShadow mouseEnteredShadow = new DropShadow(); // GRAY Color ( onMouseEntered)
    DropShadow mouseExitedShadow = new DropShadow(); //WHITE Color (Default On and onMouseExited)
    


    @FXML
    private void vsPCbuttonClicked(ActionEvent event) throws IOException {
        //Transition to the GamePlay.fxml
        GameConfig.setMode(1);  // pc mode
        SwitchTo.DifficultySelectionScene(event);
    }
    
    @FXML private void logoutButtonClicked(ActionEvent event)
    {
        System.out.println("logout clicked");
    }

    @FXML
    private void vsOnlinePlayerButtonClicked(ActionEvent event) throws IOException {
        PlayerHandler.sendPlayRequest(2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
            changePictureButton.setVisible(false);
            mouseEnteredShadow.setColor(Color.GRAY); //set the shadow color of the  Region.
            mouseExitedShadow.setColor(Color.WHITE);

            Image image1;
            try
            {
                image1 = new Image(new FileInputStream("/media/onepiece/partition1/ITI/java/TicTacToe/Client/src/main/java/com/tictactoe/tictactoefx/blue.jpg"));
               // ImageView img = new ImageView(image1);
                profilePicture.setFill(new ImagePattern(image1));
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //currentUser.add(new User(img, "Jaxon Tani", "2nd Tani", "11111"));

        
        
        

        PlayerModel.players.entrySet().stream().limit(5).forEach((player) -> {
            scoreBoardLV.getItems().add(new Label(player.getValue().getPlayerName() + ": " + player.getValue().getPlayerScore()));
        });

        scoreBoardLV.getStyleClass().add("mylistview");

        //Table 1 [User Profile]
        Player current = App.getPlayerSoc().getPlayer();
        usernameLabel.setText(current.getPlayerName());
        scoreValueLabel.setText( String.valueOf(current.getPlayerScore()));
//        userNameColumnT1.setCellValueFactory(new PropertyValueFactory<>("userName"));
//
//
//        scoreColumnT1.setCellValueFactory(new PropertyValueFactory<>("score"));
//        avatarColumnT1.setCellValueFactory(new PropertyValueFactory<>("avatar"));
//
//        currentUser.add(new User(current.getPlayerName(), "f", Integer.toString(current.getPlayerScore()), current.getPlayerAvatar()));
//        userTable.setItems(currentUser);

        username.setCellValueFactory(new PropertyValueFactory<>("name"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        allUsersTable.setItems(PlayerModel.playerslist);
    }
    
    @FXML private void changePictureButtonClicked(ActionEvent event) throws IOException
    {
        SwitchTo.ChangeProfilePictureScene(event);
    }
    
        private void changeRegionColor(Region region,  DropShadow shadowType)
        {
            region.setEffect(shadowType);
        }
    
            /*
            CSS Functions section
            */
        
            //Floating effect for profilePic region.
            @FXML private void profilePictureMouseEntered(MouseEvent event)
            {
                changePictureButton.setVisible(true);
                changeRegionColor(profileRegion, mouseEnteredShadow);
            }
            
            @FXML private void profilePictureMouseExited(MouseEvent event)
            {
                changePictureButton.setVisible(false);
                changeRegionColor(profileRegion, mouseEnteredShadow);
            }
            
            @FXML private void profileRegionMouseEntered(MouseEvent event)
            {
                changeRegionColor(profileRegion, mouseEnteredShadow);
            }

            @FXML private void profileRegionMouseExited(MouseEvent event)
            {
                 changeRegionColor(profileRegion, mouseExitedShadow);
            }
            

            
            //Floating effect for dashboard Region
            @FXML private void dashboardRegionMouseEntered(MouseEvent event)
            {
                 changeRegionColor(dashboardRegion, mouseEnteredShadow);
            }
            
            @FXML private void dashboardRegionMouseExited(MouseEvent event)
            {
                changeRegionColor(dashboardRegion, mouseExitedShadow);
            }
            
            //Floating effect for scoreBoard region
            @FXML private void scoreBoardRegionMouseEntered(MouseEvent event)
            {
                changeRegionColor(scoreBoardRegion, mouseEnteredShadow);
            }
            
            @FXML private void scoreBoardRegionMouseExited(MouseEvent event)
            {
                changeRegionColor(scoreBoardRegion, mouseExitedShadow);
            }
            
            //Floating effect for PlayersList Region
            @FXML private void playersListRegionMouseEntered(MouseEvent event)
            {
                changeRegionColor(playersListRegion, mouseEnteredShadow);
            }
            
            @FXML private void playersListRegionMouseExited(MouseEvent event)
            {
                changeRegionColor(playersListRegion, mouseExitedShadow);
            }
    

}
