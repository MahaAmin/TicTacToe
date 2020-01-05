package com.tictactoe.actions;

import com.tictactoe.database.DatabaseManager;

public interface DBConnection {
    public final static DatabaseManager db = new DatabaseManager();
}
