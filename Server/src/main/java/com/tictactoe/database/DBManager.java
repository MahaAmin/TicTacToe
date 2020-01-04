
package com.tictactoe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONObject;

public class DBManager {
    static final String DB_URL = "jdbc:mysql://localhost:3306/tictactoe";
    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_USER = "root";
    static final String DB_PASSWD = "123456";
    Connection connection = null;
    
    public DBManager(){
        try{
            connection=(Connection) DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
            System.out.println("connected to db");
        }catch(Exception ex){
            System.out.println("error in sql statment");
        }
    }
    
    public boolean Register(JSONObject player){
        try{    
            PreparedStatement statment = connection.prepareStatement("INSERT players SET name=?, password=?, email=?");
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
    public boolean Login(JSONObject player){
        try{    
            PreparedStatement statment = connection.prepareStatement("SELECT * FROM players WHERE name=? AND password=?");
            statment.setString(1, player.get("name").toString());
            statment.setString(2, player.get("password").toString());
            ResultSet res = statment.executeQuery();
            System.out.println("done select");
            if(res.next()==false){
                return false;
            }
            else{
                System.out.println("returned true!!!!!!!!!");
                return true;
            }
        }catch(SQLException e){
            return false;
        }
    }
}
