package com.tictactoe.database.playerModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class updateView {
    public static ObservableList<Player> players;
    public static ObservableList<Player> GetObservable(){
        players= FXCollections.observableArrayList();
        return players;
    }
    public static void updatePlayer(ArrayList<Player> map){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                players.clear();
                System.out.println(map.size());
                for (int i=0;i<map.size();++i) {
                    players.add(map.get(i));
                }
            }
        });
    }
}
