package com.tictactoe.tictactoefx;

import actions.App;
import actions.PlayRequest;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.PlayerSoc;
import playerModel.PlayerModel;

import java.util.HashMap;
import java.util.Map;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // connect to server socket
        App.setPlayerSoc();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setResizable(false);
        stage.setTitle("Tic-Tac-ToeFX");
        App.setWindow(stage);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            Map<String, String> map = new HashMap<>();
            map.put("type", "logout");
            map.put("id",Integer.toString(App.getPlayerSoc().getPlayer().getID()));
            System.out.println(map);
            PlayRequest.sendJSON(map);
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
