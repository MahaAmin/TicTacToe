package com.tictactoe.database;

public interface DBConfig {
    static final String DB_URL = "jdbc:mysql://localhost:3306/tictactoe";
    //    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_DRV = "com.mysql.cj.jdbc.Driver";
    static final String DB_USER = "root";
    static final String DB_PASSWD = "jaxon";


}
