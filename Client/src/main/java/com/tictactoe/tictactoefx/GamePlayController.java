package com.tictactoe.tictactoefx;

import actions.App;
import actions.GameConfig;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import org.json.simple.JSONObject;
import player.PlayerHandler;

public class GamePlayController implements Initializable {

    @FXML
    private JFXButton xoBTN1, xoBTN2, xoBTN3;
    @FXML
    private JFXButton xoBTN4, xoBTN5, xoBTN6;
    @FXML
    private JFXButton xoBTN7, xoBTN8, xoBTN9;

    //Player regions (The background color for the score section).
    @FXML
    private Region playerORegion;
    @FXML
    private Region playerXRegion;

    private String xoTXT1, xoTXT2, xoTXT3;
    private String xoTXT4, xoTXT5, xoTXT6;
    private String xoTXT7, xoTXT8, xoTXT9;

    private final String colorX = "-fx-text-fill: #3989d4; ";
    private final String colorO = "-fx-text-fill: #3abcd4; ";

    @FXML
    Circle playerXCircle, playerOCircle;

    // int to choose which mode to play in (PCMode(1) / TwoPlayersMode(2))  
    private int mode = GameConfig.getMode();

    // int to choose pc difficult level (Easy(1), Medium(2), Hard(3))
    private int level = GameConfig.getPcLevel();
    // ------------------------------------------------------------------ //
    private String currPlayerMark;

    int playerXScore;
    int playerOScore;

    // board
    ArrayList<JFXButton> xoButtonList = new ArrayList<>();

    // text on each button
    ArrayList<String> xoTextOnButtonsList = new ArrayList<>();

    //Player X and Player Y GUI Top and Bottom Sections
    @FXML
    private Label playerXLabel, playerOLabel;//Set player X and player Y Names
    @FXML
    private Label setPlayerXScore, setPlayerOScore;

    @FXML
    private void xoBTN1Clicked(ActionEvent event) {

        placeMark(0, currPlayerMark);
        pcTurn();
        if (checkForWin() != null) {
            announceGameResult();
        }
    }

    @FXML
    private void xoBTN2Clicked(ActionEvent event) {
        placeMark(1, currPlayerMark);

        pcTurn();
        if (checkForWin() != null) {
            announceGameResult();
        }
    }

    @FXML
    private void xoBTN3Clicked(ActionEvent event) {
        placeMark(2, currPlayerMark);

        pcTurn();
        if (checkForWin() != null) {
            announceGameResult();
        }
    }

    @FXML
    private void xoBTN4Clicked(ActionEvent event) {
        placeMark(3, currPlayerMark);

        pcTurn();

        if (checkForWin() != null) {
            announceGameResult();
        }
    }

    @FXML
    private void xoBTN5Clicked(ActionEvent event) {
        placeMark(4, currPlayerMark);

        pcTurn();
        if (checkForWin() != null) {
            announceGameResult();
        }
    }

    @FXML
    private void xoBTN6Clicked(ActionEvent event) {
        placeMark(5, currPlayerMark);

        pcTurn();
        if (checkForWin() != null) {
            announceGameResult();
        }
    }

    @FXML
    private void xoBTN7Clicked(ActionEvent event) {
        placeMark(6, currPlayerMark);

        pcTurn();
        if (checkForWin() != null) {
            announceGameResult();
        }
    }

    @FXML
    private void xoBTN8Clicked(ActionEvent event) {
        placeMark(7, currPlayerMark);

        pcTurn();
        if (checkForWin() != null) {
            announceGameResult();
        }
    }

