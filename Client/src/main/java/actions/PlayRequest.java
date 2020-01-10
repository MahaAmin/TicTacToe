package actions;

import org.json.simple.JSONObject;
import player.PlayerHandler;
import player.PlayerSoc;

import java.util.Map;
import java.util.Set;

public class PlayRequest {
    static PlayerSoc player = App.getPlayerSoc();

    public static void sendJSON(Map<String, String> fields) {
        JSONObject jsonMsg = new JSONObject();
        jsonMsg.putAll(fields);
        player.ps.println(jsonMsg.toJSONString());
    }

    public static void sendJSONObject(JSONObject jsonObject) {
        player.ps.println(jsonObject.toJSONString());
    }

}
