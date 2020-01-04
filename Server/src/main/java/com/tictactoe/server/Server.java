package com.tictactoe.server;

import com.tictactoe.database.DBManager;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server {
    ServerSocket ss;
    Socket s;
    ArrayList sockets = new ArrayList();
    public Server() throws Exception{
        try{
            ss = new ServerSocket(5000);
            System.out.println("Server is up");
            while(true){
                s = ss.accept();
                new DBHandler(s);
            } 
        }
        catch(Exception e){
            System.out.println("Not found");
        }
    }
    
    
    public class DBHandler extends Thread{
        DataInputStream dis;
        PrintStream os;
        String msg;
        DBHandler(Socket s) throws IOException {
            dis = new DataInputStream(s.getInputStream());
            os = new PrintStream(s.getOutputStream());
            sockets.add(this);
            start();
        }
        @Override
        public void run(){
            while(true){
                try {
                   msg = dis.readLine();
                   JSONParser parser = new JSONParser();
                   try{
                       JSONObject jsonMsg = (JSONObject) parser.parse(msg);
                       if(jsonMsg.get("type").equals("register")){
                           DBManager mng = new DBManager();
                           System.out.println("db manager created");
                           boolean resp = mng.Register(jsonMsg);
                           os.println(resp);
                       }else if(jsonMsg.get("type").equals("login")){
                           DBManager mng = new DBManager();
                           System.out.println("db manager created");
                           boolean resp = mng.Login(jsonMsg);
                           os.println(resp);
                       }
                   }catch(ParseException e){
                       System.out.println("can't get json");
                    }
                } catch (IOException ex) {
                }
            }
        }
    }
}
