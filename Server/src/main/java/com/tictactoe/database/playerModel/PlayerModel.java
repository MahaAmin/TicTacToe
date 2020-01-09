/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.database.playerModel;

import com.tictactoe.actions.App;
import com.tictactoe.actions.DBConnection;
import com.tictactoe.database.DatabaseManager;
import org.json.simple.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author halim
 */
public class PlayerModel {

    static DatabaseManager db = App.getDB();
    public static Map<Integer, Player> players; // used to send to the server to reduce the requests on the database
    // static for shared data "i need it once not for every object"

    public static void getPlayers() {
        players = new HashMap<>();
        try {
            Statement stmt = db.connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM players");
            while (resultSet.next()) {

                players.put(resultSet.getInt("id"),
                        new Player(
                                resultSet.getInt("id"), //return id
                                resultSet.getString("name"), //return name
                                resultSet.getString("email"), // return email
                                resultSet.getInt("status"), // return status
                                resultSet.getString("avatar"), //return avatar
                                resultSet.getInt("score") // return score
                        ));// adding all the players to vector
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static JSONObject getPlayersJSON() {

        JSONObject playersJson = new JSONObject();

        for (Map.Entry<Integer, Player> field : players.entrySet()) {
            Player player = field.getValue();

            JSONObject playerJson = new JSONObject();
            playerJson.put("id", player.getID());
            playerJson.put("name", player.getPlayerName());
            playerJson.put("score", player.getPlayerScore());
            playerJson.put("status", player.getPlayerStatus());

            playersJson.put(field.getKey(), playerJson);
        }
        return playersJson;

    }


    public static Player getPlayer(int id) {

        Player player = players.get(id);
        if (player != null) {
            return player;
        }
        return null;
    }


    // update score
    public void updateScore(int id, int point) {
        try {
            db.preparedStatement = db.connection.prepareStatement("UPDATE players SET score=? WHERE id=?");
            db.preparedStatement.setInt(1, point);
            db.preparedStatement.setInt(2, id);
            db.preparedStatement.executeUpdate();

            Player p = getPlayer(id);
            if (p != null) {
                p.setPlayerScore(point);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // update status
    public static void updateStatus(int id, int status) {
        try {
            System.out.println("status id " + id);
            PreparedStatement preparedStatement = db.connection.prepareStatement("UPDATE players SET status=? WHERE id=?");
            preparedStatement.setString(1, Integer.toString(status));
            preparedStatement.setInt(2, id);
            int isUpdated = preparedStatement.executeUpdate();
            if (isUpdated > 0) {
                Player player = players.get(id);
                player.setPlayerStatus(status);
                players.replace(id, player);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // logout
    public static void logout(int id) {
        updateStatus(id, 0); // refer to offline
    }


    // function used to register in GUI
    public static boolean createPlayer(JSONObject player) {
        try {
            PreparedStatement statment = db.connection.prepareStatement("INSERT players SET name=?, password=?, email=?");
            statment.setString(1, player.get("name").toString());
            statment.setString(2, player.get("password").toString());
            statment.setString(3, player.get("email").toString());
            int res = statment.executeUpdate();
            System.out.println("done update");

            return res > 0;
        } catch (SQLException e) {
            return false;
        }
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
                updateStatus(res.getInt("id"), 1); // refer to online
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
