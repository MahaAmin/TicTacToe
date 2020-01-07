package com.tictactoe.actions;

import com.tictactoe.database.DatabaseManager;
import com.tictactoe.database.playerModel.PlayerModel;


public class App {
    private static DatabaseManager db;

    public static void setDB() {
        db = new DatabaseManager();
        PlayerModel.getPlayers();
    }

    public static DatabaseManager getDB() {
        return db;
    }
}
