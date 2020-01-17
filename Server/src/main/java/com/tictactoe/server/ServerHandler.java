package com.tictactoe.server;

import com.tictactoe.actions.GameTypeScore;
import com.tictactoe.database.gameModel.Game;
import com.tictactoe.database.gameModel.GameModel;
import com.tictactoe.database.playerModel.Player;
import com.tictactoe.database.playerModel.PlayerModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

public class ServerHandler extends Thread {

    public static Vector<ServerHandler> playersSoc = new Vector<>();
    public DataInputStream dis;
    public PrintStream ps;
    public Socket soc;
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
            while (soc != null && dis != null) {
                // receive JSON
                String data = dis.readLine();
                if (!data.isEmpty()) {
                    jsonHandle(data);
                }
            }
        } catch (Exception ex) {
            System.out.println("error4");
            try {
                ps.close();
                dis.close();
                soc.close();
            } catch (IOException e) {
                ex.printStackTrace();
                try {
                    playersSoc.remove(this);
                    MakeStatusOffline();
                } catch (Exception ew) {
                    System.out.println("socket didn't close in logout function");
                }
            }
        }
    }

    private void MakeStatusOffline() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", player.getID());
        jsonObject.put("status", "0");
        PlayerModel.updateStatus(jsonObject);
        getall();
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
                break;
            case "logout":
                logout();
                break;
            case "getall":
                System.out.println("This is sending the update");
                getall();
                break;
            case "saveGameRequest":
                saveGameRequest();
                break;
            case "saveGameAnswer":
                saveGameAnswer();
                break;
            case "chosenPlayRequest":
                chosenPlayRequest();
                break;
            case "updateGameStatus":
                updateGameStatus();
                break;
            case "announceGameResult":
                announceGameResult();
                break;
            case "updateScore":
                updateScore();
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

    /**
     * for player1
     * receive a new game request from player then check game type[old,new]
     * if there is an old game ask player1 to choose whet he want to start [old,new]
     * if there is not an old game then send a new play request to player2
     */
    private void playRequest() {
        int to_id = Integer.parseInt(jsonMsg.get("to_id").toString());
        ServerHandler toPlayerHandler = getPlayerHandler(to_id);
        if (toPlayerHandler != null) {
            // check the other player is connected
            if (toPlayerHandler.soc.isConnected()) {
                int old_game_id = GameModel.getGame(jsonMsg);
                if (old_game_id > 0) {
                    int from_id = Integer.parseInt(jsonMsg.get("from_id").toString());
                    ServerHandler fromPlayerHandler = getPlayerHandler(from_id);
                    jsonMsg.replace("type", "chooseGame");
                    jsonMsg.put("old_game", old_game_id);
                    fromPlayerHandler.ps.println(jsonMsg.toJSONString());
                } else {
                    sendNewGameRequest(toPlayerHandler);
                }

            } else {
                // to_player is offline now
            }
        }
    }

    /**
     * for player1
     * after player1 has cheesed the game type
     * if he chosed to resume the old game then ask player2 for that
     * if he chosed new game then remove old one and create a new game
     */
    private void chosenPlayRequest() {
        int to_id = Integer.parseInt(jsonMsg.get("to_id").toString());
        ServerHandler toPlayerHandler = getPlayerHandler(to_id);
        if (toPlayerHandler != null) {
            jsonMsg.replace("type", "playRequest");
            int game_id = Integer.parseInt(jsonMsg.get("old_game").toString());
            if (jsonMsg.get("response").equals("true")) {
                game = GameModel.getGame(game_id);
                jsonMsg.put("game_id", game_id);
                toPlayerHandler.ps.println(jsonMsg.toJSONString());
            } else {
                // remove old game
                GameModel.removeGame(game_id);
                jsonMsg.remove("old_game");
                // create new game and send to player2
                sendNewGameRequest(toPlayerHandler);
            }
        }

    }

    private void sendNewGameRequest(ServerHandler toPlayerHandler) {
        // add new game with status request
        int game_id = GameModel.createGame(jsonMsg);
        jsonMsg.put("game_id", game_id);
        // set game data to [from_player]
        game = GameModel.getGame(game_id);
        // send play request to a friend
        System.out.println("game " + jsonMsg);
        toPlayerHandler.ps.println(jsonMsg.toJSONString());
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
        getall();
    }

    /**
     * for player2
     * if player2 accepted the new/old game request start the game on both players side
     * if not send rejection alert to player1
     */
    private void acceptRequest() {
        // set game data to player2
        game = GameModel.getGame(Integer.parseInt(jsonMsg.get("game_id").toString()));
        Player from_player = game.getFromPlayer();
        // friend accept to play with me
        if (jsonMsg.get("response").equals("true")) {
            Player to_player = game.getToPlayer();

            JSONObject jsono = new JSONObject();
            // update player2 status to busy
            jsono.put("id", to_player.getID());
            jsono.put("status", 2);
            PlayerModel.updateStatus(jsono);
            // update player1 status to busy
            jsono.replace("id", from_player.getID());
            PlayerModel.updateStatus(jsono);

            jsonMsg.replace("type", "gameStart");
            jsonMsg.put("to_name", to_player.getPlayerName());
            jsonMsg.put("from_score", from_player.getPlayerScore());
            jsonMsg.put("to_score", to_player.getPlayerScore());
            if (jsonMsg.containsKey("old_game")) {
                jsonMsg.put("board", game.getBoard());
            }

            // send to player 1  to start the game
            getPlayerHandler(from_player.getID()).ps.println(jsonMsg.toJSONString());
            // send to player 2  to start the game
            getPlayerHandler(to_player.getID()).ps.println(jsonMsg.toJSONString());
        } else {
            System.out.println("game refused");
            jsonMsg.replace("type", "requestRejected");
            // send to player 1  that player 2 refuse to play with you
            getPlayerHandler(from_player.getID()).ps.println(jsonMsg.toJSONString());
        }
    }


    /**
     * for both players
     */
    private void updateBoard() {
        jsonMsg.replace("type", "updateBoard");
        // send to player 1  to start the game
        getPlayerHandler(game.getFromPlayer().getID()).ps.println(jsonMsg.toJSONString());
        // send to player 2  to start the game
        getPlayerHandler(game.getToPlayer().getID()).ps.println(jsonMsg.toJSONString());
    }


    /**
     * for both players
     */
    private void saveGameRequest() {
        // if player 2 who want to save the game send save game request to player1
        // if player 1 who want to save the game send save game request to player1
        whichPlayer(jsonMsg);
    }

    /**
     * for both players
     */
    private void saveGameAnswer() {

        // if player 2 who want to save the game send save game answer to him
        // if player 1 who want to save the game send save game answer to him
        whichPlayer(jsonMsg);
        // if the both players wants to save the game then save it
        if (jsonMsg.get("response").equals("true")) {
            jsonMsg.remove("type");
            jsonMsg.remove("response");
            game.setBoard(jsonMsg);
            GameModel.updateGameBoard(jsonMsg, game.getId());

        }
    }

    /**
     * send json object to player against you
     */
    private void whichPlayer(JSONObject jsonObject) {
        ServerHandler serverHandle;

        if (game.getFromPlayer().getID() == player.getID()) {
            serverHandle = getPlayerHandler(game.getToPlayer().getID());
        } else {
            serverHandle = getPlayerHandler(game.getFromPlayer().getID());
        }
        serverHandle.ps.println(jsonObject.toJSONString());
    }


    /**
     * for both players
     */
    private void updateGameStatus() {
        String status = jsonMsg.get("status").toString();
        Boolean isUpdated = GameModel.updateGameStatus(game.getId(), status);
        if (isUpdated) {
            jsonMsg.replace("type", "gameStatusNotify");
            whichPlayer(jsonMsg);
        }
    }


    /**
     * for both players
     * update game status to complete
     * if any player won then
     * set winner id in the game
     * update winner score
     * else no one won then
     * send game result fr both players
     */
    private void announceGameResult() {
        int winner_id = Integer.parseInt(jsonMsg.get("winner_id").toString());
        GameModel.updateGameStatus(game.getId(), "COMPLETE");
        if (winner_id != 0) {
            GameModel.setWinner(game.getId(), winner_id);
            int new_score = player.getPlayerScore() + GameTypeScore.WITH_FRIEND;
            player.setPlayerScore(new_score);
            jsonMsg.put("new_score", new_score);
            PlayerModel.updateScore(player.getID(), new_score);
        }
        jsonMsg.replace("type", "announceGame");
        getPlayerHandler(game.getFromPlayer().getID()).ps.println(jsonMsg.toJSONString());
        getPlayerHandler(game.getToPlayer().getID()).ps.println(jsonMsg.toJSONString());
    }


    private void updateScore() {
        int score = Integer.parseInt(jsonMsg.get("score").toString());
        score += player.getPlayerScore();
        player.setPlayerScore(score);
        PlayerModel.updateScore(player.getID(), score);
        jsonMsg.replace("score", score);
        ps.println(jsonMsg.toJSONString());
    }


    private ServerHandler getPlayerHandler(int player_id) {
        for (ServerHandler playerHandle : playersSoc) {
            if (playerHandle.player.getID() == player_id) {
                return playerHandle;
            }
        }
        return null;
    }

    //logout and getall function that calls a broadcast message to send the updated data collection to all users
//this function triggers when a logout request is received at the server side
    private void logout() {

        PlayerModel.logout(jsonMsg);
        //System.out.println("This is the logout id"+jsonMsg.get("id"));p
        try {
            playersSoc.remove(this);
            getall();
        } catch (Exception ew) {
            System.out.println("socket didn't close in logout function");
        }
    }

    public void getall() {
        JSONObject resp = new JSONObject();
        resp.put("type", "getall");
        resp.put("players", PlayerModel.getPlayersJSON());
        for (ServerHandler playerHandle : playersSoc) {
            playerHandle.ps.println(resp);
        }
    }
}
