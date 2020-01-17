package com.tictactoe.tictactoefx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import actions.App;
import actions.PlayRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.json.simple.JSONObject;
import playerModel.Player;
import playerModel.PlayerModel;
import org.kordamp.ikonli.javafx.FontIcon;

public class ChangeProfilePictureController implements Initializable {
    ArrayList <Image> ImagesArray = new ArrayList<>();
    String onTheRaye2 ;
    Media meow;
    MediaPlayer mediaPlayer;
    @FXML private Circle imagePreview;
    @FXML private FontIcon pawsIcon1, pawsIcon2, pawsIcon3, pawsIcon4, pawsIcon5 ;
    Image currentImage;
    int index = 0;


    @FXML private Label upperLabel;
    
    @FXML private void previousButtonClicked(ActionEvent event)
    {
        if(index == 0)
            index = ImagesArray.size() - 1; //go to the last image
        else 
            index--;
        
        imagePreview.setFill(new ImagePattern(ImagesArray.get(index)));
    }
    
    @FXML private void nextButtonClicked(ActionEvent event)
    {
        if(index == ImagesArray.size() - 1)
            index = 0;
        
        else 
            index++;
        
        imagePreview.setFill(new ImagePattern(ImagesArray.get(index)));
    }
    
    @FXML private void backButtonClicked(ActionEvent event) throws IOException
    {
        SwitchTo.dashboardScene(event);
    }
    
    @FXML private void selectButtonClicked(ActionEvent event) throws IOException {
        String img= "./src/main/java/avatars/"+index+".png";
        JSONObject req = new JSONObject();
        req.put("type","updateAvatar");
        req.put("src",img);
        req.put("id", App.getPlayerSoc().getPlayer().getID());
        PlayRequest.sendJSON(req);
        SwitchTo.dashboardScene(event);
    }
    
    @FXML private void meowRegionMouseEntered(MouseEvent event)
    {
        String a5arbesh =  "\u0627\u062e\u0631\u0628\u0634";
        upperLabel.setText(a5arbesh);
        pawsIcon1.setVisible(true);        pawsIcon2.setVisible(true); pawsIcon3.setVisible(true); pawsIcon4.setVisible(true);  pawsIcon5.setVisible(true);

        mediaPlayer = new MediaPlayer(meow);
        mediaPlayer.play();
    }
    
    @FXML private void meowRegionMouseExited(MouseEvent event)
    {
         pawsIcon1.setVisible(false);   pawsIcon2.setVisible(false);   pawsIcon3.setVisible(false);    pawsIcon4.setVisible(false); pawsIcon5.setVisible(false);
        upperLabel.setText(onTheRaye2);
        mediaPlayer.stop();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         meow = new Media(new File("src/main/java/avatars/Meow.mp3").toURI().toString());  
         pawsIcon1.setVisible(false);   pawsIcon2.setVisible(false);   pawsIcon3.setVisible(false);    pawsIcon4.setVisible(false); pawsIcon5.setVisible(false);
        
        onTheRaye2 = " \u0627\u062e\u062a\u0627\u0631 \u0635\u0648\u0631\u062a\u0643 \u0639\u0627\u0644\u0631\u0627\u064a\u0642 \u0639\u0634\u0627\u0646 \u0645\u064a\u0627\u0648 \u0628\u064a\u062a\u0636\u0627\u064a\u0642";

        upperLabel.setText(onTheRaye2);
        Image image1;
        try
        {
            image1 = new Image(new FileInputStream("./src/main/java/avatars/0.png"));
            imagePreview.setFill(new ImagePattern(image1));
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Populate the images arrayList 
        
        for(int i = 0; i < 9; i++)
        {
            try 
            {
                currentImage = new Image(new FileInputStream("./src/main/java/avatars/" + i  +".png"));
                ImagesArray.add(i, currentImage);
            } 
            
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(ChangeProfilePictureController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }    
    
}
