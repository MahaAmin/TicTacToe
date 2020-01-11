package playerModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerModel {
    public static Map<Integer, Player> players;
    public static void getPlayers(String data) throws ParseException {
        players = new LinkedHashMap<>();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(data);

        jsonArray.forEach((playerJson) -> {
            JSONObject playerJs = (JSONObject) playerJson;
            // add player to map
            Player pl = new Player();
            pl.setID(Integer.parseInt(playerJs.get("id").toString()));
            pl.setPlayerName(playerJs.get("name").toString());
            pl.setPlayerStatus(Integer.parseInt(playerJs.get("status").toString()));
            pl.setPlayerScore(Integer.parseInt(playerJs.get("score").toString()));
            players.put(Integer.parseInt(playerJs.get("id").toString()), pl);
        });
    }
}
