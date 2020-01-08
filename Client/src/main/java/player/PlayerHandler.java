package player;

import actions.App;
import actions.PlayRequest;
import actions.RequestType;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface PlayerHandler {

    public static void sendPlayRequest(int player_id) throws IOException {
        PlayerSoc playerSoc= App.getPlayerSoc();
        Map<String, String> map = new HashMap<>();
        map.put("type", "playRequest");
        map.put("from_id", Integer.toString(playerSoc.getPlayer().getID()));
        map.put("from_name", playerSoc.getPlayer().getPlayerName());
        map.put("to_id", Integer.toString(player_id));
        PlayRequest.sendJSON(map);
    }

}
