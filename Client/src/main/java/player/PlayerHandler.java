package player;

import actions.PlayRequest;
import actions.RequestType;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface PlayerHandler {

    public final static PlayerSoc playerSoc = new PlayerSoc();

    public static void sendPlayRequest(int player_id) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("type", "playRequest");
        map.put("from_id", Integer.toString(playerSoc.getPlayer().getID()));
        map.put("from_name", playerSoc.getPlayer().getPlayerName());
        map.put("to", Integer.toString(player_id));
        PlayRequest.sendJSON(map);
    }

}
