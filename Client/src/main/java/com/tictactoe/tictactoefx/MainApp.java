package com.tictactoe.tictactoefx;

import actions.App;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.PlayerHandler;
import player.PlayerSoc;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // connect to server socket
//        final PlayerSoc player = PlayerHandler.playerSoc;

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setResizable(false);
        stage.setTitle("Tic-Tac-ToeFX");
        App.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
