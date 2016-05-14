package com.veda.util;

import com.veda.Connection;

import java.util.ArrayList;
import java.util.UUID;

public class ConnectionPool extends ArrayList<Connection> {
    private static ConnectionPool ourInstance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return ourInstance;
    }

    private ConnectionPool() {
    }

    public Connection get(UUID uuid) {
        return stream()
                .filter(connection -> connection.getUuid().equals(uuid))
                .findFirst().get();
    }
}
