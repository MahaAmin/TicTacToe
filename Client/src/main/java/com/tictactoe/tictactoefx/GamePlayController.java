package com.tictactoe.tictactoefx;

import actions.App;
import actions.GameConfig;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

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
import playerModel.Player;

public class GamePlayController implements Initializable {

    @FXML
    private JFXNodesList nodeList = new JFXNodesList();
    @FXML
    private JFXTextArea messageTA;
    @FXML
    private JFXTextField messageTF;

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

    private final String colorX = "-fx-text-fill: #3989d4; ";
    private final String colorO = "-fx-text-fill: #3abcd4; ";

    @FXML
    Circle playerXCircle, playerOCircle;

    // int to choose which mode to play in (PCMode(1) / TwoPlayersMode(2))  
    private int mode = GameConfig.getMode();

    // int to choose pc difficult level (Easy(1), Medium(2), Hard(3))
    private int level = GameConfig.getPcLevel();
    // ------------------------------------------------------------------ //
    public String currPlayerMark;

    int playerXScore;
    int playerOScore;

    public boolean gameOverFlag;

    // board
    ArrayList<JFXButton> xoButtonList = new ArrayList<>();

    // text on each button
    public ArrayList<String> xoTextOnButtonsList = new ArrayList<>();

    //Player X and Player Y GUI Top and Bottom Sections
    @FXML
    private Label playerXLabel, playerOLabel;//Set player X and player Y Names
    @FXML
    private Label setPlayerXScore, setPlayerOScore;

    @FXML
    private void xoBTN1Clicked(ActionEvent event) {

        buttonHandling(0);
    }

    @FXML
    private void xoBTN2Clicked(ActionEvent event) {
        buttonHandling(1);
    }

    @FXML
    private void xoBTN3Clicked(ActionEvent event) {
        buttonHandling(2);
    }

    @FXML
    private void xoBTN4Clicked(ActionEvent event) {
        buttonHandling(3);

    }

    @FXML
    private void xoBTN5Clicked(ActionEvent event) {
        buttonHandling(4);
    }

    @FXML
    private void xoBTN6Clicked(ActionEvent event) {
        buttonHandling(5);
    }

    @FXML
    private void xoBTN7Clicked(ActionEvent event) {
        buttonHandling(6);
    }

    @FXML
    private void xoBTN8Clicked(ActionEvent event) {
        buttonHandling(7);
    }

    @FXML
    private void xoBTN9Clicked(ActionEvent event) {
        buttonHandling(8);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Mode: " + mode + ", Level: " + level);

        gameOverFlag = false;

        xoButtonList.add(xoBTN1);
        xoButtonList.add(xoBTN2);
        xoButtonList.add(xoBTN3);
        xoButtonList.add(xoBTN4);
        xoButtonList.add(xoBTN5);
        xoButtonList.add(xoBTN6);
        xoButtonList.add(xoBTN7);
        xoButtonList.add(xoBTN8);
        xoButtonList.add(xoBTN9);

        if (!GameConfig.getXOList().isEmpty()) {
            xoTextOnButtonsList = GameConfig.getXOList();
        } else {
            for (int i = 0; i < xoButtonList.size(); i++) {
                xoTextOnButtonsList.add(" ");
            }
        }
        Player current_player = App.getPlayerSoc().getPlayer();
        if (mode == 1) // PCMode
        {
            // human starts game with label X
            currPlayerMark = "X";
            playerXScore = current_player.getPlayerScore();
            playerOScore = 0;
            //Change the X & O Player names.
            playerXLabel.setText(current_player.getPlayerName());
            playerOLabel.setText("PC O");

        } else if (mode == 2) // TwoPlayersMode
        {
            if (current_player.getPlayerName().equals(GameConfig.getPlayerX())) {
                currPlayerMark = "X";
            } else {
                currPlayerMark = "O";
            }

            //Change the X & O Player names.
            playerXLabel.setText(GameConfig.getPlayerX());
            playerOLabel.setText(GameConfig.getPlayerO());
            playerXScore = GameConfig.getPlayerXScore();
            playerOScore = GameConfig.getPlayerOScore();
            playerXCircle.setStyle("-fx-stroke: #F06585; ");
        }

        //Change Their score
        setPlayerXScore.setText(Integer.toString(playerXScore));
        setPlayerOScore.setText(Integer.toString(playerOScore));

        printBoard();
        // save game controller to access it from anywhere
        App.setGamePlayController(this);

    }

    private void buttonHandling(int buttonNumber) {
        placeMark(buttonNumber, currPlayerMark);
        if (checkForWin() != null) {
            gameOverFlag = true;
            announceGameResult();
        }
        if (mode == 1 && !gameOverFlag) {
            pcTurn();
        }
    }

    public void printBoard() {

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
        }

        return winner;

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

        whichTurn();

