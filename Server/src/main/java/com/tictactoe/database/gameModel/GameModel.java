/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.database.gameModel;

import com.tictactoe.actions.App;
import com.tictactoe.database.DatabaseManager;
import com.tictactoe.database.playerModel.PlayerModel;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;


public class GameModel {

    static DatabaseManager db = App.getDB();
    private static Map<Integer, Game> games;

    public static void getGames() {
        games = new HashMap<>();
        try {
            Statement stmt = db.connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM games");
            while (resultSet.next()) {

                games.put(resultSet.getInt("id"),gameObject(resultSet));// adding all the players to map
            }

        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Game gameObject(ResultSet resultSet){
        try {
            return new Game(
                    PlayerModel.getPlayer(resultSet.getInt("from_player")),
                    PlayerModel.getPlayer(resultSet.getInt("to_player")),
                    PlayerModel.getPlayer(resultSet.getInt("winner")),
                    resultSet.getTimestamp("created_at"),
                    resultSet.getString("status"),
                    resultSet.getString("board")
            );
        } catch (SQLException ex) {
            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Game();
    }

    public static int createGame(JSONObject game) {
        try {
            PreparedStatement statment = db.connection.prepareStatement("INSERT games SET from_player=?, to_player=?", Statement.RETURN_GENERATED_KEYS);
            statment.setString(1, game.get("from_id").toString());
            statment.setString(2, game.get("to_id").toString());
            int isInsearted = statment.executeUpdate();
            if(isInsearted > 0){
                ResultSet keys = statment.getGeneratedKeys();
                if (keys.next()){
                    int id = keys.getInt(1);
                    return id;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Game getGame(int id) {
        try {
            PreparedStatement statment = db.connection.prepareStatement("SELECT * FROM games WHERE id=?");
            statment.setInt(1, id);
            ResultSet res = statment.executeQuery();
            if (res.next()) {
                return gameObject(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public static Game getGame(int id){
//        Game game = games.get(id);
//        if (game != null) {
//            return game;
//        }
//        return null;
//    }


}
