package actions;

import org.json.simple.JSONObject;
import player.PlayerHandler;
import player.PlayerSoc;

import java.util.Map;
import java.util.Set;

public class PlayRequest {
    static PlayerSoc player;

    public static void sendJSON(Map<String, String> fields) {
        player = App.getPlayerSoc();
        JSONObject jsonMsg = new JSONObject();
        jsonMsg.putAll(fields);
        player.ps.println(jsonMsg.toJSONString());
    }

    public static void sendJSONObject(JSONObject jsonObject) {
        player = App.getPlayerSoc();
        player.ps.println(jsonObject.toJSONString());
    }

}
