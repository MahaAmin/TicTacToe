package com.tictactoe.tictactoefx;

import actions.App;
import actions.GameConfig;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;
import player.PlayerHandler;

public class WinnerController implements Initializable {

    @FXML
    private JFXButton exitButton;

    @FXML
    private static Label result_txt;

    @FXML
    private void resetButtonClicked(ActionEvent event) {
        System.out.println("Reset was Pressed");

        if (GameConfig.getMode() == 1) {
            GamePlayController game = App.getGamePlayController();
            game.currPlayerMark = "X";
            PlayerHandler.resetGame();
        } else if (GameConfig.getMode() == 2) {
//            PlayerHandler.updateFriendBoard(GameConfig.getXOList());
            PlayerHandler.resetGameRequest();
        }
        App.getPopUpWindow().close();
    }

    @FXML
    private void shareButtonClicked(ActionEvent event) {
        System.out.println("Share was pressed");
    }

    @FXML
    private void exitButtonClicked(ActionEvent event) {
        System.out.println("Exit was pressed");
//        exitButton.getScene().getWindow().hide();
        App.getPopUpWindow().close();
        try {
            SwitchTo.changeTo(App.getWindow(), 2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (GameConfig.getMode() == 2) {
            JSONObject jsonObject = GameConfig.getWinnerPobUpJson();
            int winner_id = Integer.parseInt(jsonObject.get("winner_id").toString());
            if (winner_id != 0) {
                if (winner_id == App.getPlayerSoc().getPlayer().getID()) {
                    result_txt.setText("Congratulations, You've WON !! OMG \n Your new Score is " + App.getPlayerSoc().getPlayer().getPlayerScore());
                } else {
                    result_txt.setText("Oops, You failed.. Good luck second time.");
                }
            } else {
                result_txt.setText("Game finished, It's a Tie.");
            }
        } else if (GameConfig.getMode() == 1) {
            result_txt = new Label();
            if (GameConfig.getWinner().equals("X")) {
                System.out.println(result_txt);
                result_txt.setText("Player X Won!");
            } else if (GameConfig.getWinner().equals("O")) {
                System.out.println(result_txt);
                result_txt.setText("Player O Won!");
            } else if (GameConfig.getWinner().equals("Tie")) {
                System.out.println(result_txt);
                result_txt.setText("IT IS A TIE!");
            }
        }

    }

}
