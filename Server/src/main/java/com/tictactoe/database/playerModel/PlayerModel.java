/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.database.playerModel;

import com.tictactoe.actions.App;
import com.tictactoe.database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PlayerModel {

    static DatabaseManager db = App.getDB();
    public static ObservableList<Player> playerslist;
    private static Map<Integer, Player> players;


    public static void getPlayers() {
        players = new LinkedHashMap<>();
        try {
            Statement stmt = db.connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM players ORDER BY score DESC");
            while (resultSet.next()) {
                players.put(resultSet.getInt("id"), playerObiect(resultSet));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Player playerObiect(ResultSet resultSet) {
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

    public static JSONArray getPlayersJSON() {
        playerslist = FXCollections.observableArrayList();
        playerslist.removeAll();
        LinkedHashMap<String, JSONObject> jsonOrderedMap = new LinkedHashMap<String, JSONObject>();
        JSONArray jsonArray=new JSONArray();
        for (Map.Entry<Integer, Player> field : players.entrySet()) {
            Player player = field.getValue();

            JSONObject playerJson = new JSONObject();
            playerJson.put("id", player.getID());
            playerJson.put("name", player.getPlayerName());
            playerJson.put("score", player.getPlayerScore());
            playerJson.put("status", player.getPlayerStatus());
            playerslist.add(player);
            jsonArray.add(playerJson);
        }

        return jsonArray;
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

    public static Player getPlayer(int id) {

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
        System.out.println("update "+player);
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // logout
    public static void logout(JSONObject player) {
        player.put("status", "0");
        updateStatus(player); // refer to offline

    }


    // function used to register in GUI
    public static boolean createPlayer(JSONObject player) {
        try {
            PreparedStatement statment = db.connection.prepareStatement("INSERT players SET name=?, password=?, email=?");
            statment.setString(1, player.get("name").toString());
            statment.setString(2, player.get("password").toString());
            statment.setString(3, player.get("email").toString());
            int isInserted=statment.executeUpdate();
            if(isInserted>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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
                player.put("status", "1");
                player.put("id", res.getInt("id"));
                updateStatus(player); // refer to online
                jsonObject.put("id", res.getInt("id"));
                jsonObject.put("name", res.getString("name"));
                jsonObject.put("status", true);
                jsonObject.put("score", res.getString("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
