package com.tictactoe.server;

import com.tictactoe.database.DBManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server {

    ServerSocket myServerSocket;
    DBManager db;

    public Server() {
        startConnection();
    }

    private void startConnection() {
        try {
//            InetAddress addr=InetAddress.getByName("192.168.1.12");
            myServerSocket = new ServerSocket(5005, 50);
            System.out.println("server started: <http://127.0.0.1:5005>\n");
            db = new DBManager();
//            new Thread(() -> {
//
//                try {
//                    while (true) {
//                        Socket socket = myServerSocket.accept();
//                        new ServerHandler(socket);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    try {
//                        myServerSocket.close();
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//
//            });
            try {
                while (true) {
                    Socket socket = myServerSocket.accept();
                    new ServerHandler(socket,db);
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    myServerSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
