package com.tictactoe.server;

import com.tictactoe.actions.PlayRequest;
import com.tictactoe.database.playerModel.Player;
import com.tictactoe.database.playerModel.PlayerModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class ServerHandler extends Thread {

    private static Vector<ServerHandler> players;
    private DataInputStream dis;
    //    private ObjectInputStream ois;
//    private ObjectOutputStream oos;
    private PrintStream ps;
    private Socket soc;
    private Player player;
    private JSONObject jsonMsg;

    public ServerHandler(Socket socket) {
        players = new Vector<>();
        try {
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
//            ois = new ObjectInputStream(socket.getInputStream());
//            oos = new ObjectOutputStream(socket.getOutputStream());
            soc = socket;
            players.add(this);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ServerHandler() {
    }

    @Override
    public void run() {
        try {
            while (true) {
                // receive OBJECT
//                PlayRequest req = (PlayRequest) ois.readObject();
//                if (req != null) {
//                    objectHandle(req);
//                } else {
//
//                }

                // receive JSON
                String data = dis.readLine();
                if (!data.isEmpty()) {
                    System.out.println(data);
                    jsonHandle(data);
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                ps.close();
                dis.close();
//                ois.close();
//                oos.close();
                soc.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void jsonHandle(String data) throws ParseException {
        JSONParser parser = new JSONParser();
        jsonMsg = (JSONObject) parser.parse(data);

        switch (jsonMsg.get("type").toString()) {
            case "setPlayer":
                setPlayer();
                break;
            case "playRequest":
                playRequest(data);
                break;
            case "register":
                register();
                break;
            case "login":
                login();
                break;
        }

    }

    private void setPlayer() {

        player = new Player();
        player.setID(
                Integer.parseInt(
                        jsonMsg.get("player_id").toString()
                )
        );
    }

    private void playRequest(String data) {
        ServerHandler toPlayerHandler = getPlayerHandler(Integer.parseInt(jsonMsg.get("to_id").toString()));
        toPlayerHandler.ps.println(data);
    }

    private void register() {
        boolean resp = PlayerModel.createPlayer(jsonMsg);
        ps.println(resp);
    }

    private void login() {
        boolean resp = PlayerModel.validatePlalyer(jsonMsg);
        ps.println(resp);
    }

    private ServerHandler getPlayerHandler(int player_id) {
        for (ServerHandler playerHandle : players) {
            if (playerHandle.player.getID() == player_id) {
                return playerHandle;
            }

        }
        return new ServerHandler();
    }
}
