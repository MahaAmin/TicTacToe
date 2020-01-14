/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.database.playerModel;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author halim
 */
public class Player {
    private int id; // autoGenerate in database
    private SimpleStringProperty name;
    private String uname;
    private String password; //it's a VARCHAR in database with size (32)
    private String email;
    private int status; // it's a ENUM in database which contain offline(0), online(1), busy(2)
    private int score;
    private String avatar; // optional
    private JFXButton btn=new JFXButton();

    public String getName() {
        return name.get();
    }
    public void setName(String n){
        name.set(n);
    }
    public String getScore(){
        return Integer.toString(score);
    }
    public JFXButton getStatus(){
        if(status==0){
            btn.setStyle("-fx-background-color: #F8327E;");
            btn.setText("Offline");
            return btn;
        }
        else if (status==1){
            btn.setStyle("-fx-background-color: #54dfc4;");
            btn.setText("Online");
            return btn;
        }
        btn.setText("Busy");
        return btn;
    }
    // constactor used for GUI only
    public Player(String n, String p, String e) {
        uname = n;
        name = new SimpleStringProperty(n);
        password = p;
        email = e;
        status = 0; // offline as initial 
        score = 0; // as initial value
    }

    // constactors used for retrieve in databaseManager only
    public Player(int i, String n,  String e, int s, String a, int sc) {
        id = i;
        uname = n;
        name = new SimpleStringProperty(n);
        email = e;
        status = s;
        score = sc;
        avatar = a;
    }

    public Player() {
    }

    // setDATA for player
    public void setPlayerName(String n) {
        uname = n;
    }

    public void setPlayerPassword(String p) {
        password = p;
    }

    public void setPlayerEmail(String e) {
        email = e;
    }

    public void setPlayerStatus(int s) {
        status = s;
    }

    public void setPlayerScore(int s) {
        score = s;
    }

    public void setPlayerAvatar(String s) {
        avatar = s;
    }

    // getDATA for player
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return uname;
    }

    public String getPlayerPassword() {
        return password;
    }

    public String getPlayerEmail() {
        return email;
    }

    public int getPlayerStatus() {
        return status;
    }

    public int getPlayerScore() {
        return score;
    }

    public String getPlayerAvatar() {
        return avatar;
    }
}
