/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.database.gameModel;

import com.tictactoe.database.playerModel.Player;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author halim
 */
public class Game {
    private Player from_player;
    private Player to_player;
    private Player winner;
    private Timestamp time_stamp;
    private GameStatus status;
    private String board;
    
    public Game(){}
    
    public Game(Player firstPlayer, Player secondPlayer, String b){
        from_player = firstPlayer;
        to_player = secondPlayer;
        board = b;
        winner = null;
        time_stamp = new Timestamp(System.currentTimeMillis());
        status = GameStatus.REQUEST;
    }
    
    public void setFromPlayer(Player p){
        from_player = p;
    }
    
    public void setToPlayer(Player p){
        to_player = p;
    }
    
    public void setWinnerPlayer(Player p){
        winner = p;
    }
    
    public void setBoard(JSONObject cells){
        
        board = cells.toJSONString();
    }
    
    public void setGameStatus(GameStatus g){
        status = g;
    }
    
    public void setTimeStamp(){
        time_stamp = new Timestamp(System.currentTimeMillis());
    }
       
    public Player getFromPlayer(){
        return from_player;
    }
    public Player getToPlayer(){
        return to_player;
    }
    
    public Player getWinnerPlayer(){
        return winner;
    }
    
    public JSONObject getBoard(){
        JSONParser jp= new JSONParser();
        JSONObject bo = new JSONObject();
        try {
            bo = (JSONObject)jp.parse(board);
        } catch (ParseException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bo;
    }
    
    public Timestamp getTimeStamp(){
        return time_stamp;
    }
    
    public GameStatus getGameStatus(){
        return status;
    }
    
}
