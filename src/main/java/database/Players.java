/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author halim
 */
public class Players {
    private int id; // autoGenerate in database
    private String name; 
    private String password; //it's a VARCHAR in database with size (32)
    private String email;
    private int status; // it's a ENUM in database which contain offline(0), online(1), busy(2)
    private int score;
    
    public Players(String n, String p, String e){
        name = n;
        password = p;
        email = e;
        status = 0; // offline as initial 
        score = 0; // as initial value
    }
    
    // setDATA for player
    public void setPlayerName(String n){
        name = n;
    }
    public void setPlayerPassword(String p){
        password = p;
    }
    public void setPlayerEmail(String e){
        email = e;
    }
    public void setPlayerStatus(int s){
        status = s;
    }        
    public void setPlayerScore(int s){
        score = s;
    }
    
    // getDATA for player
    public int getID(){
        return id;
    }
    public String getPlayerName(){
        return name;
    }
    public String getPlayerPassword(){
        return password;
    }
    public String getPlayerEmail(){
        return email;
    }
    public int getPlayerStatus(){
        return status;
    }        
    public int setPlayerScore(){
        return score;
    }        
}