    @FXML
    private void xoBTN9Clicked(ActionEvent event) {
        placeMark(8, currPlayerMark);

        pcTurn();
        if (checkForWin() != null) {
            announceGameResult();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("Mode: " + mode + ", Level: " + level);
        xoButtonList.add(xoBTN1);
        xoButtonList.add(xoBTN2);
        xoButtonList.add(xoBTN3);
        xoButtonList.add(xoBTN4);
        xoButtonList.add(xoBTN5);
        xoButtonList.add(xoBTN6);
        xoButtonList.add(xoBTN7);
        xoButtonList.add(xoBTN8);
        xoButtonList.add(xoBTN9);

        xoTextOnButtonsList.add(xoTXT1);
        xoTextOnButtonsList.add(xoTXT2);
        xoTextOnButtonsList.add(xoTXT3);
        xoTextOnButtonsList.add(xoTXT4);
        xoTextOnButtonsList.add(xoTXT5);
        xoTextOnButtonsList.add(xoTXT6);
        xoTextOnButtonsList.add(xoTXT7);
        xoTextOnButtonsList.add(xoTXT8);
        xoTextOnButtonsList.add(xoTXT9);

        for (int i = 0; i < xoTextOnButtonsList.size(); i++) {
            xoTextOnButtonsList.set(i, " ");
        }

        if (mode == 1) // PCMode
        {
            // human starts game with label X
            currPlayerMark = "X";
            //Change the X & O Player names.
            playerXLabel.setText(App.getPlayerSoc().getPlayer().getPlayerName());
            playerOLabel.setText("PC O");

        } else if (mode == 2) // TwoPlayersMode
        {
            if (App.getPlayerSoc().getPlayer().getPlayerName().equals(GameConfig.getPlayerX())) {
                currPlayerMark = "X";
            } else {
                currPlayerMark = "O";
            }

            //Change the X & O Player names.
            playerXLabel.setText(GameConfig.getPlayerX());
            playerOLabel.setText(GameConfig.getPlayerO());
            playerXCircle.setStyle("-fx-stroke: #F06585; ");
        }

        playerXScore = 0;
        playerOScore = 0;

        //Change Their score
        setPlayerXScore.setText(Integer.toString(playerXScore));
        setPlayerOScore.setText(Integer.toString(playerOScore));

        printBoard();
        // save game controller to access it from anywhere
        App.setGamePlayController(this);

    }

    private void printBoard() {

        setPlayerXScore.setText(Integer.toString(playerXScore));
        setPlayerOScore.setText(Integer.toString(playerOScore));
        for (int i = 0; i < xoButtonList.size(); i++) {
            // set label color
            setColorToTextOnButton();

            // print the button label
            xoButtonList.get(i).setText(xoTextOnButtonsList.get(i));
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < xoTextOnButtonsList.size(); i++) {
            if (xoTextOnButtonsList.get(i).equals(" ")) {
                return false;
            }
        }
        return true;
    }

    private String checkForWin() {
        String winner = null;
        // check Rows For win
        // 1st row
        if (checkTripleEquality(xoTextOnButtonsList.get(0), xoTextOnButtonsList.get(1), xoTextOnButtonsList.get(2))) {
            winner = xoTextOnButtonsList.get(0);
        }
        // 2nd row
        if (checkTripleEquality(xoTextOnButtonsList.get(3), xoTextOnButtonsList.get(4), xoTextOnButtonsList.get(5))) {
            winner = xoTextOnButtonsList.get(3);
        }
        // 3rd row
        if (checkTripleEquality(xoTextOnButtonsList.get(6), xoTextOnButtonsList.get(7), xoTextOnButtonsList.get(8))) {
            winner = xoTextOnButtonsList.get(6);
        }

        // check Cols for win
        // 1st col
        if (checkTripleEquality(xoTextOnButtonsList.get(0), xoTextOnButtonsList.get(3), xoTextOnButtonsList.get(6))) {
            winner = xoTextOnButtonsList.get(0);
        }
        // 2nd col
        if (checkTripleEquality(xoTextOnButtonsList.get(1), xoTextOnButtonsList.get(4), xoTextOnButtonsList.get(7))) {
            winner = xoTextOnButtonsList.get(1);
        }
        // 3rd row
        if (checkTripleEquality(xoTextOnButtonsList.get(2), xoTextOnButtonsList.get(5), xoTextOnButtonsList.get(8))) {
            winner = xoTextOnButtonsList.get(2);
        }

        // check Diagonals for win
        // 1st diagonal
        if (checkTripleEquality(xoTextOnButtonsList.get(0), xoTextOnButtonsList.get(4), xoTextOnButtonsList.get(8))) {
            winner = xoTextOnButtonsList.get(0);
        }
        // 2nd diagonal
        if (checkTripleEquality(xoTextOnButtonsList.get(2), xoTextOnButtonsList.get(4), xoTextOnButtonsList.get(6))) {
            winner = xoTextOnButtonsList.get(2);
        }

        if (isBoardFull() && winner == null) {
            return "Tie";
        } else {
            return winner;
        }

    }

    private boolean checkTripleEquality(String txt1, String txt2, String txt3) {
        return ((!txt1.equalsIgnoreCase(" ")) && (txt1.equalsIgnoreCase(txt2)) && (txt2.equalsIgnoreCase(txt3)));
    }

    private void changePlayer() {
        if (currPlayerMark.equalsIgnoreCase("x")) {
            currPlayerMark = "O";
        } else if (currPlayerMark.equalsIgnoreCase("o")) {
            currPlayerMark = "X";
        }
    }

    public void invokePrintBoard(JSONObject jsonObject) {
        playerXScore = Integer.parseInt(jsonObject.get("playerXScore").toString());
        playerOScore = Integer.parseInt(jsonObject.get("playerOScore").toString());

        String OColor = "#54dfc4";
        String XColor = "#54dfc4";

        if (GameConfig.getTurn()) {
            if (currPlayerMark.equals("X")) {
                XColor = "#F06585";

            } else {
                OColor = "#F06585";
            }
        } else {
            if (currPlayerMark.equals("X")) {
                OColor = "#F06585";
            } else {
                XColor = "#F06585";
            }
        }

        playerXCircle.setStyle("-fx-stroke: " + XColor + "; ");
        playerOCircle.setStyle("-fx-stroke: " + OColor + "; ");

        for (int i = 0; i < xoTextOnButtonsList.size(); i++) {
            xoTextOnButtonsList.set(i, jsonObject.get("cell" + i).toString());
        }
        printBoard();
    }

    private boolean isCellAvailable(int index) {
        if ((index >= 0 && index < 9) && (xoTextOnButtonsList.get(index).equals(" "))) {
            return true;
        }
        return false;
    }

    private void placeMark(int index, String text) {
        if (isCellAvailable(index)) {
            xoTextOnButtonsList.set(index, text);
            if (mode == 1) {
                printBoard();
            } else {
                // if it my turn send updates to the other player
                if (GameConfig.getTurn()) {
                    // update board in the friend side also
                    PlayerHandler.updateFriendBoard(xoTextOnButtonsList, playerXScore, playerOScore);
                }

            }

        } else {
            System.out.println("can not place mark");
        }
    }

    private void removeCell(int index) {
        xoTextOnButtonsList.set(index, " ");
    }

    public void announceGameResult() {
        if (checkForWin().equalsIgnoreCase("X")) {
            playerXScore++;
            setPlayerXScore.setText(Integer.toString(playerXScore));
            System.out.println("Player X Wins!");
            try{
                SwitchTo.WinnerPopupScene();
            }catch(IOException e){
                e.printStackTrace();
            }
            
            
        } else if (checkForWin().equalsIgnoreCase("O")) {
            playerOScore++;
            setPlayerOScore.setText(Integer.toString(playerOScore));
            System.out.println("Player O Wins!");
        } else if (isBoardFull() && !checkForWin().equalsIgnoreCase("Tie")) {
            System.out.println("It is a tie!");
        }
    }

    private Vector<Integer> getAvailableCells() {
        Vector<Integer> available = new Vector();
        for (int i = 0; i < 9; i++) {
            if (isCellAvailable(i)) {
                available.add(i);
            }
        }
        return available;
    }

    private void setColorToTextOnButton() {
        if (xoTextOnButtonsList.get(0).equalsIgnoreCase("X")) {
            xoBTN1.setStyle(colorX);
        } else {
            xoBTN1.setStyle(colorO);
        }

        if (xoTextOnButtonsList.get(1).equalsIgnoreCase("X")) {
            xoBTN2.setStyle(colorX);
        } else {
            xoBTN2.setStyle(colorO);
        }

        if (xoTextOnButtonsList.get(2).equalsIgnoreCase("X")) {
            xoBTN3.setStyle(colorX);
        } else {
            xoBTN3.setStyle(colorO);
        }

        if (xoTextOnButtonsList.get(3).equalsIgnoreCase("X")) {
            xoBTN4.setStyle(colorX);
        } else {
            xoBTN4.setStyle(colorO);
        }

        if (xoTextOnButtonsList.get(4).equalsIgnoreCase("X")) {
            xoBTN5.setStyle(colorX);
        } else {
            xoBTN5.setStyle(colorO);
        }

        if (xoTextOnButtonsList.get(5).equalsIgnoreCase("X")) {
            xoBTN6.setStyle(colorX);
        } else {
            xoBTN6.setStyle(colorO);
        }

        if (xoTextOnButtonsList.get(6).equalsIgnoreCase("X")) {
            xoBTN7.setStyle(colorX);
        } else {
            xoBTN7.setStyle(colorO);
        }

        if (xoTextOnButtonsList.get(7).equalsIgnoreCase("X")) {
            xoBTN8.setStyle(colorX);
        } else {
            xoBTN8.setStyle(colorO);
        }

        if (xoTextOnButtonsList.get(8).equalsIgnoreCase("X")) {
            xoBTN9.setStyle(colorX);
        } else {
            xoBTN9.setStyle(colorO);
        }

    }

    private void pcTurn() {
        if (mode == 1) // PCMode
        {
            if (checkForWin() != null) {
                announceGameResult();
            } else {
                changePlayer();
                switch (level) {
                    case 1:
                        pcTurnRandom();
                        break;
                    case 2:
                        //pcTurnMedium();
                        break;
                    case 3:
                        pcTurnMinimax();
                        break;
                }
            }
        }
    }

    private void pcTurnMinimax() {
        int bestScore = Integer.MIN_VALUE;
        int move = 100;

        for (int i = 0; i < 9; i++) {
            if (isCellAvailable(i)) {
                placeMark(i, "O");
                int score = minimax(xoTextOnButtonsList, 0, false);
                removeCell(i);
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        placeMark(move, currPlayerMark);
        if (checkForWin() != null) {
            announceGameResult();
        }
        changePlayer();
    }

    public int minimax(ArrayList<String> board, int depth, boolean isMaximizing) {
        /*
            Score function:
            X-win = -10;
            O-win = 10;
            tie = 0;
         */

        String result = checkForWin();
        // base-case
        if (result != null) {
            if (result.equalsIgnoreCase("X")) {
                return -10;
            } else if (result.equalsIgnoreCase("O")) {
                return 10;
            } else if (result.equalsIgnoreCase("Tie")) {
                return 0;
            }
        }

        // recursive part
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (isCellAvailable(i)) {
                    placeMark(i, "O");
                    int score = minimax(board, depth + 1, false);
                    removeCell(i);
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (isCellAvailable(i)) {
                    placeMark(i, "X");
                    int score = minimax(board, depth + 1, true);
                    removeCell(i);
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    public void pcTurnRandom() {
        // vector that carries indeces of current empty cells on the board
        Vector<Integer> availableCells = getAvailableCells();

        // How many empty cells 
        int availableCellsLength = availableCells.size();

        // Random number to choose index from availableCells vector
        int rand = 0;
        Random random = new Random();
        while (true) {
            rand = random.nextInt(availableCellsLength);
            if (rand != 0) {
                break;
            }
        }

        // cell Where to play next turn
        int nextTurn = availableCells.get(rand);
        placeMark(nextTurn, currPlayerMark);
        changePlayer();
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        SwitchTo.dashboardScene(event);
    }

    @FXML
    private void saveButtonClicked(ActionEvent event) {
        PlayerHandler.saveGameRequest(xoTextOnButtonsList);
    }

}
