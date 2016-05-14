package com.veda.task;

import com.veda.util.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class CloseConnection {
    @Value("${veda.connection.timeout}")
    private Long timeout;

    private static long toMilli(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @Scheduled(initialDelay = 60000, fixedRate = 60000)
    public void close() {
        ConnectionPool.getInstance().removeIf(connection -> {
            if (toMilli(LocalDateTime.now()) - toMilli(connection.getLastAccessTime()) < timeout) return false;
            connection.getSessionFactory().close();
            return true;
        });
    }
}