        for (int i = 0; i < xoTextOnButtonsList.size(); i++) {
            xoTextOnButtonsList.set(i, jsonObject.get("cell" + i).toString());
        }
        printBoard();
    }

    private void whichTurn() {
        String OColor = "#54dfc4";
        String XColor = "#54dfc4";
        System.out.println(currPlayerMark);
        if (GameConfig.getTurn()) {
            if (currPlayerMark.equals("X")) {
                XColor = "#F06585";
                System.out.println("1");

            } else {
                OColor = "#F06585";
                System.out.println("2");
            }
        } else {
            if (currPlayerMark.equals("X")) {
                OColor = "#F06585";
                System.out.println("3");
            } else {
                XColor = "#F06585";
                System.out.println("4");
            }
        }

        playerXCircle.setStyle("-fx-stroke: " + XColor + "; ");
        playerOCircle.setStyle("-fx-stroke: " + OColor + "; ");
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
                    PlayerHandler.updateFriendBoard(xoTextOnButtonsList);
                }

            }

        } else {
            System.out.println("can not place mark");
        }
    }

    private void removeCell(int index) {
        xoTextOnButtonsList.set(index, " ");
    }

    private void updateScore(int level) {
        switch (level) {
            case 1:
                PlayerHandler.updateScore(5);
                break;
            case 2:
                PlayerHandler.updateScore(15);
                break;
            case 3:
                PlayerHandler.updateScore(25);
                break;
        }
    }

    public void announceGameResult() {
        if (mode == 1) {
            if (checkForWin().equalsIgnoreCase("X")) {
                System.out.println("Player X Wins!");
                updateScore(level);
                try {
                    GameConfig.setWinner("X");
                    SwitchTo.WinnerPopupScene();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (checkForWin().equalsIgnoreCase("O")) {
//            playerOScore++;
//            setPlayerOScore.setText(Integer.toString(playerOScore));
                System.out.println("Player O Wins!");
                try {
                    GameConfig.setWinner("O");
                    SwitchTo.WinnerPopupScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (isBoardFull() && checkForWin().equalsIgnoreCase("Tie")) {
                System.out.println("It is a tie!");
                try {
                    GameConfig.setWinner("Tie");
                    SwitchTo.WinnerPopupScene();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            if (GameConfig.getTurn()) {
                if (checkForWin().equalsIgnoreCase("X") || checkForWin().equalsIgnoreCase("O")) {
                    PlayerHandler.announceGameResult(App.getPlayerSoc().getPlayer().getID());
                } else if (isBoardFull() && checkForWin().equalsIgnoreCase("Tie")) {
                    PlayerHandler.announceGameResult(0);
                }
            }
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

        for (int i = 0; i < xoButtonList.size(); i++) {
            if (xoTextOnButtonsList.get(i).equalsIgnoreCase("X")) {
                xoButtonList.get(i).setStyle(colorX);
            } else {
                xoButtonList.get(i).setStyle(colorO);
            }
        }
    }

    private void pcTurn() {
        changePlayer();
        switch (level) {
            case 1:
                finalizeTurn(pcTurnRandom());
                break;
            case 2:
                finalizeTurn(pcTurnMedium());
                break;
            case 3:
                finalizeTurn(pcTurnMinimax());
                break;
        }
    }

    private void finalizeTurn(int bestMove) {
        placeMark(bestMove, currPlayerMark);
        if (checkForWin() != null) {
            gameOverFlag = true;
            announceGameResult();
        }
        changePlayer();
    }

    private int pcTurnMedium() {
        int bestCell;
        // If AI can win --> AI wins:
        if (canWin("O") != -1) {
            bestCell = canWin("O");
        } // else if human can win --> block him
        else if (canWin("X") != -1) {
            bestCell = canWin("X");
        } // else --> return random available cell
        else {
            bestCell = getRandomAvailableCell();
        }

        // return best cell for ai(medium) move
        return bestCell;
    }

    private int canWin(String mark) {
        int cell;

        // check rows
        for (int i = 0; i < 9; i += 3) {
            cell = checkForThirdEmptyCell(mark, xoTextOnButtonsList.get(i), xoTextOnButtonsList.get(i + 1), xoTextOnButtonsList.get(i + 2));
            switch (cell) {
                case 1:
                    return i;
                case 2:
                    return i + 1;
                case 3:
                    return i + 2;
            }
        }

        // check cols
        for (int i = 0; i < 3; i++) {
            cell = checkForThirdEmptyCell(mark, xoTextOnButtonsList.get(i), xoTextOnButtonsList.get(i + 3), xoTextOnButtonsList.get(i + 6));
            switch (cell) {
                case 1:
                    return i;
                case 2:
                    return i + 3;
                case 3:
                    return i + 6;
            }
        }

        // check 1st diagonal
        cell = checkForThirdEmptyCell(mark, xoTextOnButtonsList.get(0), xoTextOnButtonsList.get(4), xoTextOnButtonsList.get(8));
        switch (cell) {
            case 1:
                return 0;
            case 2:
                return 4;
            case 3:
                return 8;
        }

        // check 2nd diagonal
        cell = checkForThirdEmptyCell(mark, xoTextOnButtonsList.get(2), xoTextOnButtonsList.get(4), xoTextOnButtonsList.get(6));
        switch (cell) {
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 6;
        }

        return -1;

    }

    private int checkForThirdEmptyCell(String mark, String s1, String s2, String s3) {
        if (s1.equals(mark) && s1.equals(s2) && s3.equals(" ")) {
            return 3;
        } else if (s1.equals(mark) && s1.equalsIgnoreCase(s3) && s2.equals(" ")) {
            return 2;
        } else if (s2.equals(mark) && s2.equalsIgnoreCase(s3) && s1.equals(" ")) {
            return 1;
        } else {
            return 0;
        }
    }

    private int pcTurnMinimax() {
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
        return move;
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

    private int getRandomAvailableCell() {
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
        return availableCells.get(rand);
    }

    public int pcTurnRandom() {
        return getRandomAvailableCell();
    }

    @FXML
    private void backButtonClicked(ActionEvent event) throws IOException {
        // update game status to fail
        PlayerHandler.updateGameStatus("FAIL");
        SwitchTo.dashboardScene(event);
    }

    @FXML
    private void saveButtonClicked(ActionEvent event) {
        PlayerHandler.saveGameRequest(xoTextOnButtonsList);
    }

    @FXML
    private void messageTFaction(ActionEvent event) {
        //Get the typed text.
        String getMessage = messageTF.getText();
        //Clear the screen.
        messageTF.setText("");

        messageTA.appendText(getMessage);
        messageTA.appendText("\n");
    }

}
