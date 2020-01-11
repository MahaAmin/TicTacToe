package actions;

public class GameConfig {
    private static int mode;
    private static String player_x;
    private static String player_o;
    private static int turn;

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


    public static void setTurn(int tu) {
        turn = tu;
    }

    public static int getTurn() {
        return turn;
    }
}
