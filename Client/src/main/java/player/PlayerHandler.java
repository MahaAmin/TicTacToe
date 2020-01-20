package player;

import actions.App;
import actions.GameConfig;
import actions.PlayRequest;
import com.tictactoe.tictactoefx.GamePlayController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface PlayerHandler {

    public static void sendPlayRequest(int player_id) throws IOException {
        PlayerSoc playerSoc = App.getPlayerSoc();
        Map<String, String> map = new HashMap<>();
        map.put("type", "playRequest");
        map.put("from_id", Integer.toString(playerSoc.getPlayer().getID()));
        map.put("from_name", playerSoc.getPlayer().getPlayerName());
        map.put("to_id", Integer.toString(player_id));
        PlayRequest.sendJSON(map);
    }

    // send updated board to the server
    public static void updateFriendBoard(ArrayList<String> xoTextOnButtonsList) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "updateBoard");
        map.putAll(getXOListASJSON(xoTextOnButtonsList));
        PlayRequest.sendJSON(map);
    }

    // send to the other player a request for save the game
    public static void saveGameRequest(ArrayList<String> xoTextOnButtonsList) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "saveGameRequest");
        map.putAll(getXOListASJSON(xoTextOnButtonsList));
        PlayRequest.sendJSON(map);
    }

    static Map<String, String> getXOListASJSON(ArrayList<String> xoTextOnButtonsList) {
        Map<String, String> subMap = new HashMap<>();
        for (int i = 0; i < xoTextOnButtonsList.size(); i++) {
            subMap.put("cell" + i, xoTextOnButtonsList.get(i));
        }
        return subMap;
    }

    public static void updateGameStatus(String status) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "updateGameStatus");
        map.put("status", status);
        PlayRequest.sendJSON(map);
    }

    static void announceGameResult(int winner_id) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "announceGameResult");
        map.put("winner_id", Integer.toString(winner_id));
        PlayRequest.sendJSON(map);
    }

    static void updateScore(int score) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "updateScore");
        map.put("score", Integer.toString(score));
        PlayRequest.sendJSON(map);
    }

    static void resetGameRequest() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "resetGameRequest");
        PlayRequest.sendJSON(map);
    }

    static void resetGame() {
        GamePlayController game = App.getGamePlayController();
        GameConfig.resetBoard(game.xoTextOnButtonsList);
        game.gameOverFlag = false;
        game.printBoard();
        App.getPopUpWindow().close();
    }

    static void sendMessage(String message) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "sendMessage");
        map.put("message", message);
        PlayRequest.sendJSON(map);
    }

    static void updatePlayers() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "getall");
        PlayRequest.sendJSONObject(jsonObject);
    }


}
