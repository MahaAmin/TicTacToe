/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.database.playerModel;

import com.tictactoe.actions.DBConnection;
import com.tictactoe.database.DatabaseManager;
import org.json.simple.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author halim
 */
public class PlayerModel {
    
    static  DatabaseManager db = DBConnection.db;
    private static Vector<Player> players = new Vector<>(); // used to send to the server to reduce the requests on the database
    // static for shared data "i need it once not for every object"
    
    public PlayerModel(){
        try {
            db.statement=db.connection.createStatement();
            db.resultSet=db.statement.executeQuery("SELECT * FROM players");
            while (db.resultSet.next()) {
                players.add(new Player(
                    db.resultSet.getInt(1), //return id
                    db.resultSet.getString(2), //return name 
                    db.resultSet.getString(3), //return password 
                    db.resultSet.getString(4), // return email
                    db.resultSet.getInt(5), // return status
                    db.resultSet.getString(6), //return avatar
                    db.resultSet.getInt(7) // return score
                ));// adding all the players to vector
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vector<Player> getPlayers() {
        return players;
    }
    
    public static Player getPlayer(int id){
        for(Player p: players){
            if(p.getID()==id)
                return p;
        }
        return null;
    }
    


    // update score
    public void updateScore(int id, int point){
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
    public static void updateStatus(int id, int status){
        try {
            db.preparedStatement = db.connection.prepareStatement("UPDATE players SET status=? WHERE id=?");
            db.preparedStatement.setInt(1, status);
            db.preparedStatement.setInt(2, id);
            db.preparedStatement.executeUpdate();
            
            Player p = getPlayer(id);
            assert p != null;
            p.setPlayerStatus(status);
            
        } catch (SQLException ex) {
            Logger.getLogger(PlayerModel.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    // logout
    public void logout(int id){
        updateStatus(id, 0); // refer to offline
    }


    // function used to register in GUI
    public static boolean createPlayer (JSONObject player){
        try{
            PreparedStatement statment = db.connection.prepareStatement("INSERT players SET name=?, password=?, email=?");
            statment.setString(1, player.get("name").toString());
            statment.setString(2, player.get("password").toString());
            statment.setString(3,player.get("email").toString());
            int res = statment.executeUpdate();
            System.out.println("done update");
            return res>0;
        }catch(SQLException e){
            return false;
        }
    }

    // function used to Login in GUI
    public static boolean validatePlalyer(JSONObject player){
        try{
            PreparedStatement statment = db.connection.prepareStatement("SELECT * FROM players WHERE email=? AND password=?");
            statment.setString(1, player.get("email").toString());
            statment.setString(2, player.get("password").toString());
            ResultSet res = statment.executeQuery();
            System.out.println("done select");
            if(!res.next()){
                return false;
            }
            else{
                updateStatus(res.getInt(1), 1); // refer to online
                System.out.println("got the player");
                return true;
            }
        }catch(SQLException e){
            return false;
        }
    }
    
}
