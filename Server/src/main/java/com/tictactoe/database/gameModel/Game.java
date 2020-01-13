/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.database.gameModel;

import com.tictactoe.database.playerModel.Player;
import org.json.simple.JSONObject;

import java.sql.Timestamp;


public class Game {
    private int id;
    private Player from_player;
    private Player to_player;
    private Player winner;
    private Timestamp created_at;
    private GameStatus status;
    private String board;

    public Game() {
    }

    public Game(Player firstPlayer, Player secondPlayer, String b) {
        from_player = firstPlayer;
        to_player = secondPlayer;
        board = b;
        winner = null;
        created_at = new Timestamp(System.currentTimeMillis());
        status = GameStatus.REQUEST;
    }

    Game(int id,Player f_player, Player t_player, Player w_player, Timestamp timestamp, String s, String b) {
        this.id=id;
        from_player = f_player;
        to_player = t_player;
        winner = w_player;
        created_at = timestamp;
        status = GameStatus.valueOf(s);
        board = b;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFromPlayer(Player p) {
        from_player = p;
    }

    public void setToPlayer(Player p) {
        to_player = p;
    }

    public void setWinnerPlayer(Player p) {
        winner = p;
    }

    public void setBoard(JSONObject cells) {

        board = cells.toJSONString();
    }

    public void setGameStatus(GameStatus g) {
        status = g;
    }

    public void setCreatedAt() {
        created_at = new Timestamp(System.currentTimeMillis());
    }

    public Player getFromPlayer() {
        return from_player;
    }

    public Player getToPlayer() {
        return to_player;
    }

    public Player getWinnerPlayer() {
        return winner;
    }

    public String getBoard() {
//        JSONParser jp = new JSONParser();
//        JSONObject bo = new JSONObject();
//        try {
//            bo = (JSONObject) jp.parse(board);
//        } catch (ParseException ex) {
//            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return board;
    }

    public Timestamp getCreatedAt() {
        return created_at;
    }

    public GameStatus getGameStatus() {
        return status;
    }

}
