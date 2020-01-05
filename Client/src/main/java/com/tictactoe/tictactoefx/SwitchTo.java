package com.tictactoe.tictactoefx;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SwitchTo 
{

    static ArrayList<String> ScenesList = new ArrayList<String>() { 
            { 
                add("Scene"); // 0 ==> Scene.fxml
                add("RegisterForm"); // 1 ==> RegisterForm.fxml
                add("Dashboard"); // 2 ==> Dashboard.fxml
                add("GamePlay"); // 3 ==> GamePlay.fxml
            } 
        }; 
       
    static ArrayList<String> popUpList = new ArrayList<String>() { 
            { 
                add("OnlineListPopup"); // 0 ==> OnlineListPopup.fxml
                
            } 
        }; 
    
    public static void transition(ActionEvent event, int sceneID) throws IOException
    {
        //Transition to the RegisterForm.fxml scene
        Parent root = FXMLLoader.load(SwitchTo.class.getResource("/fxml/" + ScenesList.get(sceneID) + ".fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    public static void popupTransition(int popUpID, String title) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(SwitchTo.class.getResource("/fxml/" + popUpList.get(popUpID) + ".fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
    }
    
    public static void mainScene(ActionEvent event) throws IOException
    {
        transition(event, 0);
    }
    
    public static void registerFormScene(ActionEvent event) throws IOException
    {
        transition(event, 1);
    }
    
    public static void dashboardScene(ActionEvent event) throws IOException
    {
        transition(event, 2);
    }
    
    public static void gamePlayScene(ActionEvent event) throws IOException
    {
        transition(event, 3);
    }
    
    public static void onlineListPopUpScene() throws IOException
    {
        popupTransition(0, "Online List");
    }

}
