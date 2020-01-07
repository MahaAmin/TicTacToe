package com.tictactoe.database;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseManager {
    public Connection connection;
    public Statement statement;
    public PreparedStatement preparedStatement;
    public String query;
    public ResultSet resultSet; // used for store data from database    


    public DatabaseManager(){
        startConnection();
    }

    private void startConnection() {
        try {
            Class.forName(DBConfig.DB_DRV);
            connection = DriverManager.getConnection(DBConfig.DB_URL,DBConfig.DB_USER,DBConfig.DB_PASSWD);
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
}
