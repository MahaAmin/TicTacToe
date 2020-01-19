package com.tictactoe.tictactoefx;

import actions.App;
import actions.GameConfig;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import playerModel.Player;
import playerModel.PlayerModel;

public class DashboardController implements Initializable {
    @FXML
    private JFXListView<Label> scoreBoardLV = new JFXListView();
    @FXML private JFXButton changePictureButton;
    
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
    
    @FXML private void logoutButtonClicked(ActionEvent event) throws IOException {
        App.getPlayerSoc().closeSocket();
        SwitchTo.mainScene(event);
    }

    @Override

    public void initialize(URL url, ResourceBundle rb) 
    {
        Player current = App.getPlayerSoc().getPlayer();
        System.out.println(current.getPlayerAvatar());
        changePictureButton.setVisible(false);
        mouseEnteredShadow.setColor(Color.GRAY); //set the shadow color of the  Region.
        mouseExitedShadow.setColor(Color.WHITE);

            Image image1;
            try
            {
                image1 = new Image(new FileInputStream("src/main/java/avatars/0.png"));
               // ImageView img = new ImageView(image1);
                profilePicture.setFill(new ImagePattern(image1));
            } 
            catch (FileNotFoundException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        

        PlayerModel.players.entrySet().stream().limit(5).forEach((player) -> {
            scoreBoardLV.getItems().add(new Label(player.getValue().getPlayerName() + ": " + player.getValue().getPlayerScore()));
        });

        scoreBoardLV.getStyleClass().add("mylistview");

        usernameLabel.setText(current.getPlayerName());
        scoreValueLabel.setText( String.valueOf(current.getPlayerScore()));

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
                changeRegionColor(profileRegion, mouseExitedShadow);
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
