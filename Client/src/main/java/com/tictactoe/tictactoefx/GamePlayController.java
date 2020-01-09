package com.tictactoe.tictactoefx;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

public class GamePlayController implements Initializable {

    @FXML
    private JFXButton xoBTN1, xoBTN2, xoBTN3;
    @FXML
    private JFXButton xoBTN4, xoBTN5, xoBTN6;
    @FXML
    private JFXButton xoBTN7, xoBTN8, xoBTN9;
    
    //Player Circles (The stroke color for the circle score section).
    @FXML Circle playerXCircle, playerOCircle;

    private String xoTXT1, xoTXT2, xoTXT3;
    private String xoTXT4, xoTXT5, xoTXT6;
    private String xoTXT7, xoTXT8, xoTXT9;

    private String currPlayerMark;

    int playerXScore;
    int playerOScore;

    // board
    ArrayList<JFXButton> xoButtonList = new ArrayList<>();

    // text on each button
    ArrayList<String> xoTextOnButtonsList = new ArrayList<>();
    
    @FXML private Label playerXLabel, playerOLabel;//Set player X and player Y Names
    @FXML private Label setPlayerXScore, setPlayerOScore; //Change the score label

    @FXML
    private void xoBTN1Clicked(ActionEvent event) {
        playerXCircle.setStyle("-fx-stroke: #F06585; ");
         
        placeMark(0);
        printBoard();
        if (checkForWin() || isBoardFull()) {
            announceGameResult();
            changePlayer();
        } 
        else 
        {
            changePlayer();
        }
    }

    @FXML
    private void xoBTN2Clicked(ActionEvent event) {
        playerOCircle.setStyle("-fx-stroke: #F06585; ");
        placeMark(1);
        printBoard();
        if (checkForWin() || isBoardFull()) {
            announceGameResult();
        } else {
            changePlayer();
        }
    }

    @FXML
    private void xoBTN3Clicked(ActionEvent event) {
        placeMark(2);
        printBoard();
        if (checkForWin() || isBoardFull()) {
            announceGameResult();
        } else {
            changePlayer();
        }
    }

    @FXML
    private void xoBTN4Clicked(ActionEvent event) {
        placeMark(3);
        printBoard();
        if (checkForWin() || isBoardFull()) {
            announceGameResult();
        } else {
            changePlayer();
        }
    }

    @FXML
    private void xoBTN5Clicked(ActionEvent event) {
        placeMark(4);
        printBoard();
        if (checkForWin() || isBoardFull()) {
            announceGameResult();
        } else {
            changePlayer();
        }
    }

    @FXML
    private void xoBTN6Clicked(ActionEvent event) {
        placeMark(5);
        printBoard();
        if (checkForWin() || isBoardFull()) {
            announceGameResult();
        } else {
            changePlayer();
        }
    }

    @FXML
    private void xoBTN7Clicked(ActionEvent event) {
        placeMark(6);
        printBoard();
        if (checkForWin() || isBoardFull()) {
            announceGameResult();
        } else {
            changePlayer();
        }
    }

    @FXML
    private void xoBTN8Clicked(ActionEvent event) {
        placeMark(7);
        printBoard();
        if (checkForWin() || isBoardFull()) {
            announceGameResult();
        } else {
            changePlayer();
        }
    }

    @FXML
    private void xoBTN9Clicked(ActionEvent event) {
        placeMark(8);
        printBoard();
        if (checkForWin() || isBoardFull()) {
            announceGameResult();
        } else {
            changePlayer();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Change the X & O Player names.
        playerXLabel.setText("Mod The X");
        playerOLabel.setText("Mod The O");
        
        //Change Their score
        setPlayerXScore.setText("0");
        setPlayerOScore.setText("0");
        
        //Change the background color for any plaer
        

        currPlayerMark = "X";

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

        playerXScore = 0;
        playerOScore = 0;

        for (int i = 0; i < xoTextOnButtonsList.size(); i++) {
            xoTextOnButtonsList.set(i, " ");
        }

        printBoard();

    }

    private String getCurrPlayerMark() {
        return currPlayerMark;
    }

    private void printBoard() {
        for (int i = 0; i < xoButtonList.size(); i++) {
            //Hide the button label
            xoButtonList.get(i).setText(xoTextOnButtonsList.get(i));
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < xoTextOnButtonsList.size(); i++) {
            if (xoTextOnButtonsList.get(i) == " ") {
                return false;
            }
        }
        return true;
    }

    private boolean checkForWin() {
        return (checkRowsForWin() || checkColsForWin() || checkDiagonalsForWin());
    }

    private boolean checkRowsForWin() {
        if (checkTripleEquality(xoTextOnButtonsList.get(0), xoTextOnButtonsList.get(1), xoTextOnButtonsList.get(2))
                || checkTripleEquality(xoTextOnButtonsList.get(3), xoTextOnButtonsList.get(4), xoTextOnButtonsList.get(5))
                || checkTripleEquality(xoTextOnButtonsList.get(6), xoTextOnButtonsList.get(7), xoTextOnButtonsList.get(8))) {
            return true;
        }

        return false;
    }

    private boolean checkColsForWin() {
        if (checkTripleEquality(xoTextOnButtonsList.get(0), xoTextOnButtonsList.get(3), xoTextOnButtonsList.get(6))
                || checkTripleEquality(xoTextOnButtonsList.get(1), xoTextOnButtonsList.get(4), xoTextOnButtonsList.get(7))
                || checkTripleEquality(xoTextOnButtonsList.get(2), xoTextOnButtonsList.get(5), xoTextOnButtonsList.get(8))) {
            return true;
        }

        return false;
    }

    private boolean checkDiagonalsForWin() {
        return (checkTripleEquality(xoTextOnButtonsList.get(0), xoTextOnButtonsList.get(4), xoTextOnButtonsList.get(8))
                || checkTripleEquality(xoTextOnButtonsList.get(2), xoTextOnButtonsList.get(4), xoTextOnButtonsList.get(6)));
    }

    private boolean checkTripleEquality(String txt1, String txt2, String txt3) {
        return ((txt1 != " ") && (txt1 == txt2) && (txt2 == txt3));
    }

    private void changePlayer() {
        if (currPlayerMark == "x" || currPlayerMark == "X") {
            currPlayerMark = "O";
        } else if (currPlayerMark == "o" || currPlayerMark == "O") {
            currPlayerMark = "X";
        }
    }

    private void placeMark(int index) {
        if (index >= 0 && index < 9) {
            if (xoTextOnButtonsList.get(index) == " ") {
                xoTextOnButtonsList.set(index, currPlayerMark);
            }
        } else {
            System.out.println("can not place mark");
        }
    }

    public void announceGameResult() {
        if (checkForWin() && (currPlayerMark == "X" || currPlayerMark == "x")) {
            playerXScore++;
            System.out.println("Player X Wins!");
        } else if (checkForWin() && (currPlayerMark == "O" || currPlayerMark == "o")) {
            playerOScore++;
            System.out.println("Player O Wins!");
        }
        else if(isBoardFull() && !checkForWin()){
            System.out.println("It is a tie!");
        }
    }
    
    @FXML private void backButtonClicked(ActionEvent event) throws IOException
    {
        SwitchTo.dashboardScene(event);
    }
}