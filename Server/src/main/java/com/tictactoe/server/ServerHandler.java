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

    private static Vector<ServerHandler> playersSoc;
    private DataInputStream dis;
    private PrintStream ps;
    private Socket soc;
    private Player player;
    private JSONObject jsonMsg;

    public ServerHandler(Socket socket) {
        playersSoc = new Vector<>();
        try {
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintStream(socket.getOutputStream());
            soc = socket;
            playersSoc.add(this);

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

                // receive JSON
                String data = dis.readLine();
                if (!data.isEmpty()) {
                    jsonHandle(data);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                ps.close();
                dis.close();
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

    private void setPlayer(JSONObject client) {

        player = new Player();
        player.setID(
                Integer.parseInt(client.get("id").toString())
        );
        player.setPlayerName(client.get("name").toString());
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
        JSONObject resp = PlayerModel.validatePlalyer(jsonMsg);
        if (resp.get("status").toString() == "true") {
            setPlayer(resp);
        }
        resp.put("type", "login");
        System.out.println(resp);
        ps.println(resp);
    }

    private ServerHandler getPlayerHandler(int player_id) {
        for (ServerHandler playerHandle : playersSoc) {
            if (playerHandle.player.getID() == player_id) {
                return playerHandle;
            }

        }
        return new ServerHandler();
    }
}
