package com.veda;

import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

public class Connection {
    private UUID uuid;
    private String name;
    private LocalDateTime lastAccessTime;
    private Properties properties;
    private SessionFactory sessionFactory;

    public Connection(String name, Properties properties, SessionFactory sessionFactory) {
        this.uuid = UUID.randomUUID();
        this.lastAccessTime = LocalDateTime.now();

        this.name = name;
        this.properties = properties;
        this.sessionFactory = sessionFactory;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(LocalDateTime lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
