package actions;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class GameConfig {
    private static int mode;
    private static int pc_level;
    private static String player_x;
    private static String player_o;
    private static boolean turn = false;
    private static ArrayList<String> XOList;

    public static void setMode(int mod) {
        mode = mod;
    }

    public static int getMode() {
        return mode;
    }

    public static void setPlayerX(String name) {
        player_x = name;
    }

    public static String getPlayerX() {
        return player_x;
    }

    public static void setPlayerO(String name) {
        player_o = name;
    }

    public static String getPlayerO() {
        return player_o;
    }


    public static void setTurn(boolean tu) {
        turn = tu;
    }

    public static boolean getTurn() {
        return turn;
    }

    public static void setPcLevel(int level) {
        pc_level = level;
    }

    public static int getPcLevel() {
        return pc_level;
    }

    public static void setXOList(String board) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(board);
            for (int i = 0; i < 9; i++) {
                XOList.set(i, jsonObject.get("cell" + i).toString());
            }
            System.out.println("XOLIST" + XOList);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<String> getXOList() {
        return XOList;
    }
}
