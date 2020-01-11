package actions;

public class GameConfig {
    private static int mode;
    private static int pc_level;
    private static String player_x;
    private static String player_o;
    private static boolean turn=false;

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

    public static void setPcLevel(int level){
        pc_level=level;
    }

    public static int getPcLevel(){
        return pc_level;
    }
}
