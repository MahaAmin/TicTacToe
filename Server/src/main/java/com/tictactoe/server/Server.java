package com.tictactoe.server;

import com.tictactoe.actions.DBConnection;
import com.tictactoe.database.DatabaseManager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    ServerSocket myServerSocket;

    public Server() {
        startConnection();
    }

    private void startConnection() {
        try {
//            InetAddress addr=InetAddress.getByName("192.168.1.12");
            myServerSocket = new ServerSocket(5005, 50);
            System.out.println("server started: <http://127.0.0.1:5005>\n");
            DatabaseManager db=DBConnection.db;

            new Thread(() -> {
                try {
                    while (true) {
                        Socket socket = myServerSocket.accept();
                        new ServerHandler(socket);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        myServerSocket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
