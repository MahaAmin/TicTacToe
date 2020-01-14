package com.tictactoe.tictactoefx;

import actions.App;
import actions.GameConfig;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import player.PlayerHandler;

public class WinnerController implements Initializable {

    @FXML
    private JFXButton exitButton;

    @FXML
    private void resetButtonClicked(ActionEvent event) {
        System.out.println("Reset was Pressed");
        GamePlayController game = App.getGamePlayController();
        GameConfig.resetBoard(game.xoTextOnButtonsList);
        if (GameConfig.getMode() == 1) {
            game.gameOverFlag = false;
            game.printBoard();
        } else if (GameConfig.getMode() == 2) {
            PlayerHandler.updateFriendBoard(GameConfig.getXOList(), 0, 0);
        }
    }

    @FXML
    private void shareButtonClicked(ActionEvent event) {
        System.out.println("Share was pressed");
    }

    @FXML
    private void exitButtonClicked(ActionEvent event) {
        System.out.println("Exit was pressed");
        exitButton.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
