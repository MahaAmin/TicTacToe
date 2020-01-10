package com.tictactoe.server;

import com.tictactoe.actions.PlayRequest;
import com.tictactoe.database.gameModel.Game;
import com.tictactoe.database.gameModel.GameModel;
import com.tictactoe.database.gameModel.GameStatus;
import com.tictactoe.database.playerModel.Player;
import com.tictactoe.database.playerModel.PlayerModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ServerHandler extends Thread {

    private static Vector<ServerHandler> playersSoc = new Vector<>();
    private DataInputStream dis;
    private PrintStream ps;
    private Socket soc;
    private Player player;
    private JSONObject jsonMsg;
    private Game game;

    public ServerHandler(Socket socket) {

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
                playRequest();
                break;
            case "register":
                register();
                break;
            case "login":
                login();
                break;
            case "acceptRequest":
                acceptRequest();
                break;
            case "updateBoard":
                updateBoard();
            case "logout":
                logout();
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

    private void playRequest() {
        int to_id = Integer.parseInt(jsonMsg.get("to_id").toString());
        ServerHandler toPlayerHandler = getPlayerHandler(to_id);
        if (toPlayerHandler.soc.isConnected()) {
            // add new game with status request
            int game_id = GameModel.createGame(jsonMsg);
            jsonMsg.put("game_id", game_id);
            // set game data to [from_player]
            game = GameModel.getGame(game_id);
            // send play request to a friend
            toPlayerHandler.ps.println(jsonMsg.toJSONString());
        }
    }

    private void register() {
        boolean resp = PlayerModel.createPlayer(jsonMsg);
        JSONObject jresp = new JSONObject();
        jresp.put("type", "register");
        jresp.put("status", resp);
        ps.println(jresp);
    }

    private void login() {
        JSONObject resp = PlayerModel.validatePlalyer(jsonMsg);
        if (resp.get("status").toString() == "true") {
            setPlayer(resp);
            resp.put("players", PlayerModel.getPlayersJSON().toJSONString());
        }
        resp.put("type", "login");
        ps.println(resp);
    }

    private void acceptRequest() {
        // set game data to [to_player]
        game = GameModel.getGame(Integer.parseInt(jsonMsg.get("game_id").toString()));
        JSONObject jsonObject = new JSONObject();
        // friend accept to play with me
        if (jsonMsg.get("response").equals("true")) {
            jsonObject.put("type", "gameStart");
            // send to player 1  to start the game
            getPlayerHandler(game.getFromPlayer().getID()).ps.println(jsonObject.toJSONString());
            // send to player 2  to start the game
            getPlayerHandler(game.getToPlayer().getID()).ps.println(jsonObject.toJSONString());
        } else {
            System.out.println("game refused");
            jsonMsg.replace("type", "requestRejected");
            // send to player 1  that player 2 refuse to play with you
            getPlayerHandler(game.getFromPlayer().getID()).ps.println(jsonMsg.toJSONString());
        }
    }

    private void updateBoard() {
        jsonMsg.replace("type", "updateBoard");
        // send to player 1  to start the game
        getPlayerHandler(game.getFromPlayer().getID()).ps.println(jsonMsg.toJSONString());
        // send to player 2  to start the game
        getPlayerHandler(game.getToPlayer().getID()).ps.println(jsonMsg.toJSONString());
    }

    private ServerHandler getPlayerHandler(int player_id) {
        for (ServerHandler playerHandle : playersSoc) {
            if (playerHandle.player.getID() == player_id) {
                return playerHandle;
            }

        }
        return null;
    }


    private void logout() {
        PlayerModel.logout(jsonMsg);
    }
}
