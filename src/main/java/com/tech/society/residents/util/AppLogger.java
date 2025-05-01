package com.tech.society.residents.util;

import java.time.LocalDateTime;

public class AppLogger {

    public static void log(String method, String user, String message) {
        System.out.println(String.format("[%s] [%s] [%s] %s",
                LocalDateTime.now(), method, user, message));
    }

    public static void logError(String method, String user, String message, Exception e) {
        System.err.println(String.format("[%s] [%s] [%s] ERROR: %s - %s",
                LocalDateTime.now(), method, user, message, e.getMessage()));
        e.printStackTrace();
    }
}