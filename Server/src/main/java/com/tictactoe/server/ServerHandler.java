package com.tictactoe.server;

import com.tictactoe.actions.PlayRequest;
import com.tictactoe.database.gameModel.Game;
import com.tictactoe.database.gameModel.GameStatus;
import com.tictactoe.database.playerModel.Player;
import com.tictactoe.database.playerModel.PlayerModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class ServerHandler extends Thread {

    private static Vector<ServerHandler> playersSoc;
    private DataInputStream dis;
    public PrintStream ps;
    private Socket soc;
    private Player player;
    private JSONObject jsonMsg;
    private Game game;

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
            case"logout":
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

    private void playRequest(String data) {
        int from_id = Integer.parseInt(jsonMsg.get("from_id").toString());
        int to_id = Integer.parseInt(jsonMsg.get("to_id").toString());
        ServerHandler toPlayerHandler = getPlayerHandler(to_id);
        if (toPlayerHandler.soc.isConnected()) {
            // add new game with status request
            game = new Game();
            game.setFromPlayer(PlayerModel.getPlayer(from_id));
            game.setToPlayer(PlayerModel.getPlayer(to_id));
            game.setGameStatus(GameStatus.REQUEST);
            // send play request to a friend
            toPlayerHandler.ps.println(data);
        }
    }

    private void register() {
        boolean resp = PlayerModel.createPlayer(jsonMsg);
        JSONObject jresp = new JSONObject();
        jresp.put("type","register");
        jresp.put("status",resp);
        ps.println(jresp);
    }

    private void login() {
        JSONObject resp = PlayerModel.validatePlalyer(jsonMsg);
        if (resp.get("status").toString() == "true") {
            setPlayer(resp);
            resp.put("players", PlayerModel.getPlayersJSON());
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

    private void logout(){
        PlayerModel.logout(Integer.parseInt(jsonMsg.get("id").toString()));
    }
}
