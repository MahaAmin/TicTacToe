package playerModel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class PlayerModel {
    public static Map<Integer, Player> players;

    public static void getPlayers(String data) throws ParseException {
        players = new HashMap<>();
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(data);
        for (Object key : jsonObj.keySet()) {
            //player id
            String keyStr = (String) key;
            // player object
            JSONObject playerJson = (JSONObject) jsonObj.get(keyStr);
            // add player to map
            Player pl = new Player();
            pl.setID(Integer.parseInt(playerJson.get("id").toString()));
            pl.setPlayerName(playerJson.get("name").toString());
            pl.setPlayerStatus(Integer.parseInt(playerJson.get("status").toString()));
            pl.setPlayerScore(Integer.parseInt(playerJson.get("score").toString()));
            players.put(Integer.parseInt(keyStr), pl);
        }

    }
}
