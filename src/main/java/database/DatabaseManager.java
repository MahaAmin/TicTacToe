/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author halim
 */
public class DatabaseManager {
    private Connection connection;
    private ResultSet resultSet; // used for store data from database
    public Vector<Player> players = new Vector<>(); // used to send to the server to reduce the requests on the database
    private Statement statement;
    private PreparedStatement preparedStatement;
    private String query;
    static final String DB_URL = "jdbc:mysql://localhost:3306/tic_tac_toe";
    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_USER = "root";
    static final String DB_PASSWD = "";
    
    public DatabaseManager(){
        startConnection();
        getPlayers();
        endConnection();
    }

    private void startConnection() {
        try {
            Class.forName(DB_DRV);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void endConnection() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getPlayers() {
        try {
            
            statement=connection.createStatement();
            resultSet=statement.executeQuery("SELECT * FROM players");
          
            while (resultSet.next()) {
                players.add(getPlayer()); // adding all the players to vector
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Player getPlayer(){
        try {
            return new Player(
                    resultSet.getInt(1), //return id
                    resultSet.getString(2), //return name 
                    resultSet.getString(3), //return password 
                    resultSet.getString(4), // return email
                    resultSet.getInt(5), // return status
                    resultSet.getInt(6) // return score
            );        
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Player();
    }
    
    public int createPlayer(Player p) {
        int playerCreated = 0;
        try {            
            query = "INSERT INTO players  (name, password, email) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, p.getPlayerName());
            preparedStatement.setString(3, p.getPlayerPassword());
            preparedStatement.setString(4, p.getPlayerEmail());
            playerCreated = preparedStatement.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playerCreated;
    }    
      
}
