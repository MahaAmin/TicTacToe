package actions;

import org.json.simple.JSONObject;
import player.PlayerHandler;
import player.PlayerSoc;

import java.util.Map;
import java.util.Set;

public class PlayRequest {

    public static boolean sendJSON(Map<String, String> fields) {

        PlayerSoc player = App.getPlayerSoc();
        JSONObject jsonMsg = new JSONObject();
        // Returns Set view
        Set<Map.Entry<String, String>> st = fields.entrySet();

        for (Map.Entry<String, String> field : st) {
            jsonMsg.put(field.getKey(), field.getValue());
        }

        player.ps.println(jsonMsg.toJSONString());
        return true;

    }

}
