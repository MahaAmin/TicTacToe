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
    private static int player_x_score;
    private static int player_o_score;
    private static boolean turn = false;
    private static ArrayList<String> XOList = new ArrayList<>();
    private static JSONObject requestPobUpJson;
    private static JSONObject rejectedPobUpJson;

    private static JSONObject saveGamePobUpJson;
    private static JSONObject saveGameRejectedPobUp;
    private static JSONObject saveGameSuccesPobUp;
    private static JSONObject winnerPobUpJson;
    private static String winner;
    private static JSONObject oldSavePobUp;
    private static JSONObject serverErrorPopup;
    private static JSONObject gameFaildPobUp;
    private static JSONObject resetPobUP;


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
                XOList.add(jsonObject.get("cell" + i).toString());
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<String> getXOList() {
        return XOList;
    }

    public static void resetBoard(ArrayList<String> board) {
        for (int i = 0; i < board.size(); i++) {
            board.set(i, " ");
        }
    }

    public static ArrayList<String> getEmptyBoard() {
        ArrayList<String> board = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            board.add(" ");
        }
        return board;
    }

    public static void setRequestPobUpJson(JSONObject jsonObject) {
        requestPobUpJson = jsonObject;
    }

    public static JSONObject getRequestPobUpJson() {
        return requestPobUpJson;

    }

    public static void setPlayerXSore(int score) {
        player_x_score = score;
    }

    public static int getPlayerXScore() {
        return player_x_score;
    }

    public static void setPlayerOSore(int score) {
        player_o_score = score;
    }

    public static int getPlayerOScore() {
        return player_o_score;
    }

    public static void setRejectedPobUpJson(JSONObject jsonObject) {
        rejectedPobUpJson = jsonObject;
    }

    public static JSONObject getRejectedPobUpJson() {
        return rejectedPobUpJson;
    }


    public static void setSaveGamePobUpJson(JSONObject jsonObject) {
        saveGamePobUpJson = jsonObject;
    }

    public static JSONObject getSaveGamePobUpJson() {
        return saveGamePobUpJson;
    }

    public static void setSaveGameRejectedPobUp(JSONObject jsonObject) {
        saveGameRejectedPobUp = jsonObject;
    }

    public static JSONObject getSaveGameRejectedPobUp() {
        return saveGameRejectedPobUp;
    }

    public static void setSaveGameSuccesPobUpPobUp(JSONObject jsonObject) {
        saveGameSuccesPobUp = jsonObject;
    }

    public static JSONObject getSaveGameSuccesPobUp() {
        return saveGameSuccesPobUp;
    }

    public static void setWinnerPobUpJson(JSONObject jsonObject) {
        winnerPobUpJson = jsonObject;
    }

    public static JSONObject getWinnerPobUpJson() {
        return winnerPobUpJson;
    }

    public static void setWinner(String win) {
        winner = win;
    }

    public static String getWinner() {
        return winner;
    }

    public static void setOldSavePobUp(JSONObject jsonObject) {
        oldSavePobUp = jsonObject;
    }

    public static JSONObject getOldSavePobUp() {
        return oldSavePobUp;
    }

    public static void setServerErrorPopup(JSONObject jsonObject) {
        serverErrorPopup = jsonObject;
    }

    public static JSONObject getServerErrorPopup() {
        return serverErrorPopup;
    }

    public static void setGameFaildPobUp(JSONObject jsonObject) {
        gameFaildPobUp = jsonObject;
    }

    public static JSONObject getGameFaildPobUp() {
        return gameFaildPobUp;
    }

    public static void setResetPobUP(JSONObject jsonObject) {
        resetPobUP = jsonObject;
    }

    public static JSONObject getResetPobUP() {
        return resetPobUP;
    }


}
