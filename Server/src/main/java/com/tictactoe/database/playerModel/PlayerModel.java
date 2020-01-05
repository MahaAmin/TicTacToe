/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tictactoe.database.playerModel;

import com.tictactoe.database.DatabaseManager;

import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author halim
 */
public class PlayerModel {
    
    DatabaseManager db = new DatabaseManager();
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
    
    public Player getPlayer(int id){
        for(Player p: players){
            if(p.getID()==id)
                return p;
        }
        return null;
    }
    
    // function used to register in GUI
    public int createPlayer(Player p) {
        int playerCreated = 0;
        try {            
            db.query = "INSERT INTO players  (name, password, email) VALUES (?,?,?)";
            db.preparedStatement = db.connection.prepareStatement(db.query);
            db.preparedStatement.setString(2, p.getPlayerName());
            db.preparedStatement.setString(3, p.getPlayerPassword());
            db.preparedStatement.setString(4, p.getPlayerEmail());
            playerCreated = db.preparedStatement.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playerCreated;
    }
    
    // function used to Login in GUI
    public boolean validatePlalyer(String name, String pass){
        try{    
            db.preparedStatement = db.connection.prepareStatement("SELECT * FROM players WHERE name=? AND password=?");
            db.preparedStatement.setString(1, name);
            db.preparedStatement.setString(2, pass);
            db.resultSet = db.preparedStatement.executeQuery();
            if(db.resultSet.next()==false){
                return false;
            }
            else{
                updateStatus(db.resultSet.getInt(1), 1); // refer to online
                return true;
            }
        }catch(SQLException e){
            return false;
        }
    }    
    
    // update score
    public void updateScore(int id, int point){
        try {
            db.preparedStatement = db.connection.prepareStatement("UPDATE players SET score=? WHERE id=?");
            db.preparedStatement.setInt(1, point);
            db.preparedStatement.setInt(2, id);
            db.preparedStatement.executeUpdate();
            
            Player p = getPlayer(id);
            p.setPlayerScore(point);
            
        } catch (SQLException ex) {
            Logger.getLogger(PlayerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // update status
    public void updateStatus(int id, int status){
        try {
            db.preparedStatement = db.connection.prepareStatement("UPDATE players SET status=? WHERE id=?");
            db.preparedStatement.setInt(1, status);
            db.preparedStatement.setInt(2, id);
            db.preparedStatement.executeUpdate();
            
            Player p = getPlayer(id);
            p.setPlayerStatus(status);
            
        } catch (SQLException ex) {
            Logger.getLogger(PlayerModel.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    // logout
    public void logout(int id){
        updateStatus(id, 0); // refer to offline
    }
    
}
