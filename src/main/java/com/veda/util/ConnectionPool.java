package com.veda.util;

import com.veda.Connection;

import java.util.ArrayList;

public class ConnectionPool extends ArrayList<Connection> {
    private static ConnectionPool ourInstance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return ourInstance;
    }

    private ConnectionPool() {
    }
}
