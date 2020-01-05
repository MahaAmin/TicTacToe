package com.tictactoe.server;

import com.tictactoe.actions.PlayRequest;
import com.tictactoe.database.DBManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class ServerHandler extends Thread {

    private static Vector<ServerHandler> players;
    private DataInputStream dis;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private PrintStream ps;
    private Socket soc;
    private DBManager db;

    public ServerHandler(Socket socket, DBManager db) {
        players = new Vector<>();
        try {
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            soc = socket;
            players.add(this);
            this.db = db;
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                // receive OBJECT
                PlayRequest req = (PlayRequest) ois.readObject();
                if (req != null) {
                    objectHandle(req);
                } else {
                    // receive JSON
                    String data = dis.readLine();
                    if (!data.isEmpty()) {
                        jsonHandle(data);
                    }
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                ps.close();
                dis.close();
                ois.close();
                oos.close();
                soc.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void jsonHandle(String data) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonMsg = (JSONObject) parser.parse(data);

        if (jsonMsg.get("type").equals("register")) {
            boolean resp = db.Register(jsonMsg);
            ps.println(resp);
        } else if (jsonMsg.get("type").equals("login")) {
            boolean resp = db.Login(jsonMsg);
            ps.println(resp);
        }
    }

    private void objectHandle(PlayRequest req) {
        for (ServerHandler player : players) {
            try {
                player.oos.writeObject(req);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
