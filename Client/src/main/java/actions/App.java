package actions;

import javafx.stage.Stage;
import player.PlayerSoc;

public class App {

    private static Stage window;
    private static PlayerSoc playerSoc;

    public static void setWindow(Stage stage) {
        window = stage;
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setPlayerSoc() {
        playerSoc = new PlayerSoc();
    }

    public static PlayerSoc getPlayerSoc() {
        return playerSoc;
    }
}
