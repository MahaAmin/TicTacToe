package com.tictactoe.tictactoefx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class ChangeProfilePictureController implements Initializable {
    ArrayList <Image> ImagesArray = new ArrayList<>();
    @FXML private Circle imagePreview;
    Image currentImage;
    int index = 0;
    
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
    
    @FXML private void selectButtonClicked(ActionEvent event)
    {
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        Image image1;
        try
        {
            image1 = new Image(new FileInputStream("src/main/java/avatars/0.png"));
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
                currentImage = new Image(new FileInputStream("src/main/java/avatars/" + i  +".png"));
                ImagesArray.add(i, currentImage);
            } 
            
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(ChangeProfilePictureController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }    
    
}
