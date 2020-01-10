package player;

import actions.App;
import actions.PlayRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface PlayerHandler {

//    public final static PlayerSoc playerSoc = new PlayerSoc();

    public static void sendPlayRequest(int player_id) throws IOException {
        PlayerSoc playerSoc = App.getPlayerSoc();
        Map<String, String> map = new HashMap<>();
        map.put("type", "playRequest");
        map.put("from_id", Integer.toString(playerSoc.getPlayer().getID()));
        map.put("from_name", playerSoc.getPlayer().getPlayerName());
        map.put("to_id", Integer.toString(player_id));
        PlayRequest.sendJSON(map);
    }

    public static void updateFriendBoard(ArrayList<String> xoTextOnButtonsList, int playerXScore, int playerOScore) {
        Map<String, String> map = new HashMap<>();
        Map<String, String> subMap = new HashMap<>();
        map.put("type", "updateBoard");
        for (int i = 0; i < xoTextOnButtonsList.size(); i++) {
            subMap.put("cell" + i, xoTextOnButtonsList.get(i));
        }
        map.putAll(subMap);
        map.put("playerXScore", Integer.toString(playerXScore));
        map.put("playerOScore", Integer.toString(playerOScore));
        PlayRequest.sendJSON(map);
    }

}
