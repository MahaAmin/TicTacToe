package playerModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerModel {
    public static Map<Integer, Player> players;
    public static ArrayList<Player> playerslist;
    public static ArrayList<Player> getPlayers(String data) throws ParseException {
        players = new LinkedHashMap<>();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(data);
        playerslist =new ArrayList<Player>();
        jsonArray.forEach((playerJson) -> {
            JSONObject playerJs = (JSONObject) playerJson;
            // add player to map
            Player pl = new Player();
            pl.setID(Integer.parseInt(playerJs.get("id").toString()));
            pl.setPlayerName(playerJs.get("name").toString());
            pl.setPlayerStatus(Integer.parseInt(playerJs.get("status").toString()));
            pl.setPlayerScore(Integer.parseInt(playerJs.get("score").toString()));
            if(playerJs.get("avatar")!=null)
                pl.setPlayerAvatar(playerJs.get("avatar").toString());
            players.put(Integer.parseInt(playerJs.get("id").toString()), pl);
            playerslist.add(pl);
        });

        return playerslist;
    }
}
