package actions;

import com.tictactoe.tictactoefx.GamePlayController;
import javafx.application.Platform;
import javafx.stage.Stage;
import player.PlayerSoc;

public class App {

    private static Stage window;
    private static Stage popUpWindow;
    private static PlayerSoc playerSoc;
    private static GamePlayController gamePlayController;


    public static void setWindow(Stage stage) {
        window = stage;
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setPopUpWindow(Stage stage) {
        popUpWindow = stage;
    }

    public static Stage getPopUpWindow() {
        return popUpWindow;
    }

    public static void setPlayerSoc() {
        playerSoc = new PlayerSoc();
    }

    public static PlayerSoc getPlayerSoc() {
        // connect to server socket
        if (playerSoc.socket.isClosed())
            setPlayerSoc();


        return playerSoc;
    }

    public static void setGamePlayController(GamePlayController gamePlayController1) {
        gamePlayController = gamePlayController1;
    }

    public static GamePlayController getGamePlayController() {
        return gamePlayController;
    }
}
