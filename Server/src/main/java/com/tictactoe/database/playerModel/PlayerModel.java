/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.database.playerModel;

import com.tictactoe.actions.App;
import com.tictactoe.database.DatabaseManager;
import org.json.simple.JSONObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PlayerModel {

    static DatabaseManager db = App.getDB();

    private static Map<Integer, Player> players;


    public static void getPlayers() {
        players = new HashMap<>();
        try {
            Statement stmt = db.connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM players");
            while (resultSet.next()) {
                players.put(resultSet.getInt("id"),playerObiect(resultSet));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Player playerObiect(ResultSet resultSet){
        try {
            return new Player(
                    resultSet.getInt("id"), //return id
                    resultSet.getString("name"), //return name
                    resultSet.getString("email"), // return email
                    resultSet.getInt("status"), // return status
                    resultSet.getString("avatar"), //return avatar
                    resultSet.getInt("score") // return score
            );

        } catch (SQLException ex) {
//            Logger.getLogger(PlayerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Player();
    }

    public static Player getPlayer(String email) {
        try {
            PreparedStatement statment = db.connection.prepareStatement("SELECT * FROM players WHERE email=?");
            statment.setString(1, email);
            ResultSet res = statment.executeQuery();
            if (res.next()) {
                return playerObiect(res);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(PlayerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Player getPlayer(int id){

        Player player = players.get(id);
        if (player != null) {
            return player;
        }
        return null;
    }


    // update score
    public void updateScore(JSONObject player) {
        try {
            PreparedStatement preparedStatement = db.connection.prepareStatement("UPDATE players SET score=? WHERE id=?");
            preparedStatement.setInt(1, Integer.parseInt(player.get("score").toString()));
            preparedStatement.setInt(2, Integer.parseInt(player.get("id").toString()));
            int isUpdated = preparedStatement.executeUpdate();
            if (isUpdated > 0) {
                Player p = players.get("id");
                p.setPlayerScore((int) player.get("score"));
                players.replace(Integer.parseInt(player.get("id").toString()), p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // update status
    public static void updateStatus(JSONObject player) {
        try {
            PreparedStatement preparedStatement = db.connection.prepareStatement("UPDATE players SET status=? WHERE id=?");
            preparedStatement.setString(1, player.get("status").toString());
            preparedStatement.setInt(2, Integer.parseInt(player.get("id").toString()));
            int isUpdated = preparedStatement.executeUpdate();
            if (isUpdated > 0) {
                Player p = players.get(Integer.parseInt(player.get("id").toString()));
                p.setPlayerStatus(Integer.parseInt(player.get("status").toString()));
                players.replace(Integer.parseInt(player.get("id").toString()), p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    // logout
    public void logout(JSONObject player) {
        updateStatus(player); // refer to offline
    }


    // function used to register in GUI
    public static JSONObject createPlayer(JSONObject player) {
        if (getPlayer(player.get("email").toString())!=null){
            try {
                PreparedStatement statment = db.connection.prepareStatement("INSERT players SET name=?, password=?, email=?");
                statment.setString(1, player.get("name").toString());
                statment.setString(2, player.get("password").toString());
                statment.setString(3, player.get("email").toString());
                statment.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return validatePlalyer(player);
    }

    // function used to Login in GUI
    public static JSONObject validatePlalyer(JSONObject player) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", false);
        try {
            PreparedStatement statment = db.connection.prepareStatement("SELECT * FROM players WHERE email=? AND password=?");
            statment.setString(1, player.get("email").toString());
            statment.setString(2, player.get("password").toString());
            ResultSet res = statment.executeQuery();
            System.out.println("done select");
            if (res.next()) {
                player.put("status","1");
                player.put("id",res.getInt("id"));
                updateStatus(player); // refer to online
                jsonObject.put("id", res.getInt("id"));
                jsonObject.put("name", res.getString("name"));
                jsonObject.put("status", true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
