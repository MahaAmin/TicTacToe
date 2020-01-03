/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author halim
 */
public class DatabaseManager {
    private Connection connection;
    private ResultSet resultSet;
    Statement statement;
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
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
