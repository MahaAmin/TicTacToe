package com.tictactoe.tictactoefx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class User   
{
    ImageView avatar;
    String userName;
    String rank;
    String score;
    String status;
   // Image status;

    public User(String userName, String rank, String score) {
        this.userName = userName;
        this.rank = rank;
        this.score = score;
    }
    
    public User(ImageView avatar, String userName, String rank, String score) {
        this.avatar = avatar;
        this.userName = userName;
        this.rank = rank;
        this.score = score;
    }

    //This constructor was used to fill the allUsersTable
    public User(String userName, String rank, String score, String status) {
        this.userName = userName;
        this.rank = rank;
        this.score = score;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    
    


    
}
