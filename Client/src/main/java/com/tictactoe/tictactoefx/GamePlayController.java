package com.tictactoe.tictactoefx;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class GamePlayController implements Initializable {
    @FXML private JFXButton xoBTN1, xoBTN2, xoBTN3;
    @FXML private JFXButton xoBTN4, xoBTN5, xoBTN6;
    @FXML private JFXButton xoBTN7, xoBTN8, xoBTN9;
    ArrayList<JFXButton> xoButtonList = new ArrayList<>();
    

    
    @FXML private void xoBTN1Clicked(ActionEvent event)
    {
        xoBTN1.setText("X");
    }
    
    @FXML private void xoBTN2Clicked(ActionEvent event)
    {
        xoBTN2.setText("O");
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        xoButtonList.add(xoBTN1);
        xoButtonList.add(xoBTN2);
        xoButtonList.add(xoBTN3);
        xoButtonList.add(xoBTN4);
        xoButtonList.add(xoBTN5);
        xoButtonList.add(xoBTN6);
        xoButtonList.add(xoBTN7);
        xoButtonList.add(xoBTN8);
        xoButtonList.add(xoBTN9);

        for(int i = 0; i < xoButtonList.size(); i++)
        {
            //Hide the button label
            xoButtonList.get(i).setText("");
        }
       
    }    
    
}