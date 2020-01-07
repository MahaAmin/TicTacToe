package actions;

import javafx.stage.Stage;

public class App {

    private static Stage window;

    public static void setStage(Stage stage) {
        window = stage;
    }

    public static Stage getWindow() {
        return window;
    }
}
