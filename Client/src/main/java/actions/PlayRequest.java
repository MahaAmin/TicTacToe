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
        // Returns Set view
        Set<Map.Entry<String, String>> st = fields.entrySet();

        for (Map.Entry<String, String> field : st) {
            jsonMsg.put(field.getKey(), field.getValue());
        }

        player.ps.println(jsonMsg.toJSONString());

    }

    public static void sendJSONObject(JSONObject jsonObject) {
        player.ps.println(jsonObject.toJSONString());
    }

}
