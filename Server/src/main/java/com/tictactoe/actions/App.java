package com.tictactoe.actions;

import com.tictactoe.database.DatabaseManager;
import com.tictactoe.database.playerModel.PlayerModel;

import java.net.ServerSocket;


public class App {
    private static DatabaseManager db;
    private static ServerSocket serverSocket;

    public static void setDB() {
        db = new DatabaseManager();
        PlayerModel.getPlayers();
    }

    public static DatabaseManager getDB() {
        return db;
    }

    public static void setServerSocket(ServerSocket serverSocket1) {
        serverSocket = serverSocket1;
    }

    public static ServerSocket getServerSocket() {
        return serverSocket;
    }

}
