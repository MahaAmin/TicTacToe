package player;

import actions.App;
import actions.GameConfig;
import actions.PlayRequest;
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
    public static void updateFriendBoard(ArrayList<String> xoTextOnButtonsList, int playerXScore, int playerOScore) {
        Map<String, String> map = new HashMap<>();
        map.put("type", "updateBoard");
        map.putAll(getXOListASJSON(xoTextOnButtonsList));
        map.put("playerXScore", Integer.toString(playerXScore));
        map.put("playerOScore", Integer.toString(playerOScore));
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


}
